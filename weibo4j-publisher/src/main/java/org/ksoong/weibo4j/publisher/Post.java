package org.ksoong.weibo4j.publisher;

import java.util.Random;

/**
 * A representation of a Weibo Post
 * @author kylin
 *
 */
public class Post {
    
    private boolean isIgnore;
    
    private String sourceURL;
    
    private Status status;
    
    private String pic;
    
    private Imgs imgs;
    
    public void setTxt(String txt) {
        if(this.status == null) {
            this.status = new Status();
        }
        this.status.setTxt(txt);
    }
    
    public void setLink(String link) {
        if(this.status == null) {
            this.status = new Status();
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

    public void setStatus(Status status) {
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

    public Imgs getImgs() {
        return imgs;
    }

    public void setImgs(Imgs imgs) {
        this.imgs = imgs;
    }
    
    public Img getImg() {
        return imgs.random();
    }
    
    public String getImgURL() {
        Img img = getImg();
        return img == null ? null : img.getSrc();
    }
    
    public String getImgAlt() {
        Img img = getImg();
        return img == null ? null : img.getAlt();
    }
    
    public String getImgName() {
        Img img = getImg();
        return img == null ? null : img.getName();
    }


    public static class Status {
        
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
            return txt + ", " + link;
        }
    }
    
    public static class Imgs {
        
        Random random = new Random();
        
        private final Img[] imgs;
        
        public Imgs(Img[] imgs){
            this.imgs = imgs;
        }
        
        public Img random() {
            if(imgs.length == 0){
                return null;
            }
            int index = random.nextInt(imgs.length);
            return imgs[index];
        }
        
        public Img[] imgs() {
            return this.imgs;
        }
    }
    
    public static class Img {
        
        private String alt;
        private String src;
        private String name;
        
        public Img(String alt, String src, String name) {
            this.alt = alt;
            this.src = src;
            this.name = name;
        }

        public String getAlt() {
            return alt;
        }
        
        public void setAlt(String alt) {
            this.alt = alt;
        }
        
        public String getSrc() {
            return src;
        }
        
        public void setSrc(String src) {
            this.src = src;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return src ;
        }
    }

}
