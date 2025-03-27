package com.ruoyi.web.controller.api.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CompanySubmissionStatus {
    private Integer modelId;
    private String modelName;
    private List<Map<String, String>> indicatorName;
    private List<Date> history;
    private String policyStatus;
    private Integer total;

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
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

    public CompanySubmissionStatus(Integer modelId, String modelName, List<Map<String, String>> indicatorName, List<Date> history, String policyStatus, Integer total) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.indicatorName = indicatorName;
        this.history = history;
        this.policyStatus = policyStatus;
        this.total = total;
    }

    public CompanySubmissionStatus() {
    }

    public List<Date> getHistory() {
        return history;
    }

    public void setHistory(List<Date> history) {
        this.history = history;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Map<String, String>> getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(List<Map<String, String>> indicatorName) {
        this.indicatorName = indicatorName;
    }

    @Override
    public String toString() {
        return "CompanySubmissionStatus{" +
                "modelId=" + modelId +
                ", modelName='" + modelName + '\'' +
                ", indicatorName=" + indicatorName +
                ", history=" + history +
                ", policyStatus='" + policyStatus + '\'' +
                ", total=" + total +
                '}';
    }
}
