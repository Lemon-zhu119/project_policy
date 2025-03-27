package com.ruoyi.web.controller.company.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class CompanyCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Excel(name = "级别")
    private String level;

    @Excel(name = "数据类别名")
    private String name;

    @Excel(name = "生效状态")
    private Integer status;

    public CompanyCategory(Integer id, String level, String name, Integer status) {
        this.id = id;
        this.level = level;
        this.name = name;
        this.status = status;
    }

    public CompanyCategory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "CompanyCategory{" +
                "id=" + id +
                ", level='" + level + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
