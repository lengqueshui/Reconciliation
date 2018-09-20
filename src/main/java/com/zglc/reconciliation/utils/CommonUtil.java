package com.zglc.reconciliation.utils;

public class CommonUtil {
    /**
     * 小数点后两位的String类型金额转换为long类型的单位分
     *
     * @param amount
     * @return
     */
    public static long getAmount(String amount) {
        if(amount == null || amount.length() == 0){
            return 0;
        }

        String strAmount;
        if (amount.contains(".")) {
            //100.0
            if ((amount.length() - (amount.indexOf(".") + 1)) == 1) {
                strAmount = amount.replace(".", "") + "0";
            } else {
                //100.00
                strAmount = amount.replace(".", "");
            }
        } else {
            //100
            strAmount = amount + "00";
        }


        try {
            return Long.parseLong(strAmount);
        } catch (NumberFormatException e) {
            return 0;
        }
    }


}
