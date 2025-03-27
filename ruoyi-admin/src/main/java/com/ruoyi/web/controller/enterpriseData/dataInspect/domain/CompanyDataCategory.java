package com.ruoyi.web.controller.enterpriseData.dataInspect.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class CompanyDataCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据类别ID */
    private String id;

    /** 数据类别名称*/
    @Excel(name = "数据类别名称")
    private String name;

    public CompanyDataCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CompanyDataCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
