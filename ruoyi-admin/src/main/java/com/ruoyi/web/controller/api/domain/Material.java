package com.ruoyi.web.controller.api.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class Material extends BaseEntity {
    @Excel(name = "材料Id")
    private Long clId;
    @Excel(name = "模型名称")
    private String modelName;
    @Excel(name = "材料名称")
    private String policyStatus;


    public Material(Long clId, String modelName, String policyStatus) {
        this.clId = clId;
        this.modelName = modelName;
        this.policyStatus = policyStatus;
    }

    public Long getClId() {
        return clId;
    }

    public void setClId(Long clId) {
        this.clId = clId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(String policyStatus) {
        this.policyStatus = policyStatus;
    }

    @Override
    public String toString() {
        return "Material{" +
                "clId=" + clId +
                ", modelName=" + modelName +
                ", policyStatus=" + policyStatus +
                '}';
    }
}
