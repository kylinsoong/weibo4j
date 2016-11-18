package org.ksoong.weibo4j;

/**
 * http://open.weibo.com/wiki/2/account/profile/school_list
 * http://open.weibo.com/wiki/2/account/rate_limit_status
 * @author kylin
 *
 */
public class Account extends Weibo {
    
    final static String ACCOUNT_PROFILE_SCHOOL_LIST = API_BASE + "/account/profile/school_list.json";
    final static String ACCOUNT_RATE_LIMIT_STATUS = API_BASE + "/account/rate_limit_status.json";
    final static String ACCOUNT_PROFILE_EMAIL = API_BASE + "/account/profile/email.json";
    final static String ACCOUNT_GET_UID = API_BASE + "/account/get_uid.json";
    
    public Account(String access_token) {
        super(access_token);
    }
    
    public String profile_school_list(int province, String capital) {
        return doGet(ACCOUNT_PROFILE_SCHOOL_LIST, new Prameters(new String[]{PROVINCE, CAPITAL}, new Object[]{province, capital}));     
    }
    
    public String profile_school_list(int province, String capital, int type) {
        return doGet(ACCOUNT_PROFILE_SCHOOL_LIST, new Prameters(new String[]{PROVINCE, CAPITAL, TYPE}, new Object[]{province, capital, type}));         
    }
    
    public String profile_school_list(String keyword) {
        return doGet(ACCOUNT_PROFILE_SCHOOL_LIST, new Prameters(new String[]{KEYWORD}, new Object[]{keyword}));   
    }
    
    public String profile_school_list(String keyword, int count) {
        return doGet(ACCOUNT_PROFILE_SCHOOL_LIST, new Prameters(new String[]{KEYWORD, COUNT}, new Object[]{keyword, count}));  
    }
    
    public String profile_school_list(String keyword, int province, int count) {
        return doGet(ACCOUNT_PROFILE_SCHOOL_LIST, new Prameters(new String[]{KEYWORD, PROVINCE, COUNT}, new Object[]{keyword, province, count}));  
    }
    
    public String profile_school_list(String keyword, int province, int city, int count) {
        return doGet(ACCOUNT_PROFILE_SCHOOL_LIST, new Prameters(new String[]{KEYWORD, PROVINCE, CITY, COUNT}, new Object[]{keyword, province, city, count}));  
    }
    
    public String profile_school_list(String keyword, int province, int city, int area, int count) {
        return doGet(ACCOUNT_PROFILE_SCHOOL_LIST, new Prameters(new String[]{KEYWORD, PROVINCE, CITY, AREA, COUNT}, new Object[]{keyword, province, city, area, count}));   
    }
    
    public String profile_school_list(String keyword, int province, int city, int area, int type, int count) {
        return doGet(ACCOUNT_PROFILE_SCHOOL_LIST, new Prameters(new String[]{KEYWORD, PROVINCE, CITY, AREA, TYPE, COUNT}, new Object[]{keyword, province, city, area, type, count}));   
    }
    
    /**
     * http://open.weibo.com/wiki/2/account/profile/school_list
     * @param keyword
     * @param province
     * @param city
     * @param area
     * @param type
     * @param capital
     * @param count
     * @return
     * @throws IOException
     */
    public String profile_school_list(String keyword, int province, int city, int area, int type, String capital, int count) {
        return doGet(ACCOUNT_PROFILE_SCHOOL_LIST, new Prameters(new String[]{KEYWORD, PROVINCE, CITY, AREA, TYPE, CAPITAL, COUNT}, new Object[]{keyword, province, city, area, type, capital, count}));  
    }

    /**
     * http://open.weibo.com/wiki/2/account/rate_limit_status
     * @return
     * @throws IOException
     */
    public String rate_limit_status() {
        return doGet(ACCOUNT_RATE_LIMIT_STATUS);
    }
    
    /**
     * http://open.weibo.com/wiki/2/account/profile/email
     * @return
     * @throws IOException
     * TODO
     */
    public String profile_email() {
        return doGet(ACCOUNT_PROFILE_EMAIL);
    }
    
    /**
     * http://open.weibo.com/wiki/2/account/get_uid
     * @return
     * @throws IOException
     */
    public String get_uid() {
        return doGet(ACCOUNT_GET_UID);
    }
}
