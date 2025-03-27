package com.ruoyi.web.controller.api.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class UserVisitN extends BaseEntity {
    public UserVisitN() {
    }

    @Excel(name = "政策数量")
    private Long userVisitN;


    public UserVisitN(Long userVisitN) {
        this.userVisitN = userVisitN;

    }

    public Long getUserVisitN() {
        return userVisitN;
    }

    public void setUserVisitN(Long userVisitN) {
        this.userVisitN = userVisitN;
    }

    @Override
    public String toString() {
        return "PolicyLibrary{" +
                ", userVisitN=" + userVisitN +
                '}';
    }
}
