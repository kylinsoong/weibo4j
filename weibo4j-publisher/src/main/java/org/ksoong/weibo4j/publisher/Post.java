package org.ksoong.weibo4j.publisher;

public class Post {
    
    private boolean isIgnore;
    
    private String sourceURL;
    
    private WeiboStatus status;
    
    private String pic;
    
    public void setTxt(String txt) {
        if(this.status == null) {
            this.status = new WeiboStatus();
        }
        this.status.setTxt(txt);
    }
    
    public void setLink(String link) {
        if(this.status == null) {
            this.status = new WeiboStatus();
        }
        this.status.setLink(link);
    }
     
    public String getStatus() {
        return status.toString();
    }
    
    public String getStatusTxt() {
        return this.status.getTxt();
    }
    
    public String getStatusLink() {
        return this.status.getLink();
    }

    public void setStatus(WeiboStatus status) {
        this.status = status;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public boolean isIgnore() {
        return isIgnore;
    }

    public void setIgnore(boolean isIgnore) {
        this.isIgnore = isIgnore;
    }


    public static class WeiboStatus {
        
        private String txt;
        private String link;
        
        public String getTxt() {
            return txt;
        }
        
        public void setTxt(String txt) {
            this.txt = txt;
        }
        
        public String getLink() {
            return link;
        }
        
        public void setLink(String link) {
            this.link = link;
        }

        @Override
        public String toString() {
            return txt + ", [link= " + link + "]";
        }
    }

}
