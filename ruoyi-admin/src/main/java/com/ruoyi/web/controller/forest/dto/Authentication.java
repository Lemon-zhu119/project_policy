package com.ruoyi.web.controller.forest.dto;

import com.ruoyi.common.core.domain.BaseEntity;

public class Authentication extends BaseEntity {
    private String ret;
    private String accessToken;
    private String refreshToken;
    private String expiresIn;
    private String openid;
    private String username;
    private String code;
    private String msg;

    public Authentication(String ret, String accessToken, String refreshToken, String expiresIn, String code, String msg) {
        this.ret = ret;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.code = code;
        this.msg = msg;
    }

    public Authentication(String ret, String accessToken, String refreshToken, String expiresIn, String openid, String username, String code, String msg) {
        this.ret = ret;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.openid = openid;
        this.username = username;
        this.code = code;
        this.msg = msg;
    }

    public Authentication() {
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Authentication{" +
                "ret='" + ret + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                ", openid='" + openid + '\'' +
                ", username='" + username + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
