package com.example.pope.cream.biz.beans;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * 美味类
 * 包含美食与饮品
 */
public class CateBean extends BmobObject {

    public static final Integer CATE_TYPE_FOOD = 1;
    public static final Integer CATE_TYPE_DRINK = -1;

    public static final String CATE_TYPE = "cateType";
    public static final String CATE_BIG_PIC = "cateCardPic";
    public static final String CATE_NAME = "cateName";
    public static final String CATE_SCORE = "cateScore";
    public static final String CATE_PRICE = "catePrice";
    public static final String CATE_LIKES = "cateLikes";
    public static final String CATE_DISLIKES = "cateDislikes";
    public static final String CATE_A_WORD = "cateAWord";
    public static final String CATE_INTRODUCE = "cateIntroduce";
    public static final String CATE_PICS = "catePics";
    public static final String CATE_NAMES = "cateNames";
    public static final String CATE_HITS = "cateHits";

    /**
     * 美食与饮品分类标记
     * 1->美食
     * -1->饮品
     */
    private Integer cateType;
    private String cateCardPic;
    private String cateName;
    private String cateDetailBigPic;
    private Double cateScore;
    private Integer catePrice;
    private Integer cateLikes;
    private Integer cateDislikes;
    private String cateAWord;
    private String cateIntroduce;
    private List<String> catePics;
    private List<String> cateNames;
    private Integer cateHits;

    public String getCateDetailBigPic() {
        return cateDetailBigPic;
    }

    public void setCateDetailBigPic(String cateDetailBigPic) {
        this.cateDetailBigPic = cateDetailBigPic;
    }

    public String getCateCardPic() {
        return cateCardPic;
    }

    public void setCateCardPic(String cateCardPic) {
        this.cateCardPic = cateCardPic;
    }

    public Integer getCateHits() {
        return cateHits;
    }

    public void setCateHits(Integer cateHits) {
        this.cateHits = cateHits;
    }

    public Integer getCateType() {
        return cateType;
    }

    public void setCateType(Integer cateType) {
        this.cateType = cateType;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Double getCateScore() {
        return cateScore;
    }

    public void setCateScore(Double cateScore) {
        this.cateScore = cateScore;
    }

    public Integer getCatePrice() {
        return catePrice;
    }

    public void setCatePrice(Integer catePrice) {
        this.catePrice = catePrice;
    }

    public Integer getCateLikes() {
        return cateLikes;
    }

    public void setCateLikes(Integer cateLikes) {
        this.cateLikes = cateLikes;
    }

    public Integer getCateDislikes() {
        return cateDislikes;
    }

    public void setCateDislikes(Integer cateDislikes) {
        this.cateDislikes = cateDislikes;
    }

    public String getCateAWord() {
        return cateAWord;
    }

    public void setCateAWord(String cateAWord) {
        this.cateAWord = cateAWord;
    }

    public String getCateIntroduce() {
        return cateIntroduce;
    }

    public void setCateIntroduce(String cateIntroduce) {
        this.cateIntroduce = cateIntroduce;
    }

    public List<String> getCatePics() {
        return catePics;
    }

    public void setCatePics(List<String> catePics) {
        this.catePics = catePics;
    }

    public List<String> getCateNames() {
        return cateNames;
    }

    public void setCateNames(List<String> cateNames) {
        this.cateNames = cateNames;
    }
}
