package com.ruoyi.web.controller.submission.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class CompanyPolicySubmission extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公司政策申报ID */
    private Integer id;

    /** 外键,关联子公司表 */
    @Excel(name = "外键,关联子公司表")
    private Integer userId;

    /** 外键,关联政策表 */
    @Excel(name = "外键,关联政策表")
    private Integer policyId;

    /** 材料状态 */
    @Excel(name = "材料状态")
    private String materialStatus;

    /** 申报日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申报日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 申报进度描述 */
    @Excel(name = "申报进度描述")
    private String progressDescription;

    /** 申报状态 */
    @Excel(name = "申报状态")
    private String submissionStatus;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getUserId()
    {
        return userId;
    }
    public void setPolicyId(Integer policyId)
    {
        this.policyId = policyId;
    }

    public Integer getPolicyId()
    {
        return policyId;
    }
    public void setMaterialStatus(String materialStatus)
    {
        this.materialStatus = materialStatus;
    }

    public String getMaterialStatus()
    {
        return materialStatus;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDate()
    {
        return date;
    }
    public void setProgressDescription(String progressDescription)
    {
        this.progressDescription = progressDescription;
    }

    public String getProgressDescription()
    {
        return progressDescription;
    }
    public void setSubmissionStatus(String submissionStatus)
    {
        this.submissionStatus = submissionStatus;
    }

    public String getSubmissionStatus()
    {
        return submissionStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("policyId", getPolicyId())
                .append("materialStatus", getMaterialStatus())
                .append("date", getDate())
                .append("progressDescription", getProgressDescription())
                .append("submissionStatus", getSubmissionStatus())
                .toString();
    }

    public CompanyPolicySubmission() {
    }

    public CompanyPolicySubmission(Integer userId, Integer policyId) {
        this.userId = userId;
        this.policyId = policyId;
    }

    public CompanyPolicySubmission(Integer id, Integer userId, Integer policyId, String materialStatus, Date date, String progressDescription, String submissionStatus) {
        this.id = id;
        this.userId = userId;
        this.policyId = policyId;
        this.materialStatus = materialStatus;
        this.date = date;
        this.progressDescription = progressDescription;
        this.submissionStatus = submissionStatus;
    }
}