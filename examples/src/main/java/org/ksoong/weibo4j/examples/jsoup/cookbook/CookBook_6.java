package org.ksoong.weibo4j.examples.jsoup.cookbook;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CookBook_6 {

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("http://ksoong.org/archive/").get();
        
        Element content = doc.getElementById("content");
        Elements links = content.getElementsByTag("a"); 
        
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();
            
            System.out.println(linkHref + " - " + linkText);
        }
    }

}
