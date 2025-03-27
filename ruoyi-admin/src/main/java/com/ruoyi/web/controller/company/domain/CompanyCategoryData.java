package com.ruoyi.web.controller.company.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class CompanyCategoryData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Excel(name = "外键，关联类别表")
    private Integer categoryId;

    @Excel(name = "数据类别内容")
    private String content;

    @Excel(name = "生效状态")
    private Integer status;

    public CompanyCategoryData(Integer id, Integer categoryId, String content, Integer status) {
        this.id = id;
        this.categoryId = categoryId;
        this.content = content;
        this.status = status;
    }

    public CompanyCategoryData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "CompanyCategoryData{" +
                "id=" + id +
                ", categoryId='" + categoryId + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }
}
