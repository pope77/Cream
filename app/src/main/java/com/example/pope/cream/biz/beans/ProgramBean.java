package com.example.pope.cream.biz.beans;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * 节目数据表
 *
 * @author popeg
 */
public class ProgramBean extends BmobObject {

    public static final String PROGRAM_TYPE = "programType";
    public static final String PROGRAM_NAME = "programName";
    public static final String PROGRAM_TIME = "programTime";
    public static final String PROGRAM_A_WORD = "programAWord";
    public static final String PROGRAM_SCORE = "programScore";
    public static final String PROGRAM_SOURCE_URL = "programSourceUrl";
    public static final String PROGRAM_POSTER_URL = "programPosterUrl";
    public static final String PROGRAM_INTRODUCE = "programIntroduce";
    public static final String PROGRAM_COMMENTS = "programComments";
    public static final String PROGRAM_ACTOR_LIST = "programActorList";
    public static final String PROGRAM_ROLE_LIST = "programRoleList";
    public static final String PROGRAM_ACTOR_IMAGE = "programActorImage";

    public static final Integer PROGRAM_TYPE_MOVIE = 1;
    public static final Integer PROGRAM_TYPE_VIRTY = -1;


    /**
     * 节目类型
     * 1 -> 电影
     * 2 -> 综艺
     */
    private Integer programType;
    private String programName;
    private String programTime;
    private String programAWord;
    private double programScore;
    private String programSourceUrl;
    private String programPosterUrl;
    private String programIntroduce;
    private List<String> programComments;
    private List<String> programActorList;
    private List<String> programRoleList;
    private List<String> programActorImage;

    public String getProgramPosterUrl() {
        return programPosterUrl;
    }

    public void setProgramPosterUrl(String programPosterUrl) {
        this.programPosterUrl = programPosterUrl;
    }

    public Integer getProgramType() {
        return programType;
    }

    public void setProgramType(Integer programType) {
        this.programType = programType;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramTime() {
        return programTime;
    }

    public void setProgramTime(String programTime) {
        this.programTime = programTime;
    }

    public String getProgramAWord() {
        return programAWord;
    }

    public void setProgramAWord(String programAWord) {
        this.programAWord = programAWord;
    }

    public double getProgramScore() {
        return programScore;
    }

    public void setProgramScore(double programScore) {
        this.programScore = programScore;
    }

    public String getProgramSourceUrl() {
        return programSourceUrl;
    }

    public void setProgramSourceUrl(String programSourceUrl) {
        this.programSourceUrl = programSourceUrl;
    }

    public String getProgramIntroduce() {
        return programIntroduce;
    }

    public void setProgramIntroduce(String programIntroduce) {
        this.programIntroduce = programIntroduce;
    }

    public List<String> getProgramComments() {
        return programComments;
    }

    public void setProgramComments(List<String> programComments) {
        this.programComments = programComments;
    }

    public List<String> getProgramActorList() {
        return programActorList;
    }

    public void setProgramActorList(List<String> programActorList) {
        this.programActorList = programActorList;
    }

    public List<String> getProgramRoleList() {
        return programRoleList;
    }

    public void setProgramRoleList(List<String> programRoleList) {
        this.programRoleList = programRoleList;
    }

    public List<String> getProgramActorImage() {
        return programActorImage;
    }

    public void setProgramActorImage(List<String> programActorImage) {
        this.programActorImage = programActorImage;
    }
}
