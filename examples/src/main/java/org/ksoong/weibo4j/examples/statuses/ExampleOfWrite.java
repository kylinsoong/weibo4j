package org.ksoong.weibo4j.examples.statuses;

import org.ksoong.weibo4j.Statuses;
import org.ksoong.weibo4j.examples.Token;

public class ExampleOfWrite {

    public static void main(String[] args) {

        Statuses statuses = new Statuses(Token.TOKEN);
        
        String resp = "";
        
//        resp = statuses.repost("4043123077946303");
//        resp = statuses.repost("4043123077946303", "转发自 Publisher Robot", 3, "198.162.10.168");
//        resp = statuses.destroy("4043215240285560");
        resp = statuses.update("微博 API文档_V2: http://open.weibo.com/wiki/API%E6%96%87%E6%A1%A3_V2",0);
        
        System.out.println(resp);
    }

}
