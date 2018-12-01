package com.example.pope.cream.biz.beans;

import cn.bmob.v3.BmobObject;

/**
 * @author popeg
 */
public class RecommendMsgBean extends BmobObject {

    private Integer recommendPos;
    private String recommendPicUrl;
    private String recommendObjectId;

    public Integer getRecommendPos() {
        return recommendPos;
    }

    public void setRecommendPos(Integer recommendPos) {
        this.recommendPos = recommendPos;
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
