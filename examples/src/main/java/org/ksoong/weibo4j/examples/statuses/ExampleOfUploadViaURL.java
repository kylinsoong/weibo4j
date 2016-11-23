package org.ksoong.weibo4j.examples.statuses;

import org.ksoong.weibo4j.Statuses;
import org.ksoong.weibo4j.examples.Token;

public class ExampleOfUploadViaURL {

    public static void main(String[] args) {
        
        Statuses statuses = new Statuses(Token.TOKEN);
        
        String resp = statuses.upload("Test Upload from URL", "http://ksoong.org/assets/blog/java/tree-avl-tree-example-9.png", "tree-avl-tree-example-9.png");
        
        System.out.println(resp);
    }

}
