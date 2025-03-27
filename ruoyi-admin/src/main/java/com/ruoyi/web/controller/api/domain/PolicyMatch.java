package com.ruoyi.web.controller.api.domain;

public class PolicyMatch {
    private String modelName;
    private String money;
    private String source;
    private String periodTime;

    public PolicyMatch() {
    }

    @Override
    public String toString() {
        return "PolicyMatch{" +
                "modelName='" + modelName + '\'' +
                ", money='" + money + '\'' +
                ", source='" + source + '\'' +
                ", periodTime='" + periodTime + '\'' +
                '}';
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public PolicyMatch(String modelName, String money, String source, String periodTime) {
        this.modelName = modelName;
        this.money = money;
        this.source = source;
        this.periodTime = periodTime;
    }

    public PolicyMatch(String money, String source, String periodTime) {
        this.money = money;
        this.source = source;
        this.periodTime = periodTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPeriodTime() {
        return periodTime;
    }

    public void setPeriodTime(String periodTime) {
        this.periodTime = periodTime;
    }

}
