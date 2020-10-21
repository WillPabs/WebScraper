package com.pabitero;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebUtilities {

    public WebUtilities() {}

    public String getHTMLPage() {
        String url = null;
        URLConnection connection = null;
        boolean connected = false;
        try {
            while(!connected) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Paste URL below:\t");
                url = scan.nextLine();
                connection = new URL(url).openConnection();
                connection.setConnectTimeout(100);
                Scanner scanner = new Scanner(connection.getInputStream());
                scanner.useDelimiter("\\Z");
                url = scanner.next();
                scanner.close();
                System.out.println("HTML page successfully scraped");
                connected = true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    public void HTMLToFile(String htmlString) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name for scraped file");
        String fileName = scan.nextLine();

        String userHomeFolder = System.getProperty("user.home") + "/Desktop/WebScraperFiles";
        File file = new File(userHomeFolder, fileName+".txt");

        if(!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        try {
            writer.write(htmlString);
            writer.close();
            writer.close();
            System.out.println(fileName + " was created");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void findLinksInFile(String fileName) {
        String searchFile;
        try {
            searchFile = Files.readString(Paths.get(fileName));
            String regex = "<a[^>]+href=[\\\"']?([\\\"'>]+)[\\\"']?[^>]*>(.+?)<\\/a>";
            Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
            Matcher matcher = p.matcher(searchFile);
            ArrayList<String> links = new ArrayList<>();

            while(matcher.find()) {
                String link = matcher.group();
                links.add(link);
            }

            File linkFile = new File("linkFile.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(linkFile));

            bw.write(String.valueOf(links));
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
