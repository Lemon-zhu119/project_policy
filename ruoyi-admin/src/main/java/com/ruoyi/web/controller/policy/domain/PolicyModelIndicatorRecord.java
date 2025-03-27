package com.ruoyi.web.controller.policy.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PolicyModelIndicatorRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer policyId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer indicatorDictionaryId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String operator;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Double threshold;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer status;

    @Excel(name = "复杂公式")
    private String complexFormula;

    public PolicyModelIndicatorRecord(Integer id, Integer policyId, Integer indicatorDictionaryId, String operator, Double threshold, Integer status) {
        this.id = id;
        this.policyId = policyId;
        this.indicatorDictionaryId = indicatorDictionaryId;
        this.operator = operator;
        this.threshold = threshold;
        this.status = status;
    }

    public PolicyModelIndicatorRecord(Integer id, Integer policyId, Integer indicatorDictionaryId, String operator, Double threshold, Integer status, String complexFormula) {
        this.id = id;
        this.policyId = policyId;
        this.indicatorDictionaryId = indicatorDictionaryId;
        this.operator = operator;
        this.threshold = threshold;
        this.status = status;
        this.complexFormula = complexFormula;
    }

    public PolicyModelIndicatorRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public Integer getIndicatorDictionaryId() {
        return indicatorDictionaryId;
    }

    public void setIndicatorDictionaryId(Integer indicatorDictionaryId) {
        this.indicatorDictionaryId = indicatorDictionaryId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComplexFormula() {
        return complexFormula;
    }

    public void setComplexFormula(String complexFormula) {
        this.complexFormula = complexFormula;
    }

    @Override
    public String toString() {
        return "PolicyModelIndicatorRecord{" +
                "id=" + id +
                ", policyId=" + policyId +
                ", indicatorDictionaryId=" + indicatorDictionaryId +
                ", operator='" + operator + '\'' +
                ", threshold=" + threshold +
                ", status=" + status +
                ", complexFormula='" + complexFormula + '\'' +
                '}';
    }
}