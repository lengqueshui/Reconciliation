package com.zglc.reconciliation.utils;

public class CommonUtil {

    public static long getAmount(String value){
        if (value == null || value.length() == 0) {
            return 0;
        } else {
            return (long) (Double.parseDouble(value) * 100);
        }
    }


}
