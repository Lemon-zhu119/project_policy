package com.ruoyi.web.controller.company.domain.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class CompanyDataClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Excel(name = "外键，关联类别表")
    private Integer categoryId;

    @Excel(name = "数据类别内容")
    private String content;

    @Excel(name = "类别生效状态")
    private Integer categoryStatus;

    @Excel(name = "级别")
    private String level;

    @Excel(name = "数据类别名")
    private String name;

    @Excel(name = "数据生效状态")
    private Integer status;

    public CompanyDataClass(Integer id, Integer categoryId, String content, Integer categoryStatus,String level, String name, Integer status) {
        this.id = id;
        this.categoryId = categoryId;
        this.content = content;
        this.categoryStatus = categoryStatus;
        this.level = level;
        this.name = name;
        this.status = status;
    }

    public CompanyDataClass() {
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

    public Integer getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Integer categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CompanyDataClass{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", content='" + content + '\'' +
                ", categoryStatus=" + categoryStatus +
                ", level='" + level + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
