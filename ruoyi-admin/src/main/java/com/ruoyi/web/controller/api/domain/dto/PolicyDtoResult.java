package com.ruoyi.web.controller.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.util.Date;
import java.util.List;

public class PolicyDtoResult {
    private Integer id;
    private String name;
    private List<String> children;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    @Override
    public String toString() {
        return "PolicyDtoResult{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                ", updateTime=" + updateTime +
                '}';
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public PolicyDtoResult(Integer id, String name, List<String> children, Date updateTime) {
        this.id = id;
        this.name = name;
        this.children = children;
        this.updateTime = updateTime;
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

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public PolicyDtoResult() {
    }

    public PolicyDtoResult(Integer id, String name, List<String> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }
}
