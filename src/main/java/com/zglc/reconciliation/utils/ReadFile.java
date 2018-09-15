package com.zglc.reconciliation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public static List<String> getListString(String fileName, String charsetName) {
        List<String> list = new ArrayList<String>();
        try {
            File file = new File(fileName);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), charsetName);
            BufferedReader br = new BufferedReader(isr);
            String s = null;
            while ((s = br.readLine()) != null) {
                list.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}
