package com.ruoyi.web.controller.policy.domain.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class PolicyDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Excel(name = "政策属性")
    private String name;

    @Excel(name = "政策Id")
    private Integer policyId;

    @Excel(name = "policy_properties_data.id")
    private Integer policyPropertiesDataId;

    @Excel(name = "属性明细")
    private String content;

    @Excel(name = "policy_category_data.id")
    private Integer policyCategoryDataId;

    public PolicyDetail(Integer id, String name, Integer policyId, String content) {
        this.id = id;
        this.name = name;
        this.policyId = policyId;
        this.content = content;
    }

    public PolicyDetail() {
    }

    public PolicyDetail(Integer id, String name, Integer policyId, Integer policyPropertiesDataId, String content, Integer policyCategoryDataId) {
        this.id = id;
        this.name = name;
        this.policyId = policyId;
        this.policyPropertiesDataId = policyPropertiesDataId;
        this.content = content;
        this.policyCategoryDataId = policyCategoryDataId;
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

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public Integer getPolicyPropertiesDataId() {
        return policyPropertiesDataId;
    }

    public void setPolicyPropertiesDataId(Integer policyPropertiesDataId) {
        this.policyPropertiesDataId = policyPropertiesDataId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPolicyCategoryDataId() {
        return policyCategoryDataId;
    }

    public void setPolicyCategoryDataId(Integer policyCategoryDataId) {
        this.policyCategoryDataId = policyCategoryDataId;
    }

    @Override
    public String toString() {
        return "PolicyDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", policyId=" + policyId +
                ", policyPropertiesDataId=" + policyPropertiesDataId +
                ", content='" + content + '\'' +
                ", policyCategoryDataId=" + policyCategoryDataId +
                '}';
    }
}
