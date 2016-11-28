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
import org.ksoong.weibo4j.publisher.Post;
import org.ksoong.weibo4j.publisher.Post.Img;
import org.ksoong.weibo4j.tools.IOUtils;

import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.UserMentionEntity;
import twitter4j.auth.AccessToken;

public class TestTwitterSearchParse {
    
    static {
        System.setProperty("http.proxyHost", "squid.apac.redhat.com");
        System.setProperty("http.proxyPort", "3128");
    }
    
    @Test
    public void testTopicConvert() {
        String sample_1 = "What #BigData";
        String sample_2 = "What #BigData.";
        String sample_3 = "What #BigData,";
        String sample_4 = "What #BigData. ";
        String sample_5 = "What #BigData, ";
        String sample_6 = "What #BigData can tell us about";
        helpTest(sample_1);
        helpTest(sample_2);
        helpTest(sample_3);
        helpTest(sample_4);
        helpTest(sample_5);
        helpTest(sample_6);
    }
    
    private void helpTest(String sample) {

        String[] array = sample.split(" ");
        String[] newArray = new String[array.length];
        for(int i = 0 ; i < array.length ; i ++) {
            String item = array[i];
            if(item.startsWith("#")){
                if(item.endsWith(".") || item.endsWith(",") || item.endsWith("!") || item.endsWith("?")){
                    char symbol = item.charAt(item.length() -1);
                    item = item.substring(0, item.length() -1) + "#" + symbol;
                } else {
                    item = item + "#";
                }
            }
            newArray[i] = item;
        }
        
        String txt = "";
        for(int i = 0 ; i < newArray.length ; i ++){
            String item = newArray[i];
            txt += item;
            if(i != (newArray.length -1)) {
                txt += " ";
            }
        }
        
        assertTrue(txt.contains("#BigData#"));
    }

    @Ignore
    @Test
    public void testImgDownload() throws IOException {
        String urlPath = "http://pbs.twimg.com/media/CyCK3FeWIAE0RUS.jpg";
        Path file = Files.createFile(Paths.get("target", "CyCK3FeWIAE0RUS.jpg"));
        IOUtils.downloadImg(urlPath, file.toFile());
    }
    
    @Ignore
    @Test
    public void testParser() throws Exception {
        List<Post> posts = new TwitterSearchParser().parse();
        posts.forEach(p -> {
            StringBuffer sb = new StringBuffer();
            sb.append("Type: " + p.getType() + ", Souce content: " + p.getStatus());
            if(p.getPic() != null) {
                sb.append(", Img URL: " + p.getPic());
            } else {
                Img img = p.getImg();
                if(img != null){
                    sb.append(", Img URL: " + img.getSrc());
                }
            }
            System.out.println(sb.toString());
        });
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
                    if(tweet.getURLEntities().length > 0){
                        for(URLEntity urlEntity : tweet.getURLEntities()){
                            System.out.println(urlEntity.getDisplayURL());
                            System.out.println(urlEntity.getExpandedURL());
                            System.out.println(urlEntity.getURL());
                            if(txt.contains(urlEntity.getURL())){
                                txt = txt.replace(urlEntity.getURL(), urlEntity.getExpandedURL());
                            }
                        }
                    }
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
