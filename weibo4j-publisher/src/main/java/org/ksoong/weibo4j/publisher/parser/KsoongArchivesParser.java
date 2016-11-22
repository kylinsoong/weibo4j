package org.ksoong.weibo4j.publisher.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.ksoong.weibo4j.publisher.IParser;
import org.ksoong.weibo4j.publisher.Post;

public class KsoongArchivesParser implements IParser {
    
    private static final String URL = "http://ksoong.org/archive/";
    private static final String BASE_URL = "http://ksoong.org";
    
    protected Document createDocument() throws IOException {
        return Jsoup.connect(URL).get();
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
            posts.add(post);
        }
        
        posts.stream().map(p -> {
            Post post = p;
            post.setLink(BASE_URL + post.getStatusLink());
            post.setSourceURL(post.getStatusLink());
            return post;
        }).collect(Collectors.toList());
        
        return posts;
    }

}
