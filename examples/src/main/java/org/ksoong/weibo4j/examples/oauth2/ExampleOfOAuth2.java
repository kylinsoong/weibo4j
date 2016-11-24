package org.ksoong.weibo4j.examples.oauth2;

import org.ksoong.weibo4j.tools.SecureIdentityTools;


public class ExampleOfOAuth2 {

    public static void main(String[] args) {
        
        System.out.println(SecureIdentityTools.encode("07320089dcb826a19a135be2fb1b70ea"));
        
//        Oauth2 oauth2 = new Oauth2(Token.TOKEN);
//        oauth2.authorize(PropertyIdentityTools.loadValue("client_id"), PropertyIdentityTools.loadValue("redirect_uri"));
//        String resp = oauth2.access_token(loadValue("client_id"), loadValue("client_secret"), loadValue("code"), loadValue("redirect_uri"));
//        oauth2.revoke();
        
//        System.out.println(resp);
    }

}
