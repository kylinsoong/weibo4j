package org.ksoong.weibo4j.publisher;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.ksoong.weibo4j.Statuses;
import org.ksoong.weibo4j.Weibo;
import org.ksoong.weibo4j.publisher.Post.Img;
import org.ksoong.weibo4j.publisher.parser.KsoongArchivesParser;
import org.ksoong.weibo4j.tools.PropertyIdentityTools;

public class Publisher {
    
    static Logger log = Logger.getLogger(Publisher.class.getName());

    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {
        
        Statuses statuses = new Statuses(PropertyIdentityTools.loadValue(Weibo.ACCESS_TOKEN));
        
        List<Post> posts = new KsoongArchivesParser().parse();
        
        while(true) {
            int cur = new Random().nextInt(posts.size());
            Post p = posts.get(cur);
            String status = p.getStatus();
            Img img = p.getImg();
            if(img == null) {
                statuses.update(status);
            } else {
                String picUrl = img.getSrc();
                String picName = img.getName();
                statuses.upload(status, picUrl, picName);
            }
            logPost(p, img);
            Thread.currentThread().sleep(1000 * 60 * 25);
        }
        

    }

    private static void logPost(Post p, Img img) {
        StringBuffer sb = new StringBuffer();
        sb.append("Post URL: " + p.getSourceURL() + ", Souce content: " + p.getStatus());
        if(img != null){
            sb.append(", Img URL: " + img.getSrc());
        }
        log.info(sb.toString());
    }
}
