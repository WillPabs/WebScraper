package com.pabitero;

public class Main {

    public static void main(String[] args) {
        WebUtilities webUtils = new WebUtilities();

        try {
            String page = webUtils.getHTMLPage();
            webUtils.HTMLToFile(page);
            String file = "C:\\Users\\William\\Desktop\\WebScraperFiles\\electronics.txt";
            webUtils.findLinksInFile(file);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
