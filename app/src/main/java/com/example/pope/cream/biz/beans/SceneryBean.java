package com.example.pope.cream.biz.beans;

import cn.bmob.v3.BmobObject;

public class SceneryBean extends BmobObject {

    public static final Integer SUI_RED = 11;
    public static final Integer SUI_GREEN = 22;
    public static final Integer SUI_YELLOW = 33;
    public static final Integer SUI_BLUE = 44;
    public static final Integer SUI_PURPLE = 55;

    public static final String SCENERY_UICODE = "sceneryUICode";

    private Integer sceneryUICode;
    private String sceneryPicUrl;
    private String sceneryName;
    private String sceneryAddr;
    private Integer sceneryLikes;
    private Integer sceneryDislikes;
    private Integer sceneryPrice;
    private String sceneryAWord;
    private String sceneryIntroduce;
    private String sceneryGuide;

    public Integer getSceneryUICode() {
        return sceneryUICode;
    }

    public void setSceneryUICode(Integer sceneryUICode) {
        this.sceneryUICode = sceneryUICode;
    }

    public String getSceneryPicUrl() {
        return sceneryPicUrl;
    }

    public void setSceneryPicUrl(String sceneryPicUrl) {
        this.sceneryPicUrl = sceneryPicUrl;
    }

    public String getSceneryName() {
        return sceneryName;
    }

    public void setSceneryName(String sceneryName) {
        this.sceneryName = sceneryName;
    }

    public String getSceneryAddr() {
        return sceneryAddr;
    }

    public void setSceneryAddr(String sceneryAddr) {
        this.sceneryAddr = sceneryAddr;
    }

    public Integer getSceneryLikes() {
        return sceneryLikes;
    }

    public void setSceneryLikes(Integer sceneryLikes) {
        this.sceneryLikes = sceneryLikes;
    }

    public Integer getSceneryDislikes() {
        return sceneryDislikes;
    }

    public void setSceneryDislikes(Integer sceneryDislikes) {
        this.sceneryDislikes = sceneryDislikes;
    }

    public Integer getSceneryPrice() {
        return sceneryPrice;
    }

    public void setSceneryPrice(Integer sceneryPrice) {
        this.sceneryPrice = sceneryPrice;
    }

    public String getSceneryAWord() {
        return sceneryAWord;
    }

    public void setSceneryAWord(String sceneryAWord) {
        this.sceneryAWord = sceneryAWord;
    }

    public String getSceneryIntroduce() {
        return sceneryIntroduce;
    }

    public void setSceneryIntroduce(String sceneryIntroduce) {
        this.sceneryIntroduce = sceneryIntroduce;
    }

    public String getSceneryGuide() {
        return sceneryGuide;
    }

    public void setSceneryGuide(String sceneryGuide) {
        this.sceneryGuide = sceneryGuide;
    }
}
