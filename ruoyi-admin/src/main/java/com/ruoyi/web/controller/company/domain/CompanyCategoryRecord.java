package com.ruoyi.web.controller.company.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CompanyCategoryRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer companyId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer categoryDataId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer status;

    public CompanyCategoryRecord(Integer id, Integer companyId, Integer categoryDataId, Integer status) {
        this.id = id;
        this.companyId = companyId;
        this.categoryDataId = categoryDataId;
        this.status = status;
    }

    public CompanyCategoryRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCategoryDataId() {
        return categoryDataId;
    }

    public void setCategoryDataId(Integer categoryDataId) {
        this.categoryDataId = categoryDataId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CompanyCategoryRecord{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", categoryDataId=" + categoryDataId +
                ", status=" + status +
                '}';
    }
}