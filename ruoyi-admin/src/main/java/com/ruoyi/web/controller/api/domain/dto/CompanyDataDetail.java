package com.ruoyi.web.controller.api.domain.dto;

import java.util.List;
import java.util.Map;
import java.lang.String;
public class CompanyDataDetail {
    private Integer companyId;
    private Map<String,Object> indicatorMap;
    private List<Double> manageLevelList;

    @Override
    public String toString() {
        return "CompanyDataDetail{" +
                "companyId=" + companyId +
                ", indicatorMap=" + indicatorMap +
                ", manageLevelList=" + manageLevelList +
                '}';
    }

    public List<Double> getManageLevelList() {
        return manageLevelList;
    }

    public void setManageLevelList(List<Double> manageLevelList) {
        this.manageLevelList = manageLevelList;
    }

    public CompanyDataDetail(Integer companyId, Map<String, Object> indicatorMap, List<Double> manageLevelList) {
        this.companyId = companyId;
        this.indicatorMap = indicatorMap;
        this.manageLevelList = manageLevelList;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Map<String,Object> getIndicatorMap() {
        return indicatorMap;
    }

    public void setIndicatorMap(Map<String,Object> indicatorMap) {
        this.indicatorMap = indicatorMap;
    }

    public CompanyDataDetail() {
    }

    public CompanyDataDetail(Integer companyId, Map<String,Object> indicatorMap) {
        this.companyId = companyId;
        this.indicatorMap = indicatorMap;
    }
}
