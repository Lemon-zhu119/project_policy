package com.ruoyi.web.controller.policy.service.impl;

import com.ruoyi.web.controller.policy.domain.GovernmentAudit;
import com.ruoyi.web.controller.policy.domain.dto.PolicyGovernmentAudit;
import com.ruoyi.web.controller.policy.mapper.GovernmentAuditMapper;
import com.ruoyi.web.controller.policy.mapper.PolicyGovernmentAuditMapper;
import com.ruoyi.web.controller.policy.service.IGovernmentAuditService;
import com.ruoyi.web.controller.policy.service.IPolicyGovernmentAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyGovernmentAuditServiceImpl implements IPolicyGovernmentAuditService {

    @Autowired
    private PolicyGovernmentAuditMapper policyGovernmentAuditMapper;

    @Override
    public List<PolicyGovernmentAudit> selectPolicyGovernmentAuditList(PolicyGovernmentAudit policyGovernmentAudit) {
        return policyGovernmentAuditMapper.selectPolicyGovernmentAuditList(policyGovernmentAudit);
    }
}