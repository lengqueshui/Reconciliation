package com.zglc.reconciliation.save;

import com.zglc.reconciliation.db.SqlOperation;
import com.zglc.reconciliation.model.YeepayModel;
import com.zglc.reconciliation.utils.DateUtil;
import jxl.Sheet;
import jxl.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class YeepaySave {

    private static Logger logger = LoggerFactory.getLogger(YeepaySave.class);

    private static final String CHARSET_NAME = "UTF-8";

    private final static String FILE_PATH = "/Users/mah/Downloads/审计/第三方支付易宝账单汇总/充值数据";

    public static void main(String[] args) {
        File rootFile = new File(FILE_PATH);
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
        Connection con = SqlOperation.getConnection();
        List<YeepayModel> list = new ArrayList<YeepayModel>();
        try {
            Workbook workbook = Workbook.getWorkbook(new File(filePath));
            Sheet sheet = workbook.getSheet(0);
            for (int i = 1; i < sheet.getRows(); i++) {
                YeepayModel yeepayModel = new YeepayModel();
                for (int j = 0; j < sheet.getColumns(); j++) {
                    String value = sheet.getCell(j, i).getContents() == null ? "" : sheet.getCell(j, i).getContents().trim();
                    //j == 0为序号，序号不为整型则为无效数据(一般标的和title列为无效数据)，不作处理跳过
                    if (j == 0) {
                        try {
                            Integer.parseInt(value);
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.error(sheet.getCell(j, i).getContents());
                            break;
                        }
                    }

                    switch (j) {
                        case 0:
                            yeepayModel.setIndex(Integer.parseInt(value));
                            break;
                        case 1:
                            yeepayModel.setDate(DateUtil.str2Datetime(value));
                            break;
                        case 2:
                            yeepayModel.setAccountType(value);
                            break;
                        case 3:
                            yeepayModel.setBusiType(value);
                            break;
                        case 4:
                            yeepayModel.setMerTransId(value);
                            break;
                        case 5:
                            if (value == null || value.length() == 0) {
                                yeepayModel.setAmount(0);
                            } else {
                                yeepayModel.setAmount((long) (Double.parseDouble(value) * 100));
                            }

                            break;
                        case 6:
                            if (value == null || value.length() == 0) {
                                yeepayModel.setOutAmount(0);
                            } else {
                                yeepayModel.setOutAmount((long) (Double.parseDouble(value) * 100));
                            }


                            break;
                        case 7:
                            if (value == null || value.length() == 0) {
                                yeepayModel.setFee(0);
                            } else {
                                yeepayModel.setFee((long) (Double.parseDouble(value) * 100));
                            }

                            break;
                        case 8:
                            if (value == null || value.length() == 0) {
                                yeepayModel.setBalance(0);
                            } else {
                                yeepayModel.setBalance((long) (Double.parseDouble(value) * 100));
                            }

                            break;
                        case 9:
                            yeepayModel.setRemark(value);
                            break;
                    }
                }

                yeepayModel.setFileName(fileName);
                list.add(yeepayModel);

            }

            workbook.close();// 关闭
        } catch (Exception e) {
            e.printStackTrace();
        }

        SqlOperation.batchYeePayRechargeInsert(con, list);
        SqlOperation.close(con);
    }
}
