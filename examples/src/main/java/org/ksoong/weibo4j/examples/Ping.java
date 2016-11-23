package org.ksoong.weibo4j.examples;

import org.ksoong.weibo4j.Statuses;

public class Ping {

    public static void main(String[] args) {

        Statuses statuses = new Statuses(Token.TOKEN);
        statuses.update("PING message from weibo4j Publisher");
    }

}
