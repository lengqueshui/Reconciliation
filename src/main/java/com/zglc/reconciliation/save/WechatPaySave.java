package com.zglc.reconciliation.save;

import com.zglc.reconciliation.db.SqlOperation;
import com.zglc.reconciliation.model.HuifuRechargeModel;
import com.zglc.reconciliation.utils.DateUtil;
import com.zglc.reconciliation.utils.ReadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class WechatPaySave {

    private static Logger logger = LoggerFactory.getLogger(YeepaySave.class);

    private static final String CHARSET_NAME = "UTF-8";

    private final static String FILE_PATH = "/Users/mah/Downloads/审计/第三方支付微信账单汇总/微信账单/2015年";

    public static void main(String[] args) {
        String fileName = "/Users/mah/Downloads/审计/第三方支付微信账单汇总/微信账单/2015年/微信-交易账单-201502.csv";
        File rootFile = new File(fileName);
        processFileName(rootFile);
    }

    private static void processFileName(File file){
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles != null && childFiles.length > 0) {
                for (File childFile: childFiles) {
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
        System.out.println(list.size());
//        交易时间	公众账号ID	商户号	子商户号	设备号	微信订单号	商户订单号	用户标识	交易类型	交易状态	付款银行	货币种类	总金额
//        企业红包金额	微信退款单号	商户退款单号	退款金额
//        企业红包退款金额	退款类型	退款状态	商品名称	商户数据包	手续费	费率
        //for (int i = 1; i < list.size(); i++) {
        for (int i = 1; i <= 1; i++) {
            System.out.println(list.get(i));
            String s = list.get(i);
            String[] a = s.split(",`", -1);
            System.out.println(a.length);
            for (String b: a) {
                System.out.println(b);
            }
        }
    }

}
