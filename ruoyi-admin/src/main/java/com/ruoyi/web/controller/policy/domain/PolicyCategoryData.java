package com.ruoyi.web.controller.policy.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PolicyCategoryData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer policyCategoryId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String content;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer status;

    public PolicyCategoryData(Integer id, Integer policyCategoryId, String content, Integer status) {
        this.id = id;
        this.policyCategoryId = policyCategoryId;
        this.content = content;
        this.status = status;
    }

    public PolicyCategoryData() {
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setPolicyCategoryId(Integer policyCategoryId)
    {
        this.policyCategoryId = policyCategoryId;
    }

    public Integer getPolicyCategoryId()
    {
        return policyCategoryId;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("policyCategoryId", getPolicyCategoryId())
                .append("content", getContent())
                .append("status", getStatus())
                .toString();
    }
}