package org.ksoong.weibo4j.examples.show;

import java.io.IOException;

import org.ksoong.weibo4j.User;
import org.ksoong.weibo4j.examples.Token;

public class ExampleOfDomainShow {

    public static void main(String[] args) throws IOException {

        User user = new User(Token.TOKEN);
        System.out.println(user.domain_show("teiid"));
        System.out.println(user.domain_show("kylinsoong"));
    }

}
