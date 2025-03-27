package com.ruoyi.web.controller.policy.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PolicyCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 政策类别ID */
    private Integer id;

    /** 政策类型名称 */
    @Excel(name = "政策类型名称")
    private String name;
    @Excel(name = "政策类型英文")
    private String eName;

    /** 政策属性状态 */
    @Excel(name = "政策属性状态")
    private Integer status;

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
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }


    public PolicyCategory(String eName) {
        this.eName = eName;
    }

    public PolicyCategory(Integer id, String name, Integer status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public PolicyCategory(Integer id, String name, String eName, Integer status) {
        this.id = id;
        this.name = name;
        this.eName = eName;
        this.status = status;
    }

    public PolicyCategory() {
    }

    @Override
    public String toString() {
        return "PolicyCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eName='" + eName + '\'' +
                ", status=" + status +
                '}';
    }
}