package org.ksoong.weibo4j.exceptions;

public class PropertyIdentityException extends RuntimeException {

    private static final long serialVersionUID = 7524888911583090359L;
    
    public PropertyIdentityException(String msg) {
        super(msg);
    }
    
    public PropertyIdentityException(Exception e) {
        super(e);
    }

}
