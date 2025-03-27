package com.ruoyi.web.controller.submission.domain.dto;

import com.ruoyi.web.controller.submission.domain.CompanyPolicySubmission;

import java.util.Date;

public class CompanyPolicySubmissionGa {
    private Integer id;
    private Integer userId;
    private Integer policyId;
    private String materialStatus;
    private Date date;
    private String progressDescription;
    private String submissionStatus;
    private Integer submissionId;
    private String auditDepartment;
    private Integer auditStatus;
    private String auditAdvice;
    private Date auditDate;

    public CompanyPolicySubmissionGa() {
    }

    public CompanyPolicySubmissionGa(Integer id, Integer userId, Integer policyId, String materialStatus, Date date, String progressDescription, String submissionStatus, Integer submissionId, String auditDepartment, Integer auditStatus, String auditAdvice, Date auditDate) {
        this.id = id;
        this.userId = userId;
        this.policyId = policyId;
        this.materialStatus = materialStatus;
        this.date = date;
        this.progressDescription = progressDescription;
        this.submissionStatus = submissionStatus;
        this.submissionId = submissionId;
        this.auditDepartment = auditDepartment;
        this.auditStatus = auditStatus;
        this.auditAdvice = auditAdvice;
        this.auditDate = auditDate;
    }

    @Override
    public String toString() {
        return "CompanyPolicySubmissionGa{" +
                "id=" + id +
                ", userId=" + userId +
                ", policyId=" + policyId +
                ", materialStatus='" + materialStatus + '\'' +
                ", date=" + date +
                ", progressDescription='" + progressDescription + '\'' +
                ", submissionStatus='" + submissionStatus + '\'' +
                ", submissionId=" + submissionId +
                ", auditDepartment='" + auditDepartment + '\'' +
                ", auditStatus=" + auditStatus +
                ", auditAdvice='" + auditAdvice + '\'' +
                ", auditDate=" + auditDate +
                '}';
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

    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public String getAuditDepartment() {
        return auditDepartment;
    }

    public void setAuditDepartment(String auditDepartment) {
        this.auditDepartment = auditDepartment;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditAdvice() {
        return auditAdvice;
    }

    public void setAuditAdvice(String auditAdvice) {
        this.auditAdvice = auditAdvice;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }
}
