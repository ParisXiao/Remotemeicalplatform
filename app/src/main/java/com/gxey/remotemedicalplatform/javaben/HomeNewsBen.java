package com.gxey.remotemedicalplatform.javaben;

/**
 * Created by xusongsong on 2017/1/9.
 */

public class HomeNewsBen {
    private String ID;
    private String Title;
    private String Content;
    private String LikeUrl;

    public String getLikeUrl() {
        return LikeUrl;
    }

    public void setLikeUrl(String likeUrl) {
        LikeUrl = likeUrl;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
