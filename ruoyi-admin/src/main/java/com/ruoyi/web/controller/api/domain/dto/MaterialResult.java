package com.ruoyi.web.controller.api.domain.dto;

public class MaterialResult {
    private Integer id;
    private Integer materialId;
    private String materialName;
    private String modelName;
    private Integer materialStatus;

    public MaterialResult() {
    }

    @Override
    public String toString() {
        return "MaterialResult{" +
                "id=" + id +
                ", materialId=" + materialId +
                ", materialName='" + materialName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", materialStatus='" + materialStatus + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(Integer materialStatus) {
        this.materialStatus = materialStatus;
    }

    public MaterialResult(Integer id, Integer materialId, String materialName, String modelName, Integer materialStatus) {
        this.id = id;
        this.materialId = materialId;
        this.materialName = materialName;
        this.modelName = modelName;
        this.materialStatus = materialStatus;
    }
}
