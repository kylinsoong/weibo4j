package org.ksoong.weibo4j.examples.jsoup.cookbook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CookBook_2 {

    public static void main(String[] args) {

        String html = "<html><head><title>First parse</title></head>" + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html, "https://jsoup.org/cookbook/input/parse-document-from-string");
        System.out.println(doc.title());
        System.out.println(doc.body());
        System.out.println(doc.text());
    }

}
