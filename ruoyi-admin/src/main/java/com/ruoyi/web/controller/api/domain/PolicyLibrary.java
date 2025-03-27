package com.ruoyi.web.controller.api.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class PolicyLibrary extends BaseEntity {
    @Excel(name = "政策数量")
    private Long policyN;
    @Excel(name = "用户总访问次数")
    private Long userVisitN;
    @Excel(name = "企业用户总数")
    private Long companyN;
    @Excel(name = "智能匹配总数")
    private Long matchN;
    @Excel(name = "企业申报总数")
    private Long declarationN;

    public PolicyLibrary(Long policyN, Long userVisitN, Long companyN, Long matchN, Long declarationN) {
        this.policyN = policyN;
        this.userVisitN = userVisitN;
        this.companyN = companyN;
        this.matchN = matchN;
        this.declarationN = declarationN;
    }

    public Long getPolicyN() {
        return policyN;
    }

    public void setPolicyN(Long policyN) {
        this.policyN = policyN;
    }

    public Long getUserVisitN() {
        return userVisitN;
    }

    public void setUserVisitN(Long userVisitN) {
        this.userVisitN = userVisitN;
    }

    public Long getCompanyN() {
        return companyN;
    }

    public void setCompanyN(Long companyN) {
        this.companyN = companyN;
    }

    public Long getMatchN() {
        return matchN;
    }

    public void setMatchN(Long matchN) {
        this.matchN = matchN;
    }

    public Long getDeclarationN() {
        return declarationN;
    }

    public void setDeclarationN(Long declarationN) {
        this.declarationN = declarationN;
    }

    public PolicyLibrary() {
    }

    @Override
    public String toString() {
        return "PolicyLibrary{" +
                "policyN=" + policyN +
                ", userVisitN=" + userVisitN +
                ", companyN=" + companyN +
                ", matchN=" + matchN +
                ", DeclarationN=" + declarationN +
                '}';
    }
}
