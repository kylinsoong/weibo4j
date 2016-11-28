package org.ksoong.weibo4j.publisher.parser;

import static org.ksoong.weibo4j.tools.SecureIdentityTools.decode;
import static org.ksoong.weibo4j.tools.PropertyIdentityTools.loadValue;
import static org.ksoong.weibo4j.tools.IOUtils.downloadImg;
import static org.ksoong.weibo4j.publisher.Post.Type.TWITTER_SEARCH;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.ksoong.weibo4j.publisher.IParser;
import org.ksoong.weibo4j.publisher.Post;
import org.ksoong.weibo4j.publisher.exceptions.TwitterSearchParserException;

import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.UserMentionEntity;
import twitter4j.auth.AccessToken;

/**
 * A parser to parse Twitter Search feeds
 * @author kylin
 *
 */
public class TwitterSearchParser implements IParser{
    
    Logger log = Logger.getLogger(TwitterSearchParser.class.getName());
    
    private final static String CONSUMER_KEY = "twitter.consumer.key";
    private final static String CONSUMER_SECRET = "twitter.consumer.secret";
    private final static String ACCESS_TOKEN = "twitter.access_token";
    private final static String ACCESS_TOKEN_SECRET = "twitter.access_token.secret";
    
    private final static String SEARCH_KEYWORDS = "twitter.search.keywrods";

    @Override
    public List<Post> parse() throws Exception {
        
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(decode(loadValue(CONSUMER_KEY)), decode(loadValue(CONSUMER_SECRET)));
        AccessToken accessToken = new AccessToken(decode(loadValue(ACCESS_TOKEN)), decode(loadValue(ACCESS_TOKEN_SECRET)));
        twitter.setOAuthAccessToken(accessToken);
        
        Query query = new Query(loadValue(SEARCH_KEYWORDS));
        QueryResult result = twitter.search(query);
        List<Status> tweets = result.getTweets();
        List<Post> posts = new ArrayList<>(3);
        for (Status tweet : tweets) {
            if(posts.size() >= 3) {
                break;
            }
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
                    for(URLEntity urlEntity : tweet.getURLEntities()){
                        if(txt.contains(urlEntity.getURL())){
                            txt = txt.replace(urlEntity.getURL(), urlEntity.getExpandedURL());
                        }
                    }
                    txt = rewriteTopics(txt);
                    Post post = new Post();
                    post.setTxt(txt);
                    post.setPic(mediaURL);
                    post.setType(TWITTER_SEARCH);
                    posts.add(post);
                    logPost(post);
                    break;
                }
            }
        }
        
        posts.forEach(p -> {
            Post post = p;
            post.setPic(generatePic(post.getPic()));
        });
        
        return posts;
    }
    
    /**
     * Twitter topic with format #Topic, but Weibo use format #Topic#
     * 
     * This method used to convert Twitter topic to Weibo topic
     * @param txt
     * @return
     */
    private String rewriteTopics(String sample) {
        
        if(!sample.contains("#")){
            return sample;
        }
        
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
        return txt;
    }

    private void logPost(Post post) {
        log.info("Twitter search '" + loadValue(SEARCH_KEYWORDS) + "' get post: " + post.getStatus() + ", picURL: " + post.getPic());
    }

    private String generatePic(String pic) {
        try {
            String name = pic.substring(pic.lastIndexOf(File.separator) + 1, pic.length());
            Path base = Paths.get(System.getProperty("java.io.tmpdir"), "twitter_search");
            if(!Files.exists(base)) {
                Files.createDirectory(base);
            }
            
            Path tmpFile = Paths.get(base.toString(), name);
            Files.deleteIfExists(tmpFile);
            Files.createFile(tmpFile);
            downloadImg(pic, tmpFile.toFile());
            return tmpFile.toString();
        } catch (IOException e) {
            throw new TwitterSearchParserException(e);
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
