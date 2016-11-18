package org.ksoong.weibo4j;

public class User extends Weibo {
    
    final static String USER_SHOW = API_BASE +  "/users/show.json";
    final static String USER_DOMAIN_SHOW = API_BASE +  "/users/domain_show.json";
    final static String USER_COUNTS = API_BASE +  "/users/counts.json";

    public User(String access_token) {
        super(access_token);
    }
    
    
    public String show(String uid) {
        return doGet(USER_SHOW, new Prameters(new String[]{UID}, new Object[]{uid}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/users/show
     * @param uid
     * @param screen_name
     * @return
     * @throws IOException
     */
    public String show(String uid, String screen_name) {
        return doGet(USER_SHOW, new Prameters(new String[]{UID, SCREEN_NAME}, new Object[]{uid, screen_name}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/users/domain_show
     * @param domain
     * @return
     * @throws IOException
     */
    public String domain_show(String domain) {
        return doGet(USER_DOMAIN_SHOW, new Prameters(new String[]{DOMAIN}, new Object[]{domain}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/users/counts
     * @param uids
     * @return
     * @throws IOException
     */
    public String counts(String uids) {
        return doGet(USER_COUNTS, new Prameters(new String[]{UIDS}, new Object[]{uids}));
    }

}
