package com.example.pope.cream.biz.beans;

import cn.bmob.v3.BmobObject;

/**
 * @author popeg
 */
public class TakeOutBean {

    public TakeOutBean(String takeoutFoodName, String takeoutPicUrl) {
        this.takeoutFoodName = takeoutFoodName;
        this.takeoutPicUrl = takeoutPicUrl;
    }

    private String takeoutFoodName;
    private String takeoutPicUrl;

    public String getTakeoutFoodName() {
        return takeoutFoodName;
    }

    public void setTakeoutFoodName(String takeoutFoodName) {
        this.takeoutFoodName = takeoutFoodName;
    }

    public String getTakeoutPicUrl() {
        return takeoutPicUrl;
    }

    public void setTakeoutPicUrl(String takeoutPicUrl) {
        this.takeoutPicUrl = takeoutPicUrl;
    }
}
