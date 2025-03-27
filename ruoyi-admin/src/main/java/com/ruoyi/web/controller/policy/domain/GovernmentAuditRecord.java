package com.ruoyi.web.controller.policy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class GovernmentAuditRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer auditId;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditDate;

    public GovernmentAuditRecord(Integer id, Integer auditId, String auditDepartment, Integer auditStatus, String auditAdvice, Date auditDate) {
        this.id = id;
        this.auditId = auditId;
        this.auditDepartment = auditDepartment;
        this.auditStatus = auditStatus;
        this.auditAdvice = auditAdvice;
        this.auditDate = auditDate;
    }

    public GovernmentAuditRecord() {
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setAuditId(Integer auditId)
    {
        this.auditId = auditId;
    }

    public Integer getAuditId()
    {
        return auditId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("auditId", getAuditId())
                .append("auditDepartment", getAuditDepartment())
                .append("auditStatus", getAuditStatus())
                .append("auditAdvise", getAuditAdvice())
                .append("auditDate", getAuditDate())
                .toString();
    }
}