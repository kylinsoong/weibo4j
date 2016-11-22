package org.ksoong.weibo4j.publisher;

import java.util.List;

/**
 * A parser implementation should have a zero-parameter construct and implement <code>IParser</code>.
 * 
 * Any sources(web pages, static files, data store, etc) can be parsed, which the parse result will generate a list of Weibo Post
 * @author kylin
 *
 */
public interface IParser {

    List<Post> parse() throws Exception ;
}
