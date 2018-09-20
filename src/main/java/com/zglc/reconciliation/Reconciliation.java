package com.zglc.reconciliation;

import com.zglc.reconciliation.db.SqlOperation;

import java.io.File;
import java.util.Scanner;

public class Reconciliation {

    private final static String FILE_PATH = "/Users/mah/Downloads/审计";

    public static void main(String[] args) {
        //File rootFile = new File(FILE_PATH);
        //printFileName(rootFile);
        //Scanner sc = new Scanner(System.in);
        //System.out.println(findMe(sc.nextInt()));
    }

    static int[] array = {-5, -1, 0, 5, 9, 11, 13, 15, 22, 35, 46};
    private static boolean findMe(int x){
        int maxIdx = array.length - 1;
        int minIdx = 0;
        int maxNum = array[maxIdx];
        int minNum = array[minIdx];
        while (maxIdx > minIdx) {
            System.out.println("process minArrayIdx = " + minIdx  + " minNum = " + minNum +
                    " maxArrayIdx = " + maxIdx + " maxNum = " + maxNum);
            if (x == (maxNum + minNum)) {
                System.out.println("success minArrayIdx = " + minIdx  + " minNum = " + minNum +
                        " maxArrayIdx = " + maxIdx + " maxNum = " + maxNum);
                return true;
            } else if (x > (maxNum + minNum)) {
                minIdx++;
                minNum = array[minIdx];
            } else {
                maxIdx--;
                maxNum = array[maxIdx];
            }
        }

        System.out.println("fail");
        return false;
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
