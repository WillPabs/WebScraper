package com.pabitero;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        WebUtilities webUtils = new WebUtilities();

        try {
            String file = "C:\\Users\\William\\Desktop\\WebScraperFiles\\electronics.txt";
            webUtils.findLinksInFile(file);
//            String page = webUtils.getHTMLPage();
//            webUtils.HTMLToFile(page);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
