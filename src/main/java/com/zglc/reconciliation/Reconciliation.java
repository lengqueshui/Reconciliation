package com.zglc.reconciliation;

import com.zglc.reconciliation.db.SqlOperation;

import java.io.File;

public class Reconciliation {

    private final static String FILE_PATH = "/Users/mah/Downloads/审计";

    public static void main(String[] args) {
        File rootFile = new File(FILE_PATH);
        printFileName(rootFile);
    }

    private static void printFileName(File file){
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles != null && childFiles.length > 0) {
                for (File childFile: childFiles) {
                    printFileName(childFile);
                }
            }

            System.out.println(file.getAbsolutePath());
            return;
        }

        System.out.println(file.getAbsolutePath());

    }
}
