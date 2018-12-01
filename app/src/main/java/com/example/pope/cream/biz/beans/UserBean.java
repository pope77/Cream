package com.example.pope.cream.biz.beans;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * @author popeg
 */
public class UserBean extends BmobObject {

    public static final String USER_OBJID = "userObjId";
    public static final String USER_NAME = "userName";
    public static final String USER_IMEI = "userIMEI";
    public static final String USER_PHONE_NUM = "userPhoneNum";
    public static final String USER_VIEWS = "userViews";
    public static final String USER_COLLECTION = "userCollection";
    public static final String USER_AVATAR = "userAvatar";
    public static final String USER_INTEREST = "userInterestPoint";

    private String userName;
    private String userIMEI;
    private String userPhoneNum;
    private Integer userViews;
    private Integer userCollection;
    private String userAvatar;
    private List<String> userInterestPoint;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIMEI() {
        return userIMEI;
    }

    public void setUserIMEI(String userIMEI) {
        this.userIMEI = userIMEI;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public Integer getUserViews() {
        return userViews;
    }

    public void setUserViews(Integer userViews) {
        this.userViews = userViews;
    }

    public Integer getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Integer userCollection) {
        this.userCollection = userCollection;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public List<String> getUserInterestPoint() {
        return userInterestPoint;
    }

    public void setUserInterestPoint(List<String> userInterestPoint) {
        this.userInterestPoint = userInterestPoint;
    }
}
