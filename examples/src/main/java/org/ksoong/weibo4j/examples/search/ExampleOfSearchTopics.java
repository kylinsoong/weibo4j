package org.ksoong.weibo4j.examples.search;

import org.ksoong.weibo4j.Search;
import org.ksoong.weibo4j.examples.Token;

public class ExampleOfSearchTopics {

    public static void main(String[] args) {

        Search search = new Search(Token.TOKEN);
        
        String resp = search.topics("Big Data");
        
        System.out.println(resp);
    }

}
