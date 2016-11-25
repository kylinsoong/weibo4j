package org.ksoong.weibo4j.publisher.parser;

import static org.ksoong.weibo4j.publisher.Post.Type.KSOONG_BLOG;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.ksoong.weibo4j.publisher.IParser;
import org.ksoong.weibo4j.publisher.Post;
import org.ksoong.weibo4j.publisher.Post.Img;
import org.ksoong.weibo4j.publisher.Post.Imgs;
import org.ksoong.weibo4j.publisher.exceptions.JsoupParseException;

/**
 * A parser to parse http://ksoong.org/archive/ to generate posts
 * @author kylin
 *
 */
public class KsoongArchivesParser implements IParser {
    
    Logger log = Logger.getLogger(KsoongArchivesParser.class.getName());
    
    private static final String URL = "http://ksoong.org/archive/";
    private static final String BASE_URL = "http://ksoong.org";
    
    protected Document createDocument() throws IOException {
        return Jsoup.connect(URL).get();
    }
    
    protected Document createDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    @Override
    public List<Post> parse() throws Exception {
        
        List<Post> posts = new ArrayList<>();
        
        Document doc = createDocument();
        
        Element content = doc.getElementById("content");
        Elements links = content.getElementsByTag("a"); 
        
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();
            Post post = new Post();
            post.setTxt(linkText);
            post.setLink(linkHref);
            post.setType(KSOONG_BLOG);
            posts.add(post);
        }
        
        posts = posts.stream().map(p -> {
            Post post = p;
            post.setLink(BASE_URL + post.getStatusLink());
            post.setSourceURL(post.getStatusLink());
            return post;
        }).collect(Collectors.toList());
        
        posts  = posts.stream().map( p -> {
            Post post = p;
            parseWebPage(post);
            return post;
        }).collect(Collectors.toList());
        
        return posts;
    }

    private void parseWebPage(Post post) {
        
        String sourceURL = post.getSourceURL();
        
        try {
            Document doc = createDocument(sourceURL);
            Element content = doc.getElementById("content");
            
            Elements pics = content.getElementsByTag("img");
            List<Img> list = new ArrayList<>();
            
            pics.forEach(pic -> {
                String src = pic.attr("src");
                String alt = pic.attr("alt");
                String name = src.substring(src.lastIndexOf(File.separator) + 1, src.length());
                list.add(new Img(alt, BASE_URL + src, name));
            });
            Img[] imgs = new Img[list.size()]; 
            logImg(sourceURL, list);
            post.setImgs(new Imgs(list.toArray(imgs)));
            
            Elements h2s = content.getElementsByTag("h2");
            StringBuilder sb = new StringBuilder();
            h2s.forEach(h2 -> {
                sb.append(h2.text());
                sb.append(", ");
            });
            
            String txt = sb.toString();
            txt = txt.substring(0, txt.lastIndexOf(","));
            if(txt.length() > 100) {
                txt = txt.substring(0, 100);
                txt += "...";
            }
            
            if(txt.length() > 20) {
                post.setTxt(txt);
            }
            
        } catch (IOException e) {
            throw new JsoupParseException(e);
        }
    }


    private void logImg(String sourceURL, List<Img> list) {
        log.info("Load " + list + " from " + sourceURL );
    }

}
