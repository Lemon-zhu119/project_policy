package com.ruoyi.web.controller.company.domain.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class CompanyProperty extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Excel(name = "属性名称")
    private String name;

    @Excel(name = "categoryId")
    private Integer categoryId;

    @Excel(name = "内容")
    private String content;

    @Excel(name = "companyId")
    private Integer companyId;

    @Excel(name = "categoryDataId")
    private Integer categoryDataId;

    public CompanyProperty() {
    }

    public CompanyProperty(Integer id, String name, Integer categoryId, String content, Integer companyId, Integer categoryDataId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.content = content;
        this.companyId = companyId;
        this.categoryDataId = categoryDataId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "CompanyDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", content='" + content + '\'' +
                ", companyId=" + companyId +
                ", categoryDataId=" + categoryDataId +
                '}';
    }
}
