package org.ksoong.weibo4j.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    public static void download(String urlPath, File file) throws IOException {
        
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
}
