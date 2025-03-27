package com.ruoyi.web.controller.policy.service.impl;

import com.ruoyi.web.controller.policy.domain.Policy;
import com.ruoyi.web.controller.policy.domain.dto.PolicyDetail;
import com.ruoyi.web.controller.policy.mapper.PolicyDetailMapper;
import com.ruoyi.web.controller.policy.mapper.PolicyMapper;
import com.ruoyi.web.controller.policy.service.IPolicyDetailService;
import com.ruoyi.web.controller.policy.service.IPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyDetailServiceImpl implements IPolicyDetailService
{
    @Autowired
    private PolicyDetailMapper policyDetailMapper;

    @Override
    public List<PolicyDetail> selectPolicyDetailListByPolicyId(Integer id) {
        return policyDetailMapper.selectPolicyDetailListByPolicyId(id);
    }

    @Override
    public List<PolicyDetail> selectPolicyDetailListExact(PolicyDetail policyDetail) {
        return policyDetailMapper.selectPolicyDetailListExact(policyDetail);
    }
}
