package org.ksoong.weibo4j.publisher;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.ksoong.weibo4j.publisher.Post.Img;

public class TestModelPost {
    
    @Test
    public void testRandom(){
        Random random = new Random();
        Img[] imgs = new Img[1];
        assertEquals(0, random.nextInt(imgs.length));
        imgs = new Img[0];
        Exception ex = null;
        try {
            random.nextInt(imgs.length);
        } catch (IllegalArgumentException e) {
            ex = e;
        }
        assertNotNull(ex);
    }
    
    @Test
    public void testDefault() {
        Post post = new Post();
        assertNull(post.getPic());
    }

}
