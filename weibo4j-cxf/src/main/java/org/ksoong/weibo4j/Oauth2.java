package org.ksoong.weibo4j;

import org.ksoong.weibo4j.tools.PropertyIdentityTools;

public class Oauth2 extends Weibo {
    
    final static String OAUTH2 = "https://api.weibo.com/oauth2";
    final static String REVOKE = OAUTH2 + "/revokeoauth2";
    final static String GET_TOKEN_INFO = OAUTH2 + "/get_token_info";
    final static String AUTHORIZE = OAUTH2 + "/authorize";
    final static String ACCESS_TOKEN =  OAUTH2 + "/access_token";
    
    final static String CLIENT_ID = "client_id";
    final static String CLIENT_SECRET = "client_secret";
    final static String REDIRECT_URI = "redirect_uri";
    final static String CODE = "code";
    final static String GRANT_TYPE = "grant_type";

    public Oauth2() {
        super("empty");
    }
    
    public String revoke() {
        return doGet(REVOKE);
    }
    
    public String get_token_info() {
        return doPost(GET_TOKEN_INFO);
    }
    
    public String authorize() {
        return doGet(AUTHORIZE,  new Prameters(new String[]{CLIENT_ID, REDIRECT_URI}, new Object[]{PropertyIdentityTools.loadValue(CLIENT_ID), PropertyIdentityTools.loadValue(REDIRECT_URI)}));
    }
    
    public String authorize(String client_id, String redirect_uri) {
        return doGet(AUTHORIZE,  new Prameters(new String[]{CLIENT_ID, REDIRECT_URI}, new Object[]{client_id, redirect_uri}));
    }
    
    public String access_token(String client_id, String client_secret, String code, String redirect_uri) {
        return doPostOauth2(ACCESS_TOKEN,  new Prameters(new String[]{CLIENT_ID, CLIENT_SECRET, CODE, REDIRECT_URI, GRANT_TYPE}, new Object[]{client_id, client_secret, code, redirect_uri, "authorization_code"}));
    }

}
