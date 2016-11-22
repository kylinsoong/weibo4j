package org.ksoong.weibo4j.examples.jsoup.cookbook;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CookBook_5 {

    public static void main(String[] args) throws IOException {
        
        File input = new File("/home/kylin/tmp/view-source_ksoong.org_archive_.html");
        Document doc = Jsoup.parse(input, "UTF-8", "http://ksoong.com/");
        System.out.println(doc.title());
        System.out.println(doc.body());
        System.out.println(doc.text());
    }

}
