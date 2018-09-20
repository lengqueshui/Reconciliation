package com.zglc.reconciliation.save;

import com.zglc.reconciliation.db.SqlOperation;
import com.zglc.reconciliation.model.HuifuRechargeModel;
import com.zglc.reconciliation.utils.CommonUtil;
import com.zglc.reconciliation.utils.DateUtil;
import com.zglc.reconciliation.utils.ReadFile;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class huifuSave {

    private static final String CHARSET_NAME = "GBK";

    private final static String FILE_PATH = "/Users/mah/Downloads/审计/第三方支付汇付账单汇总/充值数据";

    public static void main(String[] args) {
        File rootFile = new File(FILE_PATH);
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

    //"/Users/mah/Downloads/审计/第三方支付汇付账单汇总/充值数据/2018-09.csv"
    public static void save(String filePath, String fileName) {
        List<String> list = ReadFile.getListString(filePath, CHARSET_NAME);
        //System.out.println(list.size());
        List<HuifuRechargeModel> modelList = new ArrayList<HuifuRechargeModel>();
        Connection con = SqlOperation.getConnection();
        for (int i = 1; i < list.size(); i++) {

            String s = list.get(i).replace("\"", "");
            String[] a = s.split(",", -1);
            HuifuRechargeModel model = new HuifuRechargeModel();
            model.setFileName(fileName);
            model.setMerTransId(a[0]);
            model.setOperDate(DateUtil.str2Date(a[1]));
            model.setAmount(CommonUtil.getAmount(a[2]));
            model.setMerUserId(a[3]);
            model.setMerUserName(a[4]);
            model.setBankName(a[5]);
            model.setType(a[6]);
            model.setFee(CommonUtil.getAmount(a[7]));
            model.setMerFeeId(a[8]);
            model.setActualAmount(CommonUtil.getAmount(a[9]));
            model.setStatus(a[10]);
            model.setStatusRemark(a[11]);
            model.setFinDate(a[12]);

            modelList.add(model);
        }

        SqlOperation.batchHuifuRechargeInsert(con, modelList);
        SqlOperation.close(con);
    }


}
