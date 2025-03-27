package com.ruoyi.web.controller.submission.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class CompanyPolicySubmissionDto extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Excel(name = "userId")
    private Integer userId;

    @Excel(name = "policyId")
    private Integer policyId;

    @Excel(name = "materialStatus")
    private String materialStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申报日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    @Excel(name = "申报进度描述")
    private String progressDescription;

    @Excel(name = "审核进度描述")
    private String submissionStatus;

    @Excel(name = "公司名称")
    private String companyName;

    @Excel(name = "政策名称")
    private String policyName;

    public CompanyPolicySubmissionDto(Integer id, Integer userId, Integer policyId, String materialStatus, Date date, String progressDescription, String submissionStatus, String companyName, String policyName) {
        this.id = id;
        this.userId = userId;
        this.policyId = policyId;
        this.materialStatus = materialStatus;
        this.date = date;
        this.progressDescription = progressDescription;
        this.submissionStatus = submissionStatus;
        this.companyName = companyName;
        this.policyName = policyName;
    }

    public CompanyPolicySubmissionDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public String getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(String materialStatus) {
        this.materialStatus = materialStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProgressDescription() {
        return progressDescription;
    }

    public void setProgressDescription(String progressDescription) {
        this.progressDescription = progressDescription;
    }

    public String getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(String submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    @Override
    public String toString() {
        return "CompanyPolicySubmission{" +
                "id=" + id +
                ", userId=" + userId +
                ", policyId=" + policyId +
                ", materialStatus='" + materialStatus + '\'' +
                ", date=" + date +
                ", progressDescription='" + progressDescription + '\'' +
                ", submissionStatus='" + submissionStatus + '\'' +
                ", companyName='" + companyName + '\'' +
                ", policyName='" + policyName + '\'' +
                '}';
    }
}
