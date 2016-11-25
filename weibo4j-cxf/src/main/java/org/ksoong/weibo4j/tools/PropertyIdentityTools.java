package org.ksoong.weibo4j.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.ksoong.weibo4j.exceptions.PropertyIdentityException;

public class PropertyIdentityTools {
    
    public static String loadValue(String key) {
        return loadValue("weibo4j.properties", key);
    }
    
    public static String loadValue(String propName, String key) {
        try {
            Properties prop = loadProperties(propName);
            String value = prop.getProperty(key, null);
            if(value == null) {
                throw new PropertyIdentityException(key + " not exist in " + propName);
            } else {
                return value;
            }
        } catch (IOException e) {
            throw new PropertyIdentityException(e);
        }
    }

    public static Properties loadProperties(String name) throws IOException {
        Properties prop = new Properties();
        InputStream in = null;
        if(Files.exists(Paths.get(name))){
            in = new FileInputStream(Paths.get(name).toFile());
        } else {
            in = PropertyIdentityTools.class.getClassLoader().getResourceAsStream(name);
        }
        
        if(in == null) {
            throw new PropertyIdentityException(name + " not found");
        } else {
            try {
                prop.load(in);
            } finally {
                in.close();
            }
        }
        return prop;
    }
}
