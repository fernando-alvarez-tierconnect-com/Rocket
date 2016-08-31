package com.trucentrix.test;

/**
 * Created by falvarez on 11/10/2014.
 */

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class StringCounter {

    public static int count = 0;

    public static void main(String[] args) throws Exception {
        //1116 files
//        listFiles("D:\\share_\\PERFORMANCE_TEST_TRUEDX_391_5591\\1116_JerryFiles\\WebUpdateTestFiles-3.9.1-5591-20141015");
        // 1069 files
        listFiles("D:\\share_\\PERFORMANCE_TEST_TRUEDX_391_5591\\logsJerry3.9.1_5571\\TRUedx-web-update-files-20141007-3.9.1-1");
        //33326 files
//        listFiles("D:\\share_\\PERFORMANCE_TEST_TRUEDX_391_5591\\33326_JerryFiles\\20141014day");
        //1092 files - Build 5607
//        listFiles("D:\\share_\\TEST_TRUEDX_391_5607\\WebUpdateFilesJerry\\WebUpdateTestFiles-3.9.1-5607-20141104");
//        listFiles("C:\\testCounter");
        //1088 Files
        //listFiles("D:\\share_\\TEST_TRUEDX_391_5621\\JerryFiles\\WebUpdateTestFiles-3.9.1-5621-20141201");
        //980 Files
//        listFiles("D:\\share_\\TEST_TRUEDX_391_5621\\JerryFiles\\WebUpdateTestFiles-3.9.1-5621-20141202");
        //3476 Files
        //listFiles("D:\\share_\\TRUedx_3.9.1_5643\\xmlFiles\\webupdae20141210-3.9.1TestFiles");


        System.out.println("Total = " + count);
    }

    public static int counter(File file) throws IOException {
        String str = FileUtils.readFileToString(file);
        str = StringUtils.lowerCase(str);
        int countThe = StringUtils.countMatches(str, "<document>");
        count = count + countThe;
        return countThe;
    }

    public static void listFiles(String directoryName) {
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile() && file.getName().contains("ecdocmd")) {

                try {
                    int counter = counter(file);
//                    if (counter > 100)
//                        System.out.println(file.getName()+" - "+counter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}