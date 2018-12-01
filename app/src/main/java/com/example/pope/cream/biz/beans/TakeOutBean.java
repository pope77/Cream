package com.example.pope.cream.biz.beans;

import cn.bmob.v3.BmobObject;

/**
 * @author popeg
 */
public class TakeOutBean extends BmobObject {

    private String takeoutFoodName;
    private Integer takeoutPrice;
    private Integer takeoutLikes;
    private Integer takeoutCalm;
    private Integer takeoutDislikes;

    public String getTakeoutFoodName() {
        return takeoutFoodName;
    }

    public void setTakeoutFoodName(String takeoutFoodName) {
        this.takeoutFoodName = takeoutFoodName;
    }

    public Integer getTakeoutPrice() {
        return takeoutPrice;
    }

    public void setTakeoutPrice(Integer takeoutPrice) {
        this.takeoutPrice = takeoutPrice;
    }

    public Integer getTakeoutLikes() {
        return takeoutLikes;
    }

    public void setTakeoutLikes(Integer takeoutLikes) {
        this.takeoutLikes = takeoutLikes;
    }

    public Integer getTakeoutCalm() {
        return takeoutCalm;
    }

    public void setTakeoutCalm(Integer takeoutCalm) {
        this.takeoutCalm = takeoutCalm;
    }

    public Integer getTakeoutDislikes() {
        return takeoutDislikes;
    }

    public void setTakeoutDislikes(Integer takeoutDislikes) {
        this.takeoutDislikes = takeoutDislikes;
    }
}
