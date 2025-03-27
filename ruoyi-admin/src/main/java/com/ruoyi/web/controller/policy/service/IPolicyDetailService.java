package com.ruoyi.web.controller.policy.service;

import com.ruoyi.web.controller.policy.domain.Policy;
import com.ruoyi.web.controller.policy.domain.dto.PolicyDetail;
import io.swagger.models.auth.In;

import java.util.List;

public interface IPolicyDetailService
{
    List<PolicyDetail> selectPolicyDetailListByPolicyId(Integer id);

    List<PolicyDetail> selectPolicyDetailListExact(PolicyDetail policyDetail);
}
