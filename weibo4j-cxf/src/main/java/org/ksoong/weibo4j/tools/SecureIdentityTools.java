package org.ksoong.weibo4j.tools;

import java.math.BigInteger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.ksoong.weibo4j.exceptions.SecureIdentityException;

public class SecureIdentityTools {
    
    private final static String PHRASE = "Token on the way";
    private final static String ALGORITHM = "Blowfish";
    
    public static String encode(String secret) {
        
        byte[] kbytes = PHRASE.getBytes();
        SecretKeySpec key = new SecretKeySpec(kbytes, ALGORITHM);
        
        byte[] encoding = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encoding = cipher.doFinal(secret.getBytes());
        } catch (Exception e) {
            throw new SecureIdentityException(e);
        } 
        BigInteger n = new BigInteger(encoding);
        return n.toString(16);
    }
    
    public static String decode(String secret) {
        
        byte[] kbytes = PHRASE.getBytes();
        SecretKeySpec key = new SecretKeySpec(kbytes, ALGORITHM);
        
        BigInteger n = new BigInteger(secret, 16);
        byte[] encoding = n.toByteArray();
        
        if (encoding.length % 8 != 0) {
           int length = encoding.length;
           int newLength = ((length / 8) + 1) * 8;
           int pad = newLength - length; 
           byte[] old = encoding;
           encoding = new byte[newLength];
           for (int i = old.length - 1; i >= 0; i--) {
              encoding[i + pad] = old[i];
           }
           if (n.signum() == -1) {
              for (int i = 0; i < newLength - length; i++) {
                 encoding[i] = (byte) -1;
              }
           }
        }
        
        byte[] decode = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decode = cipher.doFinal(encoding);
        } catch (Exception e) {
            throw new SecureIdentityException(e);
        } 
        
        return new String(decode);
    }

    public static void main(String[] args) {
        String encode = encode(args[0]);
        System.out.println("Encoded password: " + encode);
    }

}
