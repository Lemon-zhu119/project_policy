package com.ruoyi.web.controller.policy.service;

import com.ruoyi.web.controller.policy.domain.GovernmentAudit;
import com.ruoyi.web.controller.policy.domain.dto.PolicyGovernmentAudit;

import java.util.List;

public interface IPolicyGovernmentAuditService
{
    public List<PolicyGovernmentAudit> selectPolicyGovernmentAuditList(PolicyGovernmentAudit policyGovernmentAudit);
}