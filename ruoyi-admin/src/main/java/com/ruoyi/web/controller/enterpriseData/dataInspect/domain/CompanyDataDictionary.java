package com.ruoyi.web.controller.enterpriseData.dataInspect.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 指标对象 company_data_dictionary
 *
 * @author ruoyi
 * @date 2024-10-26
 */
public class CompanyDataDictionary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据字典ID */
    private String id;

    /** 数据字典名称 */
    @Excel(name = "数据字典名称")
    private String name;

    /** 外键，关联数据类别表 */
    @Excel(name = "外键，关联数据类别表")
    private String categoryId;

    public CompanyDataDictionary(String id, String name, String categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getCategoryId()
    {
        return categoryId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("categoryId", getCategoryId())
                .toString();
    }
}