package com.ruoyi.web.controller.policy.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class PolicyGovernmentAudit extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Excel(name = "审核部门")
    private String auditDepartment;

    @Excel(name = "审核状态")
    private Integer auditStatus;

    @Excel(name = "审核建议")
    private String auditAdvice;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditExpireDate;
    @Excel(name = "submissionId")
    private Integer submissionId;

    @Excel(name = "政策名称")
    private String name;
    private String companyName;

    public PolicyGovernmentAudit(Integer id, String auditDepartment, Integer auditStatus, String auditAdvice, Date auditDate, Date auditExpireDate, Integer submissionId, String name, String companyName) {
        this.id = id;
        this.auditDepartment = auditDepartment;
        this.auditStatus = auditStatus;
        this.auditAdvice = auditAdvice;
        this.auditDate = auditDate;
        this.auditExpireDate = auditExpireDate;
        this.submissionId = submissionId;
        this.name = name;
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "PolicyGovernmentAudit{" +
                "id=" + id +
                ", auditDepartment='" + auditDepartment + '\'' +
                ", auditStatus=" + auditStatus +
                ", auditAdvice='" + auditAdvice + '\'' +
                ", auditDate=" + auditDate +
                ", auditExpireDate=" + auditExpireDate +
                ", submissionId=" + submissionId +
                ", name='" + name + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }

    public Date getAuditExpireDate() {
        return auditExpireDate;
    }

    public void setAuditExpireDate(Date auditExpireDate) {
        this.auditExpireDate = auditExpireDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public PolicyGovernmentAudit(Integer id, String auditDepartment, Integer auditStatus, String auditAdvice, Date auditDate, Integer submissionId, String name, String companyName) {
        this.id = id;
        this.auditDepartment = auditDepartment;
        this.auditStatus = auditStatus;
        this.auditAdvice = auditAdvice;
        this.auditDate = auditDate;
        this.submissionId = submissionId;
        this.name = name;
        this.companyName = companyName;
    }

    public PolicyGovernmentAudit(Integer id, String auditDepartment, Integer auditStatus, String auditAdvice, Date auditDate, Integer submissionId, String policyName) {
        this.id = id;
        this.auditDepartment = auditDepartment;
        this.auditStatus = auditStatus;
        this.auditAdvice = auditAdvice;
        this.auditDate = auditDate;
        this.submissionId = submissionId;
        this.name = policyName;
    }

    public PolicyGovernmentAudit() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
