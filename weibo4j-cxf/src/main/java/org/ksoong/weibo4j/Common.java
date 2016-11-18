package org.ksoong.weibo4j;

public class Common extends Weibo {
    
    final static String COMMON_CODE_TO_LOCATION = API_BASE + "/common/code_to_location.json";
    final static String COMMON_GET_CITY = API_BASE + "/common/get_city.json";
    final static String COMMON_GET_PROVINCE = API_BASE + "/common/get_province.json";
    final static String COMMON_GET_COUNTRY = API_BASE + "/common/get_country.json";
    final static String COMMON_GET_TIMEZONE = API_BASE + "/common/get_timezone.json";
    
    
    public Common(String access_token) {
        super(access_token);
    }
    
    /**
     * http://open.weibo.com/wiki/2/common/code_to_location
     * @param codes
     * @return
     * @throws IOException
     */
    public String code_to_location(String codes) {
        return doGet(COMMON_CODE_TO_LOCATION, new Prameters(new String[]{CODES}, new Object[]{codes}));
    }
    
    public String get_city(String province) {
        return doGet(COMMON_GET_CITY, new Prameters(new String[]{PROVINCE}, new Object[]{province}));
    }
    
    public String get_city(String province, String capital) {
        return doGet(COMMON_GET_CITY, new Prameters(new String[]{PROVINCE, CAPITAL}, new Object[]{province, capital}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/common/get_city
     * @param province
     * @param capital
     * @param language
     * @return
     * @throws IOException
     */
    public String get_city(String province, String capital, String language) {
        return doGet(COMMON_GET_CITY, new Prameters(new String[]{PROVINCE, CAPITAL, LANGUAGE}, new Object[]{province, capital, language}));
    }
    
    public String get_province(String country) {
        return doGet(COMMON_GET_PROVINCE, new Prameters(new String[]{COUNTRY}, new Object[]{country}));
    }
    
    public String get_province(String country, String language) {
        return doGet(COMMON_GET_PROVINCE, new Prameters(new String[]{COUNTRY, LANGUAGE}, new Object[]{country, language}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/common/get_province
     * @param country
     * @param capital
     * @param language
     * @return
     * @throws IOException
     */
    public String get_province(String country, String capital, String language) {
        return doGet(COMMON_GET_PROVINCE, new Prameters(new String[]{COUNTRY, LANGUAGE, CAPITAL}, new Object[]{country, language, capital}));
    }
    
    public String get_country() {
        return doGet(COMMON_GET_COUNTRY);
    }
    
    public String get_country(String language) {
        return doGet(COMMON_GET_COUNTRY, new Prameters(new String[]{LANGUAGE}, new Object[]{language}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/common/get_country
     * @param language
     * @param capital
     * @return
     * @throws IOException
     */
    public String get_country(String language, String capital) {
        return doGet(COMMON_GET_COUNTRY, new Prameters(new String[]{CAPITAL, LANGUAGE}, new Object[]{capital, language}));
    }
    
    public String getTimeZone() {
        return doGet(COMMON_GET_TIMEZONE);
    }
    
    /**
     * http://open.weibo.com/wiki/2/common/get_timezone
     * @param language
     * @return
     * @throws IOException
     */
    public String getTimeZone(String language) {
        return doGet(COMMON_GET_TIMEZONE, new Prameters(new String[]{LANGUAGE}, new Object[]{language}));
    }

}
