package org.ksoong.weibo4j.publisher;

import java.util.List;

import org.ksoong.weibo4j.Statuses;
import org.ksoong.weibo4j.Weibo;
import org.ksoong.weibo4j.publisher.Post.Img;
import org.ksoong.weibo4j.publisher.parser.KsoongArchivesParser;
import org.ksoong.weibo4j.tools.PropertyIdentityTools;

public class Publisher {

    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {
        
        Statuses statuses = new Statuses(PropertyIdentityTools.loadValue(Weibo.ACCESS_TOKEN));
        
        List<Post> posts = new KsoongArchivesParser().parse();
        
        posts.forEach(p -> {
            String status = p.getStatus();
            Img img = p.getImg();
            if(img == null) {
                statuses.update(status);
            } else {
                String picUrl = img.getSrc();
                String picName = img.getName();
                statuses.upload(status, picUrl, picName);
            }
            try {
                Thread.currentThread().sleep(1000 * 60 * 30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
