package com.ruoyi.web.controller.policy.mapper;

import com.ruoyi.web.controller.policy.domain.GovernmentAudit;
import com.ruoyi.web.controller.policy.domain.dto.PolicyGovernmentAudit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PolicyGovernmentAuditMapper {
    public List<PolicyGovernmentAudit> selectPolicyGovernmentAuditList(PolicyGovernmentAudit policyGovernmentAudit);

}