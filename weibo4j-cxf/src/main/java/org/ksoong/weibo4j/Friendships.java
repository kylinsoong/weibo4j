package org.ksoong.weibo4j;

public class Friendships extends Weibo {
    
    final static String FRIENDS = API_BASE + "/friendships/friends.json";
    final static String FRIENDS_IN_COMMON = API_BASE + "/friendships/friends/in_common.json";
    final static String FRIENDS_BILATERAL = API_BASE + "/friendships/friends/bilateral.json";
    final static String FRIENDS_BILATERAL_IDS = API_BASE + "/friendships/friends/bilateral/ids.json";
    final static String FRIENDS_IDS = API_BASE + "/friendships/friends/ids.json";
    
    final static String FOLLOWERS = API_BASE + "/friendships/followers.json";
    final static String FOLLOWERS_IDS = API_BASE + "/friendships/followers/ids.json";
    final static String FOLLOWERS_ACTIVE = API_BASE + "/friendships/followers/active.json"; 
    final static String FRIENDS_CHAIN_FOLLOWERS = API_BASE + "/friendships/friends_chain/followers.json";
    final static String SHOW = API_BASE + "/friendships/show.json"; 
    
    final static String CREATE = API_BASE + "/friendships/create.json";  
    final static String DESTORY = API_BASE + "/friendships/destroy.json";  


    public Friendships(String access_token) {
        super(access_token);
    }
    
    public String destroy(String screen_name) {
        return doPost(DESTORY, new Prameters(new String[]{SCREEN_NAME}, new Object[]{screen_name}));
    }
    
    public String destroy(long uid) {
        return doPost(DESTORY, new Prameters(new String[]{UID}, new Object[]{uid}));
    }
    
    public String create(String screen_name) {
        return doPost(CREATE, new Prameters(new String[]{SCREEN_NAME}, new Object[]{screen_name}));
    }
    
    public String create(long uid) {
        return doPost(CREATE, new Prameters(new String[]{UID}, new Object[]{uid}));
    }
    
    public String show(long source_id, long target_id) {
        return doGet(SHOW, new Prameters(new String[]{SOURCE_ID, TARGET_ID}, new Object[]{source_id, target_id}));
    }
    
    public String show(String source_screen_name, String target_screen_name) {
        return doGet(SHOW, new Prameters(new String[]{SOURCE_SCREEN_NAME, TARGET_SCREEN_NAME}, new Object[]{source_screen_name, target_screen_name}));
    }
    
    public String friends_chain_followers(long uid) {
        return doGet(FRIENDS_CHAIN_FOLLOWERS, new Prameters(new String[]{UID}, new Object[]{uid}));
    }
    
    public String friends_chain_followers(long uid, int count, int page) {
        return doGet(FRIENDS_CHAIN_FOLLOWERS, new Prameters(new String[]{UID, COUNT, PAGE}, new Object[]{uid, count, page}));
    }
    
    public String followers_active(long uid) {
        return doGet(FOLLOWERS_ACTIVE, new Prameters(new String[]{UID}, new Object[]{uid}));
    }
    
    public String followers_ids(String screen_name) {
        return doGet(FOLLOWERS_IDS, new Prameters(new String[]{SCREEN_NAME}, new Object[]{screen_name}));
    }
    
    public String followers_ids(long uid) {
        return doGet(FOLLOWERS_IDS, new Prameters(new String[]{UID}, new Object[]{uid}));
    }
    
    public String followers(String screen_name) {
        return doGet(FOLLOWERS, new Prameters(new String[]{SCREEN_NAME}, new Object[]{screen_name}));
    }
    
    public String followers(long uid) {
        return doGet(FOLLOWERS, new Prameters(new String[]{UID}, new Object[]{uid}));
    }
    
    public String friends_ids(String screen_name) {
        return doGet(FRIENDS_IDS, new Prameters(new String[]{SCREEN_NAME}, new Object[]{screen_name}));
    }
    
    public String friends_ids(long uid) {
        return doGet(FRIENDS_IDS, new Prameters(new String[]{UID}, new Object[]{uid}));
    }
    
    public String friends_bilateral_ids(long uid) {
        return doGet(FRIENDS_BILATERAL_IDS, new Prameters(new String[]{UID}, new Object[]{uid}));
    }
    
    public String friends_bilateral(long uid) {
        return doGet(FRIENDS_BILATERAL, new Prameters(new String[]{UID}, new Object[]{uid}));
    }
    
    public String friends_in_common(long uid) {
        return doGet(FRIENDS_IN_COMMON, new Prameters(new String[]{UID}, new Object[]{uid}));
    }
    
    public String friends(String screen_name) {
        return doGet(FRIENDS, new Prameters(new String[]{SCREEN_NAME}, new Object[]{screen_name}));
    }
    
    public String friends(long uid) {
        return doGet(FRIENDS, new Prameters(new String[]{UID}, new Object[]{uid}));
    }

}
