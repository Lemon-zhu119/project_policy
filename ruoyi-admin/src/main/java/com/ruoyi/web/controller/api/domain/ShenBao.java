package com.ruoyi.web.controller.api.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class ShenBao extends BaseEntity {
    @Excel(name = "申报状态")
    private String shenbaoStatus;
    @Excel(name = "申报意见")
    private String shenbaoYijian;

    @Excel(name = "审核部门")
    private String shenheBumen;

    @Excel(name = "审核时间")
    private String shenheTime;

    public ShenBao() {
    }

    public ShenBao(String shenbaoStatus, String shenbaoYijian, String shenheBumen, String shenheTime) {
        this.shenbaoStatus = shenbaoStatus;
        this.shenbaoYijian = shenbaoYijian;
        this.shenheBumen = shenheBumen;
        this.shenheTime = shenheTime;
    }

    public String getShenbaoStatus() {
        return shenbaoStatus;
    }

    public void setShenbaoStatus(String shenbaoStatus) {
        this.shenbaoStatus = shenbaoStatus;
    }

    public String getShenbaoYijian() {
        return shenbaoYijian;
    }

    public void setShenbaoYijian(String shenbaoYijian) {
        this.shenbaoYijian = shenbaoYijian;
    }

    public String getShenheBumen() {
        return shenheBumen;
    }

    public void setShenheBumen(String shenheBumen) {
        this.shenheBumen = shenheBumen;
    }

    public String getShenheTime() {
        return shenheTime;
    }

    public void setShenheTime(String shenheTime) {
        this.shenheTime = shenheTime;
    }

    @Override
    public String toString() {
        return "ShenBao{" +
                "shenbaoStatus='" + shenbaoStatus + '\'' +
                ", shenbaoYijian='" + shenbaoYijian + '\'' +
                ", shenheBumen='" + shenheBumen + '\'' +
                ", shenheTime='" + shenheTime + '\'' +
                '}';
    }
}
