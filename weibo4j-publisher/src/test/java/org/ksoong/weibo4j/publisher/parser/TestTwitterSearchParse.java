package org.ksoong.weibo4j.publisher.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.ksoong.weibo4j.tools.IOUtils;

import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.UserMentionEntity;
import twitter4j.auth.AccessToken;

public class TestTwitterSearchParse {
    
    static {
        System.setProperty("http.proxyHost", "squid.apac.redhat.com");
        System.setProperty("http.proxyPort", "3128");
    }
    
    @Test
    public void testCreateTempFile() throws IOException {
        String pic = "http://pbs.twimg.com/media/CyFu8KAUUAAC0ZW.jpg";
        String name = pic.substring(pic.lastIndexOf(File.separator) + 1, pic.length());
        Path base = Paths.get(System.getProperty("java.io.tmpdir"), "twitter_search");
        if(!Files.exists(base)) {
            Files.createDirectory(base);
        }
        
        Path tmpFile = Paths.get(base.toString(), name);
        Files.deleteIfExists(tmpFile);
        Files.createFile(tmpFile);
        assertTrue(Files.exists(tmpFile));
        assertEquals(0, tmpFile.toFile().length());
        assertEquals("/tmp/twitter_search/CyFu8KAUUAAC0ZW.jpg", tmpFile.toString());
        assertEquals("CyFu8KAUUAAC0ZW.jpg", tmpFile.toFile().getName());
//        IOUtils.download(pic, tmpFile.toFile());
//        assertTrue(tmpFile.toFile().length() > 0);
        System.out.println(tmpFile);
    }

    @Ignore
    @Test
    public void testGrabing() throws TwitterException {
        Twitter twitter = new TwitterFactory().getInstance();
        AccessToken accessToken = new AccessToken("209490245-KrmA9T6OfdI3syd5ryJl9jwJJumvhK69Cnxnv1Vr", "CJlREMM8dUwV3h6A8I0zw2MFFuUWBqjeWEfyXERIdYf6b");
        twitter.setOAuthConsumer("ieNoRaTmSTzos8fvDxQ9CyEJr", "GC2f6ugQCiR3RSP5VkUGbqo9lPQw984YXBxNLZrPTOuaNhpM8G");
        twitter.setOAuthAccessToken(accessToken);
        
        Query query = new Query("Big Data");
        QueryResult result;
        result = twitter.search(query);
        List<Status> tweets = result.getTweets();
        System.out.println(tweets.size());
        for (Status tweet : tweets) {
            
            if(tweet.getMediaEntities().length > 0 && tweet.getLang().equals("en")){
                for(MediaEntity media : tweet.getMediaEntities()){
                    if(!media.getType().equals("photo")){
                        continue;
                    }
                    String mediaURL = media.getMediaURL();
                    String txt = tweet.getText();
                    txt = txt.replace(media.getText(), "");
                    if(tweet.getUserMentionEntities().length > 0){
                        txt = getOriginalTweet(tweet.getUserMentionEntities(), txt);
                    }
                    
                    System.out.println(mediaURL);
                    System.out.println(txt);
                    break;
                }
            }
        }
    }

    private String getOriginalTweet(UserMentionEntity[] userMentionEntities, String txt) {
        for(UserMentionEntity entity : userMentionEntities) {
            String startMention = "RT @" + entity.getText() + ": ";
            if(txt.contains(startMention)) {
                txt = txt.replace(startMention, "");
            } else {
                txt = txt.replace("@" + entity.getText(), entity.getText());
            }
        }
        return txt;
    }
}
