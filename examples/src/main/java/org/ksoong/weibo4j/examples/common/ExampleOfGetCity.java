package org.ksoong.weibo4j.examples.common;

import java.io.IOException;

import org.ksoong.weibo4j.Common;
import org.ksoong.weibo4j.examples.Token;

public class ExampleOfGetCity {

    public static void main(String[] args) throws IOException {

        Common common = new Common(Token.TOKEN);
        System.out.println(common.get_city("001011"));
    }

}
