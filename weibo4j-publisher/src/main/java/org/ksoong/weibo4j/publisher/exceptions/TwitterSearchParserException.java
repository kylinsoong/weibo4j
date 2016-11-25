package org.ksoong.weibo4j.publisher.exceptions;

public class TwitterSearchParserException extends RuntimeException {

    private static final long serialVersionUID = -4044507761156097336L;
    
    public TwitterSearchParserException(Exception e) {
        super(e);
    }

}
