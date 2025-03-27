package com.ruoyi.web.controller.policy.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class GovernmentAudit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer submissionId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String auditDepartment;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer auditStatus;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String auditAdvice;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date auditDate;
    private Date auditExpireDate;


    public GovernmentAudit(Integer id, Integer submissionId, String auditDepartment, Integer auditStatus, String auditAdvice, Date auditDate, Date auditExpireDate) {
        this.id = id;
        this.submissionId = submissionId;
        this.auditDepartment = auditDepartment;
        this.auditStatus = auditStatus;
        this.auditAdvice = auditAdvice;
        this.auditDate = auditDate;
        this.auditExpireDate = auditExpireDate;
    }

    @Override
    public String toString() {
        return "GovernmentAudit{" +
                "id=" + id +
                ", submissionId=" + submissionId +
                ", auditDepartment='" + auditDepartment + '\'' +
                ", auditStatus=" + auditStatus +
                ", auditAdvice='" + auditAdvice + '\'' +
                ", auditDate=" + auditDate +
                ", auditExpireDate=" + auditExpireDate +
                '}';
    }

    public Date getAuditExpireDate() {
        return auditExpireDate;
    }

    public void setAuditExpireDate(Date auditExpireDate) {
        this.auditExpireDate = auditExpireDate;
    }

    public GovernmentAudit(Integer id, Integer submissionId, String auditDepartment, Integer auditStatus, String auditAdvice, Date auditDate) {
        this.id = id;
        this.submissionId = submissionId;
        this.auditDepartment = auditDepartment;
        this.auditStatus = auditStatus;
        this.auditAdvice = auditAdvice;
        this.auditDate = auditDate;
    }

    public GovernmentAudit() {
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setSubmissionId(Integer submissionId)
    {
        this.submissionId = submissionId;
    }

    public Integer getSubmissionId()
    {
        return submissionId;
    }
    public void setAuditDepartment(String auditDepartment)
    {
        this.auditDepartment = auditDepartment;
    }

    public String getAuditDepartment()
    {
        return auditDepartment;
    }
    public void setAuditStatus(Integer auditStatus)
    {
        this.auditStatus = auditStatus;
    }

    public Integer getAuditStatus()
    {
        return auditStatus;
    }
    public void setAuditAdvice(String auditAdvice)
    {
        this.auditAdvice = auditAdvice;
    }

    public String getAuditAdvice()
    {
        return auditAdvice;
    }
    public void setAuditDate(Date auditDate)
    {
        this.auditDate = auditDate;
    }

    public Date getAuditDate()
    {
        return auditDate;
    }

}