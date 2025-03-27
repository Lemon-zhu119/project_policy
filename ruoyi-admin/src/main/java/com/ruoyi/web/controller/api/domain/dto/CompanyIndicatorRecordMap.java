package com.ruoyi.web.controller.api.domain.dto;

public class CompanyIndicatorRecordMap {
    private String key;
    private String threshold;
    private String remark;
    private Integer year;

    public Object getThresholdOrRemark(){
        if(this.remark!=null){
            return this.remark;
        }else {
            return this.threshold;
        }
    }
    @Override
    public String toString() {
        return "CompanyIndicatorRecordMap{" +
                "key='" + key + '\'' +
                ", threshold='" + threshold + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public CompanyIndicatorRecordMap(String key, String threshold, String remark) {
        this.key = key;
        this.threshold = threshold;
        this.remark = remark;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public CompanyIndicatorRecordMap() {
    }

    public CompanyIndicatorRecordMap(String key, String threshold) {
        this.key = key;
        this.threshold = threshold;
    }
}
