package org.ksoong.weibo4j.examples.statuses;

import org.ksoong.weibo4j.Statuses;
import org.ksoong.weibo4j.examples.Token;

public class ExampleOfUpload {

    public static void main(String[] args) {

        Statuses statuses = new Statuses(Token.TOKEN);
        
        String resp = statuses.upload("Editorial: We need to harness the power of big data for medical progress https://t.co/nCKXWYGipY ", "/tmp/twitter_search/CyJYgaxWEAALsvU.JPG");
        
        System.out.println(resp);
    }

}
