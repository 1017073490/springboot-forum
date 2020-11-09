package com.zhangxing.springbootforum.model;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/9 19:17
 */
public class User {
    private Integer ID;
    private String LOGIN_ID;
    private String TOKEN;
    private Long CREATE_DATE;
    private Long MODIFIED_DATE;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getLOGIN_ID() {
        return LOGIN_ID;
    }

    public void setLOGIN_ID(String LOGIN_ID) {
        this.LOGIN_ID = LOGIN_ID;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
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
}
