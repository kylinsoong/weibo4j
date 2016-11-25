package org.ksoong.weibo4j.publisher;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.ksoong.weibo4j.Statuses;
import org.ksoong.weibo4j.Weibo;
import org.ksoong.weibo4j.publisher.Post.Img;
import org.ksoong.weibo4j.publisher.parser.KsoongArchivesParser;
import org.ksoong.weibo4j.publisher.parser.TwitterSearchParser;
import org.ksoong.weibo4j.tools.PropertyIdentityTools;

public class Publisher {
    
    static Logger log = Logger.getLogger(Publisher.class.getName());

    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {
        
        Statuses statuses = new Statuses(PropertyIdentityTools.loadValue(Weibo.ACCESS_TOKEN));
        
        TwitterSearchParser twitter = new TwitterSearchParser();
        List<Post> archives = new KsoongArchivesParser().parse();
        
        boolean isTweets= true;
        while(true) {
            if(isTweets) {
                isTweets = false;
                List<Post>  tweets = twitter.parse();
                tweets.forEach(p -> send(p, statuses));
            } else {
                isTweets= true;
                int cur = new Random().nextInt(archives.size());
                send(archives.get(cur), statuses);
            }
            
            Thread.currentThread().sleep(1000 * 60 * 25);
        }
        

    }

    private static Object send(Post post, Statuses statuses) {
        
        String status = post.getStatus();
        if(post.getPic() != null) {
            String picPath = post.getPic();
            statuses.upload(status, picPath);
        } else {
            Img img = post.getImg();
            if(img == null) {
                statuses.update(status);
            } else {
                String picUrl = img.getSrc();
                String picName = img.getName();
                statuses.upload(status, picUrl, picName);
            }
        }
        
        logPost(post);
        return null;
    }

    private static void logPost(Post p) {
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
        log.info(sb.toString());
    }
}
