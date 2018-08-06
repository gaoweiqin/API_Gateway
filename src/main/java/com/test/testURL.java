package com.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class testURL {
    public static void main(String[] args) {
        String urlString = "http://localhost:8080/api/employees/find";
        try {
            URL url = new URL(urlString);
            System.out.println(url);
            getHeadByName(url);
        } catch (IOException e) {
            System.out.println("Fail to connect to " + urlString);
        }
    }
    public static void getHeadByName(URL url) {
        try {
            /**
             * 不区分大小写
             */
            URLConnection uc = url.openConnection();
            System.out.println("Content-Type: "
                    + uc.getHeaderField("content-type"));
            System.out.println("CONTENT-TYPE: "
                    + uc.getHeaderField("CONTENT-TYPE"));
            System.out.println("DATE: "
                    + uc.getDate());

        } catch (MalformedURLException e) {
            // TODO: handle exception
            System.err.println(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
