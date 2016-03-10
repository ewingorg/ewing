package com.ewing.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlFetcher {

    public static String fetchContent(final String url) {
        try {
            Connection conn = Jsoup.connect(url);
            conn.timeout(120 * 1000);
            Document doc = conn.get();
          

            return doc.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        String url = "http://120.25.210.50:8080/ewingapp/module/index.html?userId=10";
        String content = fetchContent(url);
        System.out.println(content);
    }
}
