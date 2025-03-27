package com.ruoyi.web.controller.policy.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PolicyPropertiesData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer policyId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer policyCategoryDataId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer status;

    public PolicyPropertiesData() {
    }

    public PolicyPropertiesData(Integer id, Integer policyId, Integer policyCategoryDataId, Integer status) {
        this.id = id;
        this.policyId = policyId;
        this.policyCategoryDataId = policyCategoryDataId;
        this.status = status;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setPolicyId(Integer policyId)
    {
        this.policyId = policyId;
    }

    public Integer getPolicyId()
    {
        return policyId;
    }
    public void setPolicyCategoryDataId(Integer policyCategoryDataId)
    {
        this.policyCategoryDataId = policyCategoryDataId;
    }

    public Integer getPolicyCategoryDataId()
    {
        return policyCategoryDataId;
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
                .append("policyId", getPolicyId())
                .append("policyCategoryDataId", getPolicyCategoryDataId())
                .append("status", getStatus())
                .toString();
    }
}
