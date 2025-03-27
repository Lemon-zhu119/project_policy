package com.ruoyi.web.controller.api.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class MatchN extends BaseEntity {
    @Excel(name = "智能匹配总数")
    private Long matchN;
    public MatchN(Long matchN) {
        this.matchN = matchN;
    }

    public Long getMatchN() {
        return matchN;
    }

    public void setMatchN(Long matchN) {
        this.matchN = matchN;
    }

    @Override
    public String toString() {
        return "MatchN{" +
                "matchN=" + matchN +
                '}';
    }

    public MatchN() {
    }
}