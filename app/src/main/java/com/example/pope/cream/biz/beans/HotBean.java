package com.example.pope.cream.biz.beans;

import cn.bmob.v3.BmobObject;

public class HotBean extends BmobObject {

    public static final String HOT_HITS = "hotHits";
    public static final String HOT_OBJID = "hotObjId";
    public static final String HOT_COLLECTION = "hotCollection";

    private Integer hotHits;
    private String hotType;
    private Integer hotCollection;
    private String hotObjId;

    public Integer getHotHits() {
        return hotHits;
    }

    public void setHotHits(Integer hotHits) {
        this.hotHits = hotHits;
    }

    public String getHotType() {
        return hotType;
    }

    public void setHotType(String hotType) {
        this.hotType = hotType;
    }

    public Integer getHotCollection() {
        return hotCollection;
    }

    public void setHotCollection(Integer hotCollection) {
        this.hotCollection = hotCollection;
    }

    public String getHotObjId() {
        return hotObjId;
    }

    public void setHotObjId(String hotObjId) {
        this.hotObjId = hotObjId;
    }

}
