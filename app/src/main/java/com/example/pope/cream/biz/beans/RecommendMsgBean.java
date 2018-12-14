package com.example.pope.cream.biz.beans;

import cn.bmob.v3.BmobObject;

/**
 * @author popeg
 */
public class RecommendMsgBean extends BmobObject {

    public static final String RECOMMEND_POS = "recommendPos";

    public static final Integer RECOMMEND_POS_HOME = 1001;
    public static final Integer RECOMMEND_POS_FOOD = 1002;
    public static final Integer RECOMMEND_POS_DRINK = 1003;

    private String recommendType;
    private String recommendPicUrl;
    private String recommendObjectId;
    private Integer recommendPos;
    private String recommendTitle;

    public String getRecommendTitle() {
        return recommendTitle;
    }

    public void setRecommendTitle(String recommendTitle) {
        this.recommendTitle = recommendTitle;
    }

    public Integer getRecommendPos() {
        return recommendPos;
    }

    public void setRecommendPos(Integer recommendPos) {
        this.recommendPos = recommendPos;
    }

    public String getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(String recommendType) {
        this.recommendType = recommendType;
    }

    public String getRecommendPicUrl() {
        return recommendPicUrl;
    }

    public void setRecommendPicUrl(String recommendPicUrl) {
        this.recommendPicUrl = recommendPicUrl;
    }

    public String getRecommendObjectId() {
        return recommendObjectId;
    }

    public void setRecommendObjectId(String recommendObjectId) {
        this.recommendObjectId = recommendObjectId;
    }
}
