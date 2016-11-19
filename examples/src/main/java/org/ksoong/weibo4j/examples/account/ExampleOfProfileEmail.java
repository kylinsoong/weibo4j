package org.ksoong.weibo4j.examples.account;

import java.io.IOException;

import org.ksoong.weibo4j.Account;
import org.ksoong.weibo4j.examples.Token;

public class ExampleOfProfileEmail {

    public static void main(String[] args) throws IOException {

        Account account = new Account(Token.TOKEN);
        System.out.println(account.profile_email());
    }

}
