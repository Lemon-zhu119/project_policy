package com.ruoyi.web.controller.api.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class ShenHe extends BaseEntity {
    @Excel(name = "申报状态")
    private String materialStatus;
    private String submissionStatus;

    @Excel(name = "申报意见")
    private String shenheYijian;

    @Override
    public String toString() {
        return "ShenHe{" +
                "materialStatus='" + materialStatus + '\'' +
                ", submissionStatus='" + submissionStatus + '\'' +
                ", shenheYijian='" + shenheYijian + '\'' +
                '}';
    }

    public String getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(String submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public ShenHe(String materialStatus, String submissionStatus, String shenheYijian) {
        this.materialStatus = materialStatus;
        this.submissionStatus = submissionStatus;
        this.shenheYijian = shenheYijian;
    }

    public ShenHe(String materialStatus, String shenheYijian) {
        this.materialStatus = materialStatus;
        this.shenheYijian = shenheYijian;
    }

    public ShenHe() {
    }

    public String getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(String materialStatus) {
        this.materialStatus = materialStatus;
    }

    public String getShenheYijian() {
        return shenheYijian;
    }

    public void setShenheYijian(String shenheYijian) {
        this.shenheYijian = shenheYijian;
    }

}
