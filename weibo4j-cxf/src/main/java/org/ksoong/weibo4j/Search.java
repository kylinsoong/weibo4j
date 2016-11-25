package org.ksoong.weibo4j;

public class Search extends Weibo {
    
    final static String SUGGESTIONS_USERS =  API_BASE + "/search/suggestions/users.json";
    final static String SUGGESTIONS_SCHOOLS = API_BASE + "/search/suggestions/schools.json";
    final static String SUGGESTIONS_COMPANIES = API_BASE + "/search/suggestions/companies.json";
    final static String SUGGESTIONS_APPS = API_BASE + "/search/suggestions/apps.json";
    final static String SUGGESTIONS_AT_USERS = API_BASE + "/search/suggestions/at_users.json";
    
    final static String TOPICS = API_BASE + "/search/topics.json";
    
    public Search(String access_token) {
        super(access_token);
    }
    
    /**
     * http://open.weibo.com/wiki/2/search/suggestions/users
     * @param q
     * @return
     */
    public String suggestions_users(String q) {
        return doGet(SUGGESTIONS_USERS, new Prameters(new String[]{Q}, new Object[]{q}));
    }
    
    public String suggestions_users(String q, int count) {
        return doGet(SUGGESTIONS_USERS, new Prameters(new String[]{Q, COUNT}, new Object[]{q, count}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/search/suggestions/schools
     * @param q
     * @return
     */
    public String suggestions_schools(String q) {
        return doGet(SUGGESTIONS_SCHOOLS, new Prameters(new String[]{Q}, new Object[]{q}));
    }
    
    public String suggestions_schools(String q, int count) {
        return doGet(SUGGESTIONS_SCHOOLS, new Prameters(new String[]{Q, COUNT}, new Object[]{q, count}));
    }

    
    public String suggestions_schools(String q, int count, int type) {
        return doGet(SUGGESTIONS_SCHOOLS, new Prameters(new String[]{Q, COUNT, TYPE}, new Object[]{q, count, type}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/search/suggestions/companies
     * @param q
     * @return
     */
    public String suggestions_companies(String q) {
        return doGet(SUGGESTIONS_COMPANIES, new Prameters(new String[]{Q}, new Object[]{q}));
    }
    
    public String suggestions_companies(String q, int count) {
        return doGet(SUGGESTIONS_COMPANIES, new Prameters(new String[]{Q, COUNT}, new Object[]{q, count}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/search/suggestions/apps
     * @param q
     * @return
     */
    public String suggestions_apps(String q) {
        return doGet(SUGGESTIONS_APPS, new Prameters(new String[]{Q}, new Object[]{q}));
    }
    
    public String suggestions_apps(String q, int count) {
        return doGet(SUGGESTIONS_APPS, new Prameters(new String[]{Q, COUNT}, new Object[]{q, count}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/search/suggestions/at_users
     * @param q
     * @return
     */
    public String suggestions_at_users(String q, int type) {
        return doGet(SUGGESTIONS_AT_USERS, new Prameters(new String[]{Q, TYPE}, new Object[]{q, type}));
    }
    
    public String suggestions_at_users(String q, int type, int count) {
        return doGet(SUGGESTIONS_AT_USERS, new Prameters(new String[]{Q, TYPE, COUNT}, new Object[]{q, type, count}));
    }
    
    public String suggestions_at_users(String q, int type, int count, int range) {
        return doGet(SUGGESTIONS_AT_USERS, new Prameters(new String[]{Q, TYPE, COUNT, RANGE}, new Object[]{q, type, count, range}));
    }

    /**
     * http://open.weibo.com/wiki/2/search/topics
     * @param q
     * @return
     */
    public String topics(String q) {
        return doGet(TOPICS, new Prameters(new String[]{Q}, new Object[]{q}));
    }
    
    public String stopics(String q, int count) {
        return doGet(TOPICS, new Prameters(new String[]{Q, COUNT}, new Object[]{q, count}));
    }

    
    public String topics(String q, int count, int page) {
        return doGet(TOPICS, new Prameters(new String[]{Q, COUNT, PAGE}, new Object[]{q, count, page}));
    }

}
