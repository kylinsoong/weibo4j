package org.ksoong.weibo4j.examples.statuses;

import java.io.IOException;

import org.ksoong.weibo4j.Statuses;
import org.ksoong.weibo4j.examples.Token;

public class ExampleOfFriendsTimeline {

    public static void main(String[] args) throws IOException {

        Statuses statuses = new Statuses(Token.TOKEN);
        
//        System.out.println(statuses.friends_timeline());
//        System.out.println(statuses.friends_timeline(50, 1, 0, 0, 1));
        
//        System.out.println(statuses.show("4043128417852511"));
        
//        System.out.println(statuses.show_batch("4043128417852511"));
        
//        System.out.println(statuses.querymid("4043183459853164", 1));
        
//        System.out.println(statuses.queryid("4043183459853164", 1));
//        System.out.println(statuses.queryid("Ei7oJFlg0", 1, 0, 0, 1));
        
//        System.out.println(statuses.count("4043128417852511,3180105891,4043123077946303"));
        
//        System.out.println(statuses.emotions());
        System.out.println(statuses.emotions("ani"));
    }

}
