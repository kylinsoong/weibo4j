package org.ksoong.weibo4j;

import static org.ksoong.weibo4j.Weibo.*;
import static org.ksoong.weibo4j.tools.SecureIdentityTools.*;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

public class TestWebClient {

    @Test
    public void testQueryURL() {
        WebClient wc = WebClient.create("https://api.weibo.com/2/friendships/followers.json");
        wc.query(ACCESS_TOKEN, decode("-52ed5042b10eedc1d7c14267f5305ab1246d26e6405eb467b366fb607de848e5b731ea06889a5c61"));
        wc.query(UID, "5957842765");
        
        System.out.println(wc.getCurrentURI());
    }
}
