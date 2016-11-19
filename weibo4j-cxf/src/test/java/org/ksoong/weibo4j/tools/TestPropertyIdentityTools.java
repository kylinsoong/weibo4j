package org.ksoong.weibo4j.tools;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.ksoong.weibo4j.Weibo;

public class TestPropertyIdentityTools {

    @Test
    public void testLoadValue(){
        String token = PropertyIdentityTools.loadValue(Weibo.ACCESS_TOKEN);
        assertEquals("76cecdeae6a44be40334530c40508c1b", token);
        token = SecureIdentityTools.decode(token);
        assertEquals("password1!", token);
    }
}
