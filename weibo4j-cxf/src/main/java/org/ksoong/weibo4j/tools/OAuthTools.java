package org.ksoong.weibo4j.tools;

import java.net.URI;
import java.util.Scanner;

import org.apache.cxf.rs.security.oauth2.client.Consumer;
import org.apache.cxf.rs.security.oauth2.client.OAuthClientUtils;
import org.ksoong.weibo4j.Oauth2;

public class OAuthTools {
    
    final static String WEIBO_AUTHORIZE = "https://api.weibo.com/oauth2/authorize";
    final static String WEIBO_ACCESS_TOKEN = "https://api.weibo.com/oauth2/access_token";
    
    public static String getInput(Scanner in, String message) throws Exception {
        return getInput(in, message, false); 
    }    
    
    public static String getInput(Scanner in, String message, boolean allowNull) throws Exception {
        while (true) {
            System.out.print(message);
            String input = in.nextLine();
            input = input.trim();
            if (input.length() > 1) {
                System.out.println("");
                return input;
            }
            
            if (allowNull) {
                return null;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);

        System.out.println("Select type of OAuth authentication");
        System.out.println("1) OAuth 2 - Weibo");
        System.out.println();
        
        String input = in.nextLine();
        input = input.trim();
        
        switch(Integer.parseInt(input)) {
        case 1:
            oauth2WeiboFlow(in);
            break;
        case 2:
            break;
        }
        in.close();
    }

    private static void oauth2WeiboFlow(Scanner in) throws Exception {

        Oauth2Authorize entity = oauth20Authorize(in, WEIBO_AUTHORIZE);
        String result = new Oauth2().access_token(entity.consumer.getClientId(), entity.consumer.getClientSecret(), entity.authCode, entity.callback);
        System.out.println(result);
        
    }
    
    private static Oauth2Authorize oauth20Authorize(Scanner in, String authorizeURL) throws Exception {
        System.out.println("=== OAuth 2.0 Workflow ===");
        System.out.println();

        String clientID = getInput(in, "Enter the Client ID = ");
        String clientSecret = getInput(in, "Enter the Client Secret = ");
        Consumer consumer = new Consumer(clientID, clientSecret);
        
        String scope = getInput(in, "Enter scope (hit enter for none) = ", true);
        
        String callback = getInput(in, "Enter callback URL (default: urn:ietf:wg:oauth:2.0:oob) = ", true);
        if (callback == null) {
            callback = "urn:ietf:wg:oauth:2.0:oob";
        }
        
        URI authenticateURL = OAuthClientUtils.getAuthorizationURI(authorizeURL, consumer.getClientId(), callback, "Auth URL", scope);
        
        System.out.println("Cut & Paste the URL in a web browser, and Authticate");
        System.out.println("Authorize URL  = " + authenticateURL.toASCIIString());
        System.out.println("");
        
        String authCode = getInput(in, "Enter Token Secret (Auth Code, Pin) from previous step = ");
        
       return new Oauth2Authorize(consumer, callback, authCode);
    }
    
    private static class Oauth2Authorize {
        private Consumer consumer;
        private String callback;
        private String authCode;
        
        public Oauth2Authorize(Consumer consumer, String callback, String authCode) {
            super();
            this.consumer = consumer;
            this.callback = callback;
            this.authCode = authCode;
        }

    }

}
