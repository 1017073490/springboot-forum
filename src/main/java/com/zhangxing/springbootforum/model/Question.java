package com.zhangxing.springbootforum.model;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/10 10:32
 */
public class Question {
    private Integer ID;
    private String TITLE;
    private String DESCRIPTION;
    private Long CREATE_DATE;
    private Long MODIFIED_DATE;
    private Integer CREATOR_ID;
    private Integer COMMENT_COUNT;
    private Integer VIEW_COUNT;
    private Integer LIKE_COUNT;
    private String TAGS;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public Long getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(Long CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }

    public Long getMODIFIED_DATE() {
        return MODIFIED_DATE;
    }

    public void setMODIFIED_DATE(Long MODIFIED_DATE) {
        this.MODIFIED_DATE = MODIFIED_DATE;
    }

    public Integer getCREATOR_ID() {
        return CREATOR_ID;
    }

    public void setCREATOR_ID(Integer CREATOR_ID) {
        this.CREATOR_ID = CREATOR_ID;
    }

    public Integer getCOMMENT_COUNT() {
        return COMMENT_COUNT;
    }

    public void setCOMMENT_COUNT(Integer COMMENT_COUNT) {
        this.COMMENT_COUNT = COMMENT_COUNT;
    }

    public Integer getVIEW_COUNT() {
        return VIEW_COUNT;
    }

    public void setVIEW_COUNT(Integer VIEW_COUNT) {
        this.VIEW_COUNT = VIEW_COUNT;
    }

    public Integer getLIKE_COUNT() {
        return LIKE_COUNT;
    }

    public void setLIKE_COUNT(Integer LIKE_COUNT) {
        this.LIKE_COUNT = LIKE_COUNT;
    }

    public String getTAGS() {
        return TAGS;
    }

    public void setTAGS(String TAGS) {
        this.TAGS = TAGS;
    }
}
