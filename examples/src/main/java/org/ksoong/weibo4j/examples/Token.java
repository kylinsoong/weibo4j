package org.ksoong.weibo4j.examples;

import org.ksoong.weibo4j.Weibo;
import org.ksoong.weibo4j.tools.PropertyIdentityTools;

public class Token {

    public static final String TOKEN = PropertyIdentityTools.loadValue(Weibo.ACCESS_TOKEN);
}
