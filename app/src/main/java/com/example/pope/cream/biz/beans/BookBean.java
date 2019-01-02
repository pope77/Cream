package com.example.pope.cream.biz.beans;

import java.util.List;

import cn.bmob.v3.BmobObject;

public class BookBean extends BmobObject {

    public static final String BOOK_TYPE = "bookType";
    public static final int BOOK_TYPE_YOUTH = 1001;
    public static final int BOOK_TYPE_NOVEL = 1002;
    public static final int BOOK_TYPE_LITERATURE = 1003;
    public static final int BOOK_TYPE_ART = 1004;
    public static final int BOOK_TYPE_HUMOR = 1005;
    public static final int BOOK_TYPE_FASHION = 1006;
    public static final int BOOK_TYPE_TOURISM = 1007;
    public static final int BOOK_TYPE_GEOGRAPHY = 1008;
    public static final int BOOK_TYPE_LIFE = 1009;
    public static final int BOOK_TYPE_LOVE = 1010;
    public static final int BOOK_TYPE_PARENTING = 1011;
    public static final int BOOK_TYPE_HEALTHCARE = 1012;
    public static final int BOOK_TYPE_SPORTS = 1013;
    public static final int BOOK_TYPE_ADMINISTRATION = 1014;
    public static final int BOOK_TYPE_ECONOMICS = 1015;
    public static final int BOOK_TYPE_LAW = 1016;
    public static final int BOOK_TYPE_POLITICS = 1017;
    public static final int BOOK_TYPE_MILITARY = 1018;
    public static final int BOOK_TYPE_PHILOSOPHY = 1019;
    public static final int BOOK_TYPE_RELIGION = 1020;
    public static final int BOOK_TYPE_SOCIOLOGY = 1021;
    public static final int BOOK_TYPE_ANCIENTBOOKS = 1022;
    public static final int BOOK_TYPE_HISTORY = 1023;
    public static final int BOOK_TYPE_BIOGRAPHY = 1024;

    private String bookName;
    private String bookAuthor;
    private Double bookScore;
    private String bookCoverUrl;
    private Integer bookType;
    private String bookIntroduce;
    private String authorIntroduce;
    private List<String> shortComment;
    private List<String> longComment;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Double getBookScore() {
        return bookScore;
    }

    public void setBookScore(Double bookScore) {
        this.bookScore = bookScore;
    }

    public String getBookCoverUrl() {
        return bookCoverUrl;
    }

    public void setBookCoverUrl(String bookCoverUrl) {
        this.bookCoverUrl = bookCoverUrl;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public String getBookIntroduce() {
        return bookIntroduce;
    }

    public void setBookIntroduce(String bookIntroduce) {
        this.bookIntroduce = bookIntroduce;
    }

    public String getAuthorIntroduce() {
        return authorIntroduce;
    }

    public void setAuthorIntroduce(String authorIntroduce) {
        this.authorIntroduce = authorIntroduce;
    }

    public List<String> getShortComment() {
        return shortComment;
    }

    public void setShortComment(List<String> shortComment) {
        this.shortComment = shortComment;
    }

    public List<String> getLongComment() {
        return longComment;
    }

    public void setLongComment(List<String> longComment) {
        this.longComment = longComment;
    }

}
