package org.ksoong.weibo4j.tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestSecureIdentityTools {

    @Test
    public void testEncode() {
        String pass = "password1!";
        String encode = SecureIdentityTools.encode(pass);
        assertNotNull(encode);
    }
    
    @Test
    public void testdecode() {
        String pass = "password1!";
        String encode = SecureIdentityTools.encode(pass);
        String decode = SecureIdentityTools.decode(encode);
        assertEquals(pass, decode);
    }
    
    @Test
    public void testMain() {
        String pass = "password1!";
        SecureIdentityTools.main(new String[]{pass});
    }
}
