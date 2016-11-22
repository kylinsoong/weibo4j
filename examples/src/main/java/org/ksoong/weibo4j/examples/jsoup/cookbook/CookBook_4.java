package org.ksoong.weibo4j.examples.jsoup.cookbook;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CookBook_4 {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://ksoong.org/archive/").get();

        System.out.println(doc.title());
        System.out.println(doc.body());
        System.out.println(doc.text());
        
    }   

}
