package com.ruoyi.web.controller.company.domain;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 company_indicator_dictionary
 *
 * @author ruoyi
 * @date 2024-10-29
 */
public class CompanyIndicatorDictionary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据字典ID */
    private Integer id;

    /** 数据字典名称 */
    @Excel(name = "数据字典名称")
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String key;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer status;

    public CompanyIndicatorDictionary(Integer id, String name, String key, Integer status) {
        this.id = id;
        this.name = name;
        this.key = key;
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
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setKey(String key)
    {
        this.key = key;
    }

    public String getKey()
    {
        return key;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("key", getKey())
                .append("status", getStatus())
                .toString();
    }
}