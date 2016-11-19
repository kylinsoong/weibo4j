package org.ksoong.weibo4j.exceptions;

public class SecureIdentityException extends RuntimeException {

    private static final long serialVersionUID = 8847087824289288943L;
    
    public SecureIdentityException(Exception e) {
        super(e);
    }

}
