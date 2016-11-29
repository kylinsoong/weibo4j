package org.ksoong.weibo4j.examples.friendships;

import org.ksoong.weibo4j.Friendships;
import org.ksoong.weibo4j.examples.Token;

public class ExampleOfFriends {

    public static void main(String[] args) {

        Friendships test = new Friendships(Token.TOKEN);
        
//        System.out.println(test.friends("Data_virtualization"));
//        System.out.println(test.friends(5957842765l));
        
//        System.out.println(test.friends_in_common(5957842765l));
        
//        System.out.println(test.friends_bilateral(5957842765l));
        
//        System.out.println(test.friends_bilateral_ids(5957842765l));
     
//        System.out.println(test.friends_ids("Data_virtualization"));
//        System.out.println(test.friends_ids(5957842765l));
        
//        System.out.println(test.followers("Data_virtualization"));
        System.out.println(test.followers(5957842765l));
        
//        System.out.println(test.followers_ids("Data_virtualization"));
//        System.out.println(test.followers_ids(5957842765l));
        
//        System.out.println(test.followers_active(5957842765l));
        
//        System.out.println(test.friends_chain_followers(5957842765l));
        
//        System.out.println(test.show(5957842765l, 1803641581));
//        System.out.println(test.show("Data_virtualization", "kylinsoong"));
    }

}
