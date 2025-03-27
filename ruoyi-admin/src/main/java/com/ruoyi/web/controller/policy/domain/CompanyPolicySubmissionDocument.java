package com.ruoyi.web.controller.policy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class CompanyPolicySubmissionDocument extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公司政策申报上传文件ID */
    private Integer id;

    /** 外键,关联子公司表 */
    @Excel(name = "外键,关联子公司表")
    private Integer userId;

    /** 外键,公司政策申报表 */
    @Excel(name = "外键,公司政策申报表")
    private Integer submissionId;

    /** 文件路径或存储位置 */
    @Excel(name = "文件路径或存储位置")
    private String filePath;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String name;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadDate;

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
    public void setSubmissionId(Integer submissionId)
    {
        this.submissionId = submissionId;
    }

    public Integer getSubmissionId()
    {
        return submissionId;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setUploadDate(Date uploadDate)
    {
        this.uploadDate = uploadDate;
    }

    public Date getUploadDate()
    {
        return uploadDate;
    }

    public CompanyPolicySubmissionDocument(Integer id, Integer userId, Integer submissionId, String filePath, String name, Date uploadDate) {
        this.id = id;
        this.userId = userId;
        this.submissionId = submissionId;
        this.filePath = filePath;
        this.name = name;
        this.uploadDate = uploadDate;
    }

    public CompanyPolicySubmissionDocument() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("submissionId", getSubmissionId())
                .append("filePath", getFilePath())
                .append("name", getName())
                .append("uploadDate", getUploadDate())
                .toString();
    }
}
