package com.ruoyi.web.controller.company.domain.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class CompanyIndicator extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @Excel(name = "dictionaryId")
    private Integer dictionaryId;

    @Excel(name = "companyId")
    private Integer companyId;

    @Excel(name = "指标名称")
    private String name;

    @Excel(name = "指标英译")
    private String key;

    @Excel(name = "阈值")
    private Double threshold;

    public CompanyIndicator(Integer id, Integer dictionaryId, Integer companyId, String name, String key, Double threshold) {
        this.id = id;
        this.dictionaryId = dictionaryId;
        this.companyId = companyId;
        this.name = name;
        this.key = key;
        this.threshold = threshold;
    }

    public CompanyIndicator() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return "CompanyIndicator{" +
                "id=" + id +
                ", dictionaryId='" + dictionaryId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", threshold=" + threshold +
                '}';
    }
}
