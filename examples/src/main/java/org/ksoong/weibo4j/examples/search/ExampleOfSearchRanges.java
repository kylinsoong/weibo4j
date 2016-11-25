package org.ksoong.weibo4j.examples.search;

import org.ksoong.weibo4j.Search;
import org.ksoong.weibo4j.examples.Token;

public class ExampleOfSearchRanges {

    public static void main(String[] args) {

        Search search = new Search(Token.TOKEN);
        
        String resp = search.suggestions_at_users("中国", 1);
        
        System.out.println(resp);
    }

}
