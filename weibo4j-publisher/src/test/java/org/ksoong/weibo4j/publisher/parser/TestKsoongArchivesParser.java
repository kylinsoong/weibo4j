package org.ksoong.weibo4j.publisher.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ksoong.weibo4j.publisher.IParser;
import org.ksoong.weibo4j.publisher.Post;
import org.ksoong.weibo4j.publisher.Post.Img;

/**
 * https://jsoup.org/cookbook/extracting-data/selector-syntax
 * @author kylin
 *
 */
public class TestKsoongArchivesParser {
    
    private static IParser createTestParser(){
        return  new KsoongArchivesParser(){

            @Override
            protected Document createDocument(String url) throws IOException {
                return Jsoup.parse(TestKsoongArchivesParser.class.getClassLoader().getResourceAsStream("ksoong.org.tree.html"), "UTF-8", "ksoong.org");
            }

            @Override
            protected Document createDocument() throws IOException {
                return Jsoup.parse(TestKsoongArchivesParser.class.getClassLoader().getResourceAsStream("ksoongachieves.html"), "UTF-8", "ksoong.org");
            }};
    }
    
    static IParser parser;
    
    @BeforeClass
    public static void initParser() {
        parser = createTestParser();
    }
    
    @Test
    public void testParser_1() throws Exception {
        
        List<Post> posts = parser.parse();
        
        assertEquals(185, posts.size());
        posts.forEach(p -> {
            assertEquals(p.getSourceURL(), p.getStatusLink());
            assertNotNull(p.getStatus());
            assertTrue(p.getStatus().contains(p.getSourceURL()));
        });
        
        Post post = posts.get(0);
        assertEquals(18, post.getImgs().imgs().length);
        Img img = post.getImg();
        assertTrue(img.getSrc().startsWith("http://ksoong.org") && img.getSrc().endsWith(".png"));
        assertTrue(img.getName().endsWith(".png"));
    }
    
    @Test
    public void testParser_2() throws Exception {

        List<Post> posts = parser.parse();
        Post p = posts.get(10);
        assertEquals(p.getSourceURL(), p.getStatusLink());
        assertNotNull(p.getStatus());
        assertTrue(p.getStatus().contains(p.getSourceURL()));
    }
    
    @Test
    public void testParser_3() throws Exception {

        List<Post> posts = parser.parse();
        Post p = posts.get(10);
        assertNull(p.getPic());
    }

    @Test
    public void testJsoup_1() throws Exception {
        
        Document doc = Jsoup.parse(TestKsoongArchivesParser.class.getClassLoader().getResourceAsStream("ksoongachieves.html"), "UTF-8", "ksoong.org");
        Element content = doc.getElementById("content");
        Elements links = content.select("a[href]");
        links.forEach(link -> {
            String linkHref = link.attr("href");
            String linkText = link.text();
            assertNotNull(linkHref);
            assertNotNull(linkText);
        });
     
    }
    
    @Test
    public void testJsoup_2() throws Exception {
        
        Document doc = Jsoup.parse(TestKsoongArchivesParser.class.getClassLoader().getResourceAsStream("ksoong.org.tree.html"), "UTF-8", "ksoong.org");
        Element content = doc.getElementById("content");
        
        Elements pics = content.getElementsByTag("img");
        
        pics.forEach(pic -> {
            String src = pic.attr("src");
            String alt = pic.attr("alt");
            assertTrue(src.startsWith("/assets/blog/java"));
            assertNotNull(alt);
            src = src.substring(src.lastIndexOf(File.separator) + 1, src.length());
            assertTrue(src.endsWith(".png"));
        });
     
    }
    
    @Test
    public void testJsoup_3() throws Exception {
        
        Document doc = Jsoup.parse(TestKsoongArchivesParser.class.getClassLoader().getResourceAsStream("ksoong.org.tree.html"), "UTF-8", "ksoong.org");
        Element content = doc.getElementById("content");
        
        Elements h2s = content.getElementsByTag("h2");
        StringBuilder sb = new StringBuilder();
        h2s.forEach(h2 -> {
            sb.append(h2.text());
            sb.append(", ");
        });
        
        String txt = sb.toString();
        if(txt.length() > 100) {
            txt = txt.substring(0, 100);
            txt += "...";
        }
        
     
    }
}
