package org.ksoong.weibo4j;

import static org.ksoong.weibo4j.tools.SecureIdentityTools.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.ksoong.weibo4j.exceptions.ParseResultException;
import org.ksoong.weibo4j.exceptions.UoloadPicNotExistException;

public class Weibo {
    
    final static String PROVINCE = "province";
    final static String CAPITAL = "capital";
    final static String COUNT = "count";
    final static String CITY = "city";
    final static String AREA = "area";
    final static String TYPE = "type";
    final static String KEYWORD = "keyword";
    final static String CODES = "codes";
    final static String LANGUAGE = "language";
    final static String COUNTRY = "country";
    final static String ID = "id";
    final static String IDS = "ids";
    final static String MID = "mid";
    final static String UID = "uid";
    final static String UIDS = "uids";
    final static String SOURCE_ID = "source_id";
    final static String TARGET_ID = "target_id";
    final static String SCREEN_NAME = "screen_name";
    final static String SOURCE_SCREEN_NAME = "source_screen_name";
    final static String TARGET_SCREEN_NAME = "target_screen_name";
    final static String DOMAIN = "domain";
    final static String PAGE = "page";
    final static String BASE_APP = "base_app";
    final static String FEATURE = "feature";
    final static String TRIM_USER = "trim_user";
    final static String FILTER_BY_AUTHOR = "filter_by_author";
    final static String FILTER_BY_SOURCE = "filter_by_source";
    final static String FILTER_BY_TYPE = "filter_by_type";
    final static String IS_BATCH = "is_batch";
    final static String INBOX = "inbox";
    final static String ISBASE62 = "isBase62";
    final static String STATUS = "status";
    final static String IS_COMMENT = "is_comment";
    final static String RIP = "rip";
    final static String VISIBLE = "visible";
    final static String LIST_ID = "lisit_id";
    final static String LAT = "lat";
    final static String LONG = "long";
    final static String PIC = "pic";
    final static String ANNOTATIONS = "annotations";
    final static String Q = "q";
    final static String RANGE = "range";
    
    public final static String ACCESS_TOKEN = "access_token";
    public final static String API_BASE =  "https://api.weibo.com/2";

    protected String access_token;
    
    protected Weibo(String access_token) {
        this.access_token = access_token;
    }
    
    String access_token() {
        return decode(access_token);
    }
    
    String doPostMultipart(String api, String pic) {
        return doPostMultipart(api, new Prameters(new String[]{}, new Object[]{}), pic);
    }
    
    String doPostMultipart(String api, Prameters prams, String pic) {
        WebClient wc = WebClient.create(api);
        wc.type(MediaType.MULTIPART_FORM_DATA);
        
        List<Attachment> atts = new LinkedList<>();
        ContentDisposition tockencd = new ContentDisposition("form-data; name=\"access_token\";");
        atts.add(new Attachment(ACCESS_TOKEN, new ByteArrayInputStream(access_token().getBytes()), tockencd));
        
        String[] keys = prams.keys;
        for(int i = 0 ; i < keys.length ; i ++){
            ContentDisposition cd = new ContentDisposition("form-data; name=\"" + keys[i] + "\";");
            atts.add(new Attachment(keys[i], new ByteArrayInputStream(prams.value(i).toString().getBytes()), cd));
        }
        
        Path path = Paths.get(pic);
        String picName = path.toFile().getName();
        ContentDisposition cd = new ContentDisposition("form-data; name=\"" + PIC + "\";filename=\"" + picName + "\"");
        atts.add(new Attachment("pic", new ByteArrayInputStream(readBytesFromFile(path)), cd));
        Response resp= wc.post(new MultipartBody(atts));
        String result = "";
        try {
            result = IOUtils.toString((InputStream) resp.getEntity());
        } catch (IOException e) {
            throw new ParseResultException(e);
        }
        handleResponse(resp, wc);
        return result;
    }
    
    String doPostMultipart(String api, Prameters prams, String picURL, String picName) {
        WebClient wc = WebClient.create(api);
        wc.type(MediaType.MULTIPART_FORM_DATA);
        
        List<Attachment> atts = new LinkedList<>();
        ContentDisposition tockencd = new ContentDisposition("form-data; name=\"access_token\";");
        atts.add(new Attachment(ACCESS_TOKEN, new ByteArrayInputStream(access_token().getBytes()), tockencd));
        
        String[] keys = prams.keys;
        for(int i = 0 ; i < keys.length ; i ++){
            ContentDisposition cd = new ContentDisposition("form-data; name=\"" + keys[i] + "\";");
            atts.add(new Attachment(keys[i], new ByteArrayInputStream(prams.value(i).toString().getBytes()), cd));
        }
        
        ContentDisposition cd = new ContentDisposition("form-data; name=\"" + PIC + "\";filename=\"" + picName + "\"");
        atts.add(new Attachment("pic", new ByteArrayInputStream(readBytesFromURL(picURL)), cd));
        Response resp= wc.post(new MultipartBody(atts));
        String result = "";
        try {
            result = IOUtils.toString((InputStream) resp.getEntity());
        } catch (IOException e) {
            throw new ParseResultException(e);
        }
        handleResponse(resp, wc);
        return result;
    }

    private byte[] readBytesFromURL(String picURL) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        URL url = null;
        try {
            url = new URL(picURL);
        } catch (MalformedURLException e) {
            throw new UoloadPicNotExistException(e);
        }
        try(InputStream in = url.openStream()){
            byte[] byteChunk = new byte[4096];
            int n;
            while ( (n = in.read(byteChunk)) > 0 ) {
                baos.write(byteChunk, 0, n);
            }
        } catch (Exception e) {
            throw new UoloadPicNotExistException(e);
        }
        return baos.toByteArray();
    }

    private byte[] readBytesFromFile(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new UoloadPicNotExistException(e);
        }
    }

    String doPost(String api) {
        return doPost(api, new Prameters(new String[]{}, new Object[]{}));
    }
    
    String doPost(String api, Prameters prams) {
        WebClient wc = WebClient.create(api);
        Form form = new Form();
        form.param(ACCESS_TOKEN, access_token());
        String[] keys = prams.keys;
        for(int i = 0 ; i < keys.length ; i ++){
            form.param(keys[i], prams.value(i).toString());
        }
        Response resp = wc.form(form);
        String result = "";
        try {
            result = IOUtils.toString((InputStream) resp.getEntity());
        } catch (IOException e) {
            throw new ParseResultException(e);
        }
        handleResponse(resp, wc);
        return result;
    }
    
    String doPostOauth2(String api, Prameters prams) {
        WebClient wc = WebClient.create(api);
        Form form = new Form();
        String[] keys = prams.keys;
        for(int i = 0 ; i < keys.length ; i ++){
            form.param(keys[i], prams.value(i).toString());
        }
        Response resp = wc.form(form);
        String result = "";
        try {
            result = IOUtils.toString((InputStream) resp.getEntity());
        } catch (IOException e) {
            throw new ParseResultException(e);
        }
        handleResponse(resp, wc);
        return result;
    }
    
    String doGet(String api){
        return doGet(api, new Prameters(new String[]{}, new Object[]{}));
    }
    
    String doGet(String api, Prameters prams){
        WebClient wc = WebClient.create(api);
        wc.query(ACCESS_TOKEN, access_token());
        String[] keys = prams.keys;
        for(int i = 0 ; i < keys.length ; i ++){
            wc.query(keys[i], prams.value(i));
        }
        Response resp = wc.get();
        String result = "";
        try {
            result = IOUtils.toString((InputStream) resp.getEntity());
        } catch (IOException e) {
            throw new ParseResultException(e);
        }
        handleResponse(resp, wc);
        return result;
    }
    
    void handleResponse(Response resp, WebClient wc) {
        System.out.println(resp.getStatus() + " - " + resp.getStatusInfo().getReasonPhrase());
        wc.close();
    }
    
    public static class Prameters {
        
        final String[] keys;
        final Object[] values;
        
        public Prameters(String[] keys, Object[] values) {
            this.keys = keys;
            this.values = values;
        }
        
        public String[] keys() {
            return this.keys;
        }
        
        public Object value(int cursor) {
            return values[cursor];
        }
    }
}
