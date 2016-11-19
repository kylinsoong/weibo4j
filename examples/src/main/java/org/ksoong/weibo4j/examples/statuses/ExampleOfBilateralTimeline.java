package org.ksoong.weibo4j.examples.statuses;

import org.ksoong.weibo4j.Statuses;
import org.ksoong.weibo4j.examples.Token;

public class ExampleOfBilateralTimeline {

    public static void main(String[] args) {

        Statuses statuses = new Statuses(Token.TOKEN);
        
        System.out.println(statuses.bilateral_timeline());
        System.out.println(statuses.bilateral_timeline(20, 1, 0, 0, 0));
    }

}
