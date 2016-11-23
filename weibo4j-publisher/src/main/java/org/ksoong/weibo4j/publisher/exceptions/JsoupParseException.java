package org.ksoong.weibo4j.publisher.exceptions;

public class JsoupParseException extends RuntimeException {

    private static final long serialVersionUID = 8234660724409338085L;
    
    public JsoupParseException(Exception e) {
        super(e);
    }

}
