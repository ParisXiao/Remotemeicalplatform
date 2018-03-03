package com.gxey.remotemedicalplatform.javaben;

/**
 * Created by lanluo on 2017/1/8.
 */

public class MessageEntity {

    private int cenum;
    private String content;
    private int length;
    private String suffix;
    private String createtime;
    private String ConnectionId;


    private int  direction;//方向 0收 1发
    private String HeadImg;


    public int getCenum() {
        return cenum;
    }

    public void setCenum(int cenum) {
        this.cenum = cenum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getConnectionId() {
        return ConnectionId;
    }

    public void setConnectionId(String connectionId) {
        ConnectionId = connectionId;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getHeadImg() {
        return HeadImg;
    }

    public void setHeadImg(String headImg) {
        HeadImg = headImg;
    }
}
