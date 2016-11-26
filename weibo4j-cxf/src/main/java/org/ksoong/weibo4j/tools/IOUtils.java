package org.ksoong.weibo4j.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class IOUtils {

    /**
     * Download a remote http file
     * @param urlPath
     * @param file
     * @throws IOException
     */
    public static void downloadFile(String urlPath, File file) throws IOException {
        
        URL url =   new URL(urlPath);
        URLConnection conn = url.openConnection();
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                ){
            String inputLine;
            while ((inputLine = br.readLine()) != null){
                bw.write(inputLine);
            }
        }
    }
    
    public static void downloadImg(String urlPath, File file) throws IOException {
        
        URL url =   new URL(urlPath);
        try(
                InputStream in = new BufferedInputStream(url.openStream());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                FileOutputStream fos = new FileOutputStream(file);
                ){
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))){
                out.write(buf, 0, n);
            }
            byte[] bytes = out.toByteArray();
            fos.write(bytes);
        }
    }
}
