package com.ruoyi.web.controller.api.domain;

import java.util.List;

public class DeleteDto {
    private Integer id;
    private Integer deleteId;
    private List<Integer> deleteIds;

    @Override
    public String toString() {
        return "DeleteDto{" +
                "id=" + id +
                ", deleteId=" + deleteId +
                ", deleteIds=" + deleteIds +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(Integer deleteId) {
        this.deleteId = deleteId;
    }

    public List<Integer> getDeleteIds() {
        return deleteIds;
    }

    public void setDeleteIds(List<Integer> deleteIds) {
        this.deleteIds = deleteIds;
    }

    public DeleteDto(Integer id, Integer deleteId, List<Integer> deleteIds) {
        this.id = id;
        this.deleteId = deleteId;
        this.deleteIds = deleteIds;
    }

    public DeleteDto() {
    }
}
