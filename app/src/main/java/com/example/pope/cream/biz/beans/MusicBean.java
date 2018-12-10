package com.example.pope.cream.biz.beans;

import cn.bmob.v3.BmobObject;

public class MusicBean extends BmobObject {

    public static final String MUSIC_TYPE = "musicType";

    /**
     * 音乐类型
     * 1..10 -> "流行","摇滚","民谣","电子","布鲁斯","爵士","轻音乐","说唱","金属","朋克"
     */
    private Integer musicType;
    private String musicName;
    private String musicSingerName;
    private String musicCover;
    private String musicUrl;

    public Integer getMusicType() {
        return musicType;
    }

    public void setMusicType(Integer musicType) {
        this.musicType = musicType;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicSingerName() {
        return musicSingerName;
    }

    public void setMusicSingerName(String musicSingerName) {
        this.musicSingerName = musicSingerName;
    }

    public String getMusicCover() {
        return musicCover;
    }

    public void setMusicCover(String musicCover) {
        this.musicCover = musicCover;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }
}
