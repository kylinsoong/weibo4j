package org.ksoong.weibo4j.examples.jsoup.cookbook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CookBook_3 {

    public static void main(String[] args) {

        String html = "<div><p>Lorem ipsum.</p>";
        Document doc = Jsoup.parseBodyFragment(html);
        Element body = doc.body();
        
        System.out.println(body.data());
    }

}
