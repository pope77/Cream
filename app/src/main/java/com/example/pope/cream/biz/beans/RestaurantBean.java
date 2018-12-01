package com.example.pope.cream.biz.beans;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * @author popeg
 */
public class RestaurantBean extends BmobObject {

    private String restaurantName;
    private double restaurantScore;
    private String restaurantIntroduce;
    private String restaurantAWord;
    private Integer restaurantPrice;
    private Integer restaurantLikes;
    private Integer restaurantDislikes;
    private List<String> restaurantHot;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public double getRestaurantScore() {
        return restaurantScore;
    }

    public void setRestaurantScore(double restaurantScore) {
        this.restaurantScore = restaurantScore;
    }

    public String getRestaurantIntroduce() {
        return restaurantIntroduce;
    }

    public void setRestaurantIntroduce(String restaurantIntroduce) {
        this.restaurantIntroduce = restaurantIntroduce;
    }

    public String getRestaurantAWord() {
        return restaurantAWord;
    }

    public void setRestaurantAWord(String restaurantAWord) {
        this.restaurantAWord = restaurantAWord;
    }

    public Integer getRestaurantPrice() {
        return restaurantPrice;
    }

    public void setRestaurantPrice(Integer restaurantPrice) {
        this.restaurantPrice = restaurantPrice;
    }

    public Integer getRestaurantLikes() {
        return restaurantLikes;
    }

    public void setRestaurantLikes(Integer restaurantLikes) {
        this.restaurantLikes = restaurantLikes;
    }

    public Integer getRestaurantDislikes() {
        return restaurantDislikes;
    }

    public void setRestaurantDislikes(Integer restaurantDislikes) {
        this.restaurantDislikes = restaurantDislikes;
    }

    public List<String> getRestaurantHot() {
        return restaurantHot;
    }

    public void setRestaurantHot(List<String> restaurantHot) {
        this.restaurantHot = restaurantHot;
    }
}
