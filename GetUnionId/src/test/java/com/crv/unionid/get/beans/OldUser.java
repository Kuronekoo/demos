package com.crv.unionid.get.beans;

public class OldUser {
    private String openid;
    private String lang;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public OldUser(String openid, String lang) {
        this.openid = openid;
        this.lang = lang;
    }

    public OldUser() {
    }
}
