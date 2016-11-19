package org.ksoong.weibo4j.examples.statuses;

import org.ksoong.weibo4j.Statuses;
import org.ksoong.weibo4j.examples.Token;

public class ExampleOfUpload {

    public static void main(String[] args) {

        Statuses statuses = new Statuses(Token.TOKEN);
        
        String resp = statuses.upload("WildFly Swarm Teiid - http://ksoong.org/slide/wildfly-swarm-teiid/#/", "/home/kylin/Pictures/wildfly-swarm-Uberjar.png");
        
        System.out.println(resp);
    }

}
