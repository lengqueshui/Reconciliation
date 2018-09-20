package com.zglc.reconciliation.save;

import com.zglc.reconciliation.db.SqlOperation;
import com.zglc.reconciliation.model.HuifuRechargeModel;
import com.zglc.reconciliation.model.WechatModel;
import com.zglc.reconciliation.utils.CommonUtil;
import com.zglc.reconciliation.utils.DateUtil;
import com.zglc.reconciliation.utils.ReadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WechatPaySave {

    private static Logger logger = LoggerFactory.getLogger(YeepaySave.class);

    private static final String CHARSET_NAME = "UTF-8";

    private final static String FILE_PATH = "/Users/mah/Downloads/审计/第三方支付微信账单汇总/微信账单";

    public static void main(String[] args) {
        String fileName = "/Users/mah/Downloads/审计/第三方支付微信账单汇总/微信账单/2015年/微信-交易账单-201512.csv";
        File rootFile = new File(fileName);
        processFileName(rootFile);
    }

    private static void processFileName(File file) {
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles != null && childFiles.length > 0) {
                for (File childFile : childFiles) {
                    processFileName(childFile);
                }
            }

            System.out.println(file.getAbsolutePath());
            return;
        }

        System.out.println(file.getAbsolutePath());
        save(file.getAbsolutePath(), file.getName());
    }

    public static void save(String filePath, String fileName) {
        List<String> list = ReadFile.getListString(filePath, CHARSET_NAME);
        Connection con = SqlOperation.getConnection();
//        交易时间	公众账号ID	商户号	子商户号	设备号
// 微信订单号	商户订单号	用户标识	交易类型	交易状态	付款银行	货币种类	总金额
//        企业红包金额	微信退款单号	商户退款单号	退款金额
//        企业红包退款金额	退款类型	退款状态	商品名称	商户数据包	手续费	费率

//        `2015-02-02 12:01:28,`wx2ea510113b49e9b9,`1226972102,`0,`,`1006890668201502020011902448,
//        `DASZ2849674645682Z19,`o15zytwRrdCL_9wwUySZgYesa0aw,`JSAPI,`SUCCESS,`CMB_DEBIT,`CNY,`1.00,
//        `0.00,`0,`0,`0,`0,`,`,`zhugelicai.com,`,`0.00600,`0.60%

        List<WechatModel> modelList = new ArrayList<WechatModel>();
        for (int i = 1; i < list.size(); i++) {
            WechatModel model = new WechatModel();
            String s = list.get(i);
            if (s.startsWith("`")) {
                s = s.substring(1);
            }

            String[] a = s.split(",`", -1);
            for (int j = 0; j < a.length; j++) {
                String value = a[j];
                Date date = null;
                if (j == 0) {
                    try {
                        date = DateUtil.str2Datetime(value);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error(value);
                        break;
                    }
                }

                switch (j) {
                    case 0:
                        model.setDate(date);
                        break;
                    case 1:
                        model.setAppId(value);
                        break;
                    case 2:
                        model.setMerId(value);
                        break;
                    case 3:
                        model.setChildMerId(value);
                        break;
                    case 4:
                        model.setDeviceId(value);
                        break;
                    case 5:
                        model.setWxTransId(value);
                        break;
                    case 6:
                        model.setMerTransId(value);
                        break;
                    case 7:
                        model.setWxUserNo(value);
                        break;
                    case 8:
                        model.setType(value);
                        break;
                    case 9:
                        model.setStatus(value);
                        break;
                    case 10:
                        model.setBankNo(value);
                        break;
                    case 11:
                        model.setCcyNo(value);
                        break;
                    case 12:
                        model.setAmount(CommonUtil.getAmount(value));
                        break;
                    case 13:
                        model.setHongbaoAmount(CommonUtil.getAmount(value));
                        break;
                    case 14:
                        model.setWxRefundTransId(value);
                        break;

                    case 15:
                        model.setMerRefundTransId(value);
                        break;
                    case 16:
                        model.setRefundAmount(CommonUtil.getAmount(value));
                        break;
                    case 17:
                        model.setRefundHongbaoAmount(CommonUtil.getAmount(value));
                        break;
                    case 18:
                        model.setRefundType(value);
                        break;
                    case 19:
                        model.setRefundStatus(value);
                        break;

                    case 20:
                        model.setProductName(value);
                        break;
                    case 21:
                        model.setMerDataPackage(value);
                        break;
                    case 22:
                        model.setFee(CommonUtil.getAmount(value));
                        break;
                    case 23:
                        model.setFeePercent(value);
                        break;
                }
            }

            if (model.getDate() != null) {
                model.setFileName(fileName);
                modelList.add(model);

                if (modelList.size() == 500) {
                    SqlOperation.batchWechatPayRechargeInsert(con, modelList);
                    modelList = new ArrayList<WechatModel>();
                }
            }

        }

        SqlOperation.batchWechatPayRechargeInsert(con, modelList);
        SqlOperation.close(con);
    }

}
