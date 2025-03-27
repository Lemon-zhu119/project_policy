package com.ruoyi.web.controller.policy.domain.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

public class PolicyModel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @Excel(name = "政策名")
    private String name;

    @Excel(name = "英译")
    private String key;

    @Excel(name = "政策Id")
    private Integer policyId;

    @Excel(name = "indicator_dictionary_id")
    private Integer indicatorDictionaryId;

    @Excel(name = "操作符")
    private String operator;

    @Excel(name = "公式")
    private String formula;

    @Excel(name = "复杂公式")
    private String complexFormula;

    @Excel(name = "复杂公式拆分")
    private List<String> complexFormulaList;

    @Excel(name = "阈值")
    private double threshold;

    public PolicyModel(Integer id, String name, String key, Integer policyId, Integer indicatorDictionaryId, String operator, String formula, double threshold) {
        this.id = id;
        this.name = name;
        this.key = key;
        this.policyId = policyId;
        this.indicatorDictionaryId = indicatorDictionaryId;
        this.operator = operator;
        this.formula = formula;
        this.threshold = threshold;
    }

    public PolicyModel(Integer id, String name, String key, Integer policyId, Integer indicatorDictionaryId, String operator, String formula, String complexFormula, double threshold) {
        this.id = id;
        this.name = name;
        this.key = key;
        this.policyId = policyId;
        this.indicatorDictionaryId = indicatorDictionaryId;
        this.operator = operator;
        this.formula = formula;
        this.complexFormula = complexFormula;
        this.threshold = threshold;
    }

    public PolicyModel(Integer id, String name, String key, Integer policyId, Integer indicatorDictionaryId, String operator, String formula, String complexFormula, List<String> complexFormulaList, double threshold) {
        this.id = id;
        this.name = name;
        this.key = key;
        this.policyId = policyId;
        this.indicatorDictionaryId = indicatorDictionaryId;
        this.operator = operator;
        this.formula = formula;
        this.complexFormula = complexFormula;
        this.complexFormulaList = complexFormulaList;
        this.threshold = threshold;
    }

    public PolicyModel() {
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getComplexFormula() {
        return complexFormula;
    }

    public void setComplexFormula(String complexFormula) {
        this.complexFormula = complexFormula;
    }

    public List<String> getComplexFormulaList() {
        return complexFormulaList;
    }

    public void setComplexFormulaList(List<String> complexFormulaList) {
        this.complexFormulaList = complexFormulaList;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return "PolicyModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", policyId=" + policyId +
                ", indicatorDictionaryId=" + indicatorDictionaryId +
                ", operator='" + operator + '\'' +
                ", formula='" + formula + '\'' +
                ", complexFormula='" + complexFormula + '\'' +
                ", complexFormulaList=" + complexFormulaList +
                ", threshold=" + threshold +
                '}';
    }
}
