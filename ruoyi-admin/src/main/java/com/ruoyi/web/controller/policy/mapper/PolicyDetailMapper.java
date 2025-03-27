package com.ruoyi.web.controller.policy.mapper;

import com.ruoyi.web.controller.policy.domain.Policy;
import com.ruoyi.web.controller.policy.domain.dto.PolicyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PolicyDetailMapper
{

    public List<PolicyDetail> selectPolicyDetailListByPolicyId(Integer id);

    public List<PolicyDetail> selectPolicyDetailListExact(PolicyDetail policyDetail);

}