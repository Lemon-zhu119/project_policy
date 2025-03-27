package com.ruoyi.web.controller.api.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class EntNatureDate extends BaseEntity {
    @Excel(name="企业行业属性名")
    private String value;
    @Excel(name="企业行业属性值")
    private String label;

    public EntNatureDate() {
    }

    @Override
    public String toString() {
        return "entNatureDate{" +
                "value='" + value + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public EntNatureDate(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
