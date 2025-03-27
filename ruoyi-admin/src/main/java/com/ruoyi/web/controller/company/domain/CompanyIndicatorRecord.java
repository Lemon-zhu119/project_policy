package com.ruoyi.web.controller.company.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class CompanyIndicatorRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Excel(name = "companyId")
    private Integer companyId;

    @Excel(name = "状态")
    private Integer status;

    @Excel(name = "dictionaryId")
    private Integer dictionaryId;

    @Excel(name = "threshold")
    private Double threshold;
    @Excel(name= "manageLevel")
    private String manageLevel;
    //用于指标文字值的回显和修改
    private String remark;
    private Integer year;

    public CompanyIndicatorRecord(Integer id, Integer companyId, Integer status, Integer dictionaryId, Double threshold, String manageLevel, String remark, Integer year) {
        this.id = id;
        this.companyId = companyId;
        this.status = status;
        this.dictionaryId = dictionaryId;
        this.threshold = threshold;
        this.manageLevel = manageLevel;
        this.remark = remark;
        this.year = year;
    }

    @Override
    public String toString() {
        return "CompanyIndicatorRecord{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", status=" + status +
                ", dictionaryId=" + dictionaryId +
                ", threshold=" + threshold +
                ", manageLevel='" + manageLevel + '\'' +
                ", remark='" + remark + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Object getThresholdOrRemark(){
        if(this.remark!=null){
            return this.remark;
        }else {
            return this.threshold;
        }
    }

    public CompanyIndicatorRecord(Integer id, Integer companyId, Integer status, Integer dictionaryId, Double threshold, String manageLevel, String remark) {
        this.id = id;
        this.companyId = companyId;
        this.status = status;
        this.dictionaryId = dictionaryId;
        this.threshold = threshold;
        this.manageLevel = manageLevel;
        this.remark = remark;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getManageLevel() {
        return manageLevel;
    }

    public void setManageLevel(String manageLevel) {
        this.manageLevel = manageLevel;
    }

    public CompanyIndicatorRecord(Integer id, Integer companyId, Integer status, Integer dictionaryId, Double threshold, String manageLevel) {
        this.id = id;
        this.companyId = companyId;
        this.status = status;
        this.dictionaryId = dictionaryId;
        this.threshold = threshold;
        this.manageLevel = manageLevel;
    }

    public CompanyIndicatorRecord(Integer id, Integer companyId, Integer status, Integer dictionaryId, Double threshold) {
        this.id = id;
        this.companyId = companyId;
        this.status = status;
        this.dictionaryId = dictionaryId;
        this.threshold = threshold;
    }

    public CompanyIndicatorRecord(Integer companyId, Integer dictionaryId, Integer year) {
        this.companyId = companyId;
        this.dictionaryId = dictionaryId;
        this.year = year;
    }

    public CompanyIndicatorRecord(Integer companyId, Integer dictionaryId) {
        this.companyId = companyId;
        this.dictionaryId = dictionaryId;
    }

    public CompanyIndicatorRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

}
