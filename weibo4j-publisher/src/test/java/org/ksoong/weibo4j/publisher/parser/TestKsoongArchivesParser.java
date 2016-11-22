package org.ksoong.weibo4j.publisher.parser;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.ksoong.weibo4j.publisher.IParser;
import org.ksoong.weibo4j.publisher.Post;

/**
 * https://jsoup.org/cookbook/extracting-data/selector-syntax
 * @author kylin
 *
 */
public class TestKsoongArchivesParser {
    
    @Test
    public void testParser_1() throws Exception {
        IParser parser = new KsoongArchivesParser(){

            @Override
            protected Document createDocument() throws IOException {
                return Jsoup.parse(TestKsoongArchivesParser.class.getClassLoader().getResourceAsStream("ksoongachieves.html"), "UTF-8", "ksoong.org");
            }};
        
        List<Post> posts = parser.parse();
        
        assertEquals(185, posts.size());
    }

    @Test
    public void testParser_2() throws Exception {
        
        Document doc = Jsoup.parse(TestKsoongArchivesParser.class.getClassLoader().getResourceAsStream("ksoongachieves.html"), "UTF-8", "ksoong.org");
        
        Elements links = doc.select("a[href]");
        links.forEach(link -> {
            String linkHref = link.attr("href");
            String linkText = link.text();
            System.out.println(linkHref + " - " + linkText);
        });
     
    }
    
    @Test
    public void testParser_3() throws Exception {
        
        Document doc = Jsoup.parse(TestKsoongArchivesParser.class.getClassLoader().getResourceAsStream("ksoong.org.tree.html"), "UTF-8", "ksoong.org");
        
        Elements content = doc.select("a[href]");
     
    }
}
