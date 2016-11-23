package org.ksoong.weibo4j;

public class Statuses extends Weibo {
    
    final static String PUBLIC_TIMELINE = API_BASE + "/statuses/public_timeline.json";
    final static String FRIENDS_TIMELINE = API_BASE + "/statuses/friends_timeline.json";
    final static String HOME_TIMELINE = API_BASE + "/statuses/home_timeline.json";
    final static String FRIENDS_TIMELINE_IDS = API_BASE + "/statuses/friends_timeline/ids.json";
    final static String USER_TIMELINE = API_BASE + "/statuses/user_timeline.json";
    final static String USER_TIMELINE_IDS = API_BASE + "/statuses/user_timeline/ids.json";
    final static String TIMELINE_BATCH = API_BASE + "/statuses/timeline_batch.json";
    final static String REPOST_TIMELINE = API_BASE + "/statuses/repost_timeline.json";
    final static String REPOST_TIMELINE_IDS = API_BASE + "/statuses/repost_timeline/ids.json";
    final static String MENTIONS = API_BASE + "/statuses/mentions.json";
    final static String MENTIONS_IDS = API_BASE + "/statuses/mentions/ids.json";
    final static String BILATERAL_TIMELINE = API_BASE + "/statuses/bilateral_timeline.json";
    final static String SHOW = API_BASE + "/statuses/show.json";
    final static String SHOW_BATCH = API_BASE + "/statuses/show_batch.json";
    final static String QUERYMID = API_BASE + "/statuses/querymid.json";
    final static String QUERYID = API_BASE + "/statuses/queryid.json";
    final static String COUNT = API_BASE + "/statuses/count.json";
    final static String GO = API_BASE + "/statuses/go";
    final static String EMOTIONS = API_BASE + "/emotions.json";
    
    final static String REPOST = API_BASE + "/statuses/repost.json";
    final static String DESTORY = API_BASE + "/statuses/destroy.json";
    final static String UPDATE = API_BASE + "/statuses/update.json";
    final static String UPLOAD = API_BASE + "/statuses/upload.json";
    
    public Statuses(String access_token) {
        super(access_token);
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/upload
     * @param status
     * @return
     */
    public String upload(String status, String picPath) {
        return doPostMultipart(UPLOAD, new Prameters(new String[]{STATUS}, new Object[]{status}), picPath);
    }
    
    public String upload(String status, String picPath, int visible) {
        return doPostMultipart(UPLOAD, new Prameters(new String[]{STATUS, VISIBLE}, new Object[]{status, visible}), picPath);
    }
    
    public String upload(String status, String picPath, int visible, String list_id, String lat, String longs, String annotations, String rip) {
        return doPostMultipart(UPLOAD, new Prameters(new String[]{STATUS, VISIBLE, LIST_ID, LAT, LONG, ANNOTATIONS, RIP}, new Object[]{status, visible, list_id, lat, longs, annotations, rip}), picPath);
    }
    
    public String upload(String status, String picUrl, String picName) {
        return doPostMultipart(UPLOAD, new Prameters(new String[]{STATUS}, new Object[]{status}), picUrl, picName);
    }
    
    public String upload(String status, String picUrl, String picName, int visible) {
        return doPostMultipart(UPLOAD, new Prameters(new String[]{STATUS, VISIBLE}, new Object[]{status, visible}), picUrl, picName);
    }
    
    public String upload(String status, String picUrl, String picName, int visible, String list_id, String lat, String longs, String annotations, String rip) {
        return doPostMultipart(UPLOAD, new Prameters(new String[]{STATUS, VISIBLE, LIST_ID, LAT, LONG, ANNOTATIONS, RIP}, new Object[]{status, visible, list_id, lat, longs, annotations, rip}), picUrl, picName);
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/update
     * @param status
     * @return
     */
    public String update(String status) {
        return doPost(UPDATE, new Prameters(new String[]{STATUS}, new Object[]{status}));
    }
    
    public String update(String status, int visible) {
        return doPost(UPDATE, new Prameters(new String[]{STATUS, VISIBLE}, new Object[]{status, visible}));
    }
    
    public String update(String status, int visible, String list_id, String lat, String longs, String annotations, String rip) {
        return doPost(UPDATE, new Prameters(new String[]{STATUS, VISIBLE, LIST_ID, LAT, LONG, ANNOTATIONS, RIP}, new Object[]{status, visible, list_id, lat, longs, annotations, rip}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/destroy
     * @param id
     */
    public String destroy(String id) {
        return doPost(DESTORY, new Prameters(new String[]{ID}, new Object[]{id}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/repost
     * @param id
     * @return
     */
    public String repost(String id){
        return doPost(REPOST, new Prameters(new String[]{ID}, new Object[]{id}));
    }
    
    public String repost(String id, String status){
        return doPost(REPOST, new Prameters(new String[]{ID, STATUS}, new Object[]{id, status}));
    }
    
    public String repost(String id, String status, int is_comment){
        return doPost(REPOST, new Prameters(new String[]{ID, STATUS, IS_COMMENT}, new Object[]{id, status, is_comment}));
    }
    
    public String repost(String id, String status, int is_comment, String rip){
        return doPost(REPOST, new Prameters(new String[]{ID, STATUS, IS_COMMENT, RIP}, new Object[]{id, status, is_comment, rip}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/emotions
     * @return
     */
    public String emotions() {
        return doGet(EMOTIONS);
    }
    
    public String emotions(String type) {
        return doGet(EMOTIONS, new Prameters(new String[]{TYPE}, new Object[]{type}));
    }
    
    public String emotions(String type, String language) {
        return doGet(EMOTIONS, new Prameters(new String[]{TYPE, LANGUAGE}, new Object[]{type, language}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/go
     * @param uid
     * @param id
     * @return
     */
    public String go(String uid, String id){
        return doGet(GO, new Prameters(new String[]{UID, ID}, new Object[]{uid, id}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/count
     * @param ids
     * @return
     */
    public String count(String ids) {
        return doGet(COUNT, new Prameters(new String[]{IDS}, new Object[]{ids}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/queryid
     * @param id
     * @param type
     * @return
     */
    public String queryid(String mid, int type) {
        return doGet(QUERYID, new Prameters(new String[]{MID, TYPE}, new Object[]{mid, type}));
    }
    
    public String queryid(String mid, int type, int is_batch, int inbox, int isBase62) {
        return doGet(QUERYID, new Prameters(new String[]{MID, TYPE, IS_BATCH, INBOX, ISBASE62}, new Object[]{mid, type, is_batch, inbox, isBase62}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/querymid
     * @param id
     * @return
     */
    public String querymid(String id, int type) {
        return doGet(QUERYMID, new Prameters(new String[]{ID, TYPE}, new Object[]{id, type}));
    }
    
    public String querymid(String id, int type, int is_batch) {
        return doGet(QUERYMID, new Prameters(new String[]{ID, TYPE, IS_BATCH}, new Object[]{id, type, is_batch}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/show_batch
     * @param ids
     * @return
     * TODO
     */
    public String show_batch(String ids){
        return doGet(SHOW_BATCH, new Prameters(new String[]{IDS}, new Object[]{ids}));
    }
    
    public String show_batch(String ids, int trim_user){
        return doGet(SHOW_BATCH, new Prameters(new String[]{IDS, TRIM_USER}, new Object[]{ids, trim_user}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/show
     * @param id
     * @return
     */
    public String show(String id) {
        return doGet(SHOW, new Prameters(new String[]{ID}, new Object[]{id}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/bilateral_timeline
     * @return
     */
    public String bilateral_timeline() {
        return doGet(BILATERAL_TIMELINE);
    }
    
    public String bilateral_timeline(int count, int page, int base_app, int feature, int trim_user) {
        return doGet(BILATERAL_TIMELINE, new Prameters(new String[]{COUNT, PAGE, BASE_APP, FEATURE, TRIM_USER}, new Object[]{count, page, base_app, feature, trim_user}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/mentions/ids
     * @return
     * @throws IOException
     */
    public String mentions_ids() {
        return doGet(MENTIONS_IDS);
    }
    
    public String mentions_ids(int count, int page, int filter_by_author, int filter_by_source, int filter_by_type) {
        return doGet(MENTIONS_IDS, new Prameters(new String[]{COUNT, PAGE, FILTER_BY_AUTHOR, FILTER_BY_SOURCE, FILTER_BY_TYPE}, new Object[]{count, page, filter_by_author, filter_by_source, filter_by_type}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/mentions
     * @return
     * @throws IOException
     */
    public String mentions() {
        return doGet(MENTIONS);
    }
    
    public String mentions(int count, int page, int filter_by_author, int filter_by_source, int filter_by_type) {
        return doGet(MENTIONS, new Prameters(new String[]{COUNT, PAGE, FILTER_BY_AUTHOR, FILTER_BY_SOURCE, FILTER_BY_TYPE}, new Object[]{count, page, filter_by_author, filter_by_source, filter_by_type}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/repost_timeline/ids
     * @param id
     * @return
     * @throws IOException
     */
    public String repost_timeline_ids(String id) {
        return doGet(REPOST_TIMELINE_IDS, new Prameters(new String[]{ID}, new Object[]{id}));
    }
    
    public String repost_timeline_ids(String id, int count, int page, int filter_by_author) {
        return doGet(REPOST_TIMELINE_IDS, new Prameters(new String[]{ID, COUNT, PAGE, FILTER_BY_AUTHOR}, new Object[]{id, count, page, filter_by_author}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/repost_timeline
     * @param id
     * @return
     * @throws IOException
     */
    public String repost_timeline(String id) {
        return doGet(REPOST_TIMELINE, new Prameters(new String[]{ID}, new Object[]{id}));
    }
    
    public String repost_timeline(String id, int count, int page, int filter_by_author) {
        return doGet(REPOST_TIMELINE, new Prameters(new String[]{ID, COUNT, PAGE, FILTER_BY_AUTHOR}, new Object[]{id, count, page, filter_by_author}));
    }
    
    
    /**
     * http://open.weibo.com/wiki/2/statuses/timeline_batch
     * @param uids
     * @return
     * @throws IOException
     * TODO
     */
    public String timeline_batch(String uids) {
        return doGet(TIMELINE_BATCH, new Prameters(new String[]{UIDS}, new Object[]{uids}));
    }
    
    public String timeline_batch(String uids, int count) {
        return doGet(TIMELINE_BATCH, new Prameters(new String[]{UIDS, COUNT}, new Object[]{uids, count}));
    }
    
    public String timeline_batch(String uids, int count, int page) {
        return doGet(TIMELINE_BATCH, new Prameters(new String[]{UIDS, COUNT, PAGE}, new Object[]{uids, count, page}));
    }
    
    public String timeline_batch(String uids, int count, int page, int base_app) {
        return doGet(TIMELINE_BATCH, new Prameters(new String[]{UIDS, COUNT, PAGE, BASE_APP}, new Object[]{uids, count, page, base_app}));
    }
    
    public String timeline_batch(String uids, int count, int page, int base_app, int feature) {
        return doGet(TIMELINE_BATCH, new Prameters(new String[]{UIDS, COUNT, PAGE, BASE_APP, FEATURE}, new Object[]{uids, count, page, base_app, feature}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/user_timeline/ids
     * @return 
     * @throws IOException
     */
    public String user_timeline_ids() {
        return doGet(USER_TIMELINE_IDS);
    }
    
    public String user_timeline_ids(String uid) {
        return doGet(USER_TIMELINE_IDS, new Prameters(new String[]{UID}, new Object[]{uid}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/user_timeline
     * @return
     * @throws IOException
     */
    public String user_timeline() {
        return doGet(USER_TIMELINE);
    }
    
    public String user_timeline(String uid) {
        return doGet(USER_TIMELINE, new Prameters(new String[]{UID}, new Object[]{uid}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/friends_timeline/ids
     * @return
     * @throws IOException
     */
    public String friends_timeline_ids() {
        return doGet(FRIENDS_TIMELINE_IDS);
    }
    
    public String friends_timeline_ids(int count) {
        return doGet(FRIENDS_TIMELINE_IDS, new Prameters(new String[]{COUNT}, new Object[]{count}));
    }
    
    public String friends_timeline_ids(int count, int page) {
        return doGet(FRIENDS_TIMELINE_IDS, new Prameters(new String[]{COUNT, PAGE}, new Object[]{count, page}));
    }
    
    public String friends_timeline_ids(int count, int page, int base_app) {
        return doGet(FRIENDS_TIMELINE_IDS, new Prameters(new String[]{COUNT, PAGE, BASE_APP}, new Object[]{count, page, base_app}));
    }
    
    public String friends_timeline_ids(int count, int page, int base_app, int feature) {
        return doGet(FRIENDS_TIMELINE_IDS, new Prameters(new String[]{COUNT, PAGE, BASE_APP, FEATURE}, new Object[]{count, page, base_app, feature}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/home_timeline
     * @return
     * @throws IOException 
     */
    public String home_timeline() {
        return doGet(HOME_TIMELINE);
    }
    
    public String home_timeline(int count) {
        return doGet(HOME_TIMELINE, new Prameters(new String[]{COUNT}, new Object[]{count}));
    }
    
    public String home_timeline(int count, int page) {
        return doGet(HOME_TIMELINE, new Prameters(new String[]{COUNT, PAGE}, new Object[]{count, page}));
    }
    
    public String home_timeline(int count, int page, int base_app) {
        return doGet(HOME_TIMELINE, new Prameters(new String[]{COUNT, PAGE, BASE_APP}, new Object[]{count, page, base_app}));
    }
    
    public String home_timeline(int count, int page, int base_app, int feature) {
        return doGet(HOME_TIMELINE, new Prameters(new String[]{COUNT, PAGE, BASE_APP, FEATURE}, new Object[]{count, page, base_app, feature}));
    }
    
    public String home_timeline(int count, int page, int base_app, int feature, int trim_user) {
        return doGet(HOME_TIMELINE, new Prameters(new String[]{COUNT, PAGE, BASE_APP, FEATURE, TRIM_USER}, new Object[]{count, page, base_app, feature, trim_user}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/friends_timeline
     * @return
     * @throws IOException
     */
    public String friends_timeline() {
        return doGet(FRIENDS_TIMELINE);
    }
    
    public String friends_timeline(int count) {
        return doGet(FRIENDS_TIMELINE, new Prameters(new String[]{COUNT}, new Object[]{count}));
    }
    
    public String friends_timeline(int count, int page) {
        return doGet(FRIENDS_TIMELINE, new Prameters(new String[]{COUNT, PAGE}, new Object[]{count, page}));
    }
    
    public String friends_timeline(int count, int page, int base_app) {
        return doGet(FRIENDS_TIMELINE, new Prameters(new String[]{COUNT, PAGE, BASE_APP}, new Object[]{count, page, base_app}));
    }
    
    public String friends_timeline(int count, int page, int base_app, int feature) {
        return doGet(FRIENDS_TIMELINE, new Prameters(new String[]{COUNT, PAGE, BASE_APP, FEATURE}, new Object[]{count, page, base_app, feature}));
    }
    
    public String friends_timeline(int count, int page, int base_app, int feature, int trim_user) {
        return doGet(FRIENDS_TIMELINE, new Prameters(new String[]{COUNT, PAGE, BASE_APP, FEATURE, TRIM_USER}, new Object[]{count, page, base_app, feature, trim_user}));
    }
    
    public String public_timeline() {
        return doGet(PUBLIC_TIMELINE);
    }
    
    public String public_timeline(int count) {
        return doGet(PUBLIC_TIMELINE, new Prameters(new String[]{COUNT}, new Object[]{count}));
    }
    
    public String public_timeline(int count, int page) {
        return doGet(PUBLIC_TIMELINE, new Prameters(new String[]{COUNT, PAGE}, new Object[]{count, page}));
    }
    
    /**
     * http://open.weibo.com/wiki/2/statuses/public_timeline
     * @param count
     * @param page
     * @param base_app
     * @return
     * @throws IOException
     */
    public String public_timeline(int count, int page, int base_app) {
        return doGet(PUBLIC_TIMELINE, new Prameters(new String[]{COUNT, PAGE, BASE_APP}, new Object[]{count, page, base_app}));
    }

}
