package com.ruoyi.web.controller.policy.service.impl;

import com.ruoyi.web.controller.policy.domain.PolicyPropertiesData;
import com.ruoyi.web.controller.policy.mapper.PolicyPropertiesDataMapper;
import com.ruoyi.web.controller.policy.service.IPolicyPropertiesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyPropertiesDataServiceImpl implements IPolicyPropertiesDataService
{
    @Autowired
    private PolicyPropertiesDataMapper policyPropertiesDataMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public PolicyPropertiesData selectPolicyPropertiesDataById(Integer id)
    {
        return policyPropertiesDataMapper.selectPolicyPropertiesDataById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param policyPropertiesData 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<PolicyPropertiesData> selectPolicyPropertiesDataList(PolicyPropertiesData policyPropertiesData)
    {
        return policyPropertiesDataMapper.selectPolicyPropertiesDataList(policyPropertiesData);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param policyPropertiesData 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertPolicyPropertiesData(PolicyPropertiesData policyPropertiesData)
    {
        return policyPropertiesDataMapper.insertPolicyPropertiesData(policyPropertiesData);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param policyPropertiesData 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updatePolicyPropertiesData(PolicyPropertiesData policyPropertiesData)
    {
        return policyPropertiesDataMapper.updatePolicyPropertiesData(policyPropertiesData);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePolicyPropertiesDataByIds(Integer[] ids)
    {
        return policyPropertiesDataMapper.deletePolicyPropertiesDataByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePolicyPropertiesDataById(Integer id)
    {
        return policyPropertiesDataMapper.deletePolicyPropertiesDataById(id);
    }

    @Override
    public List<PolicyPropertiesData> selectPolicyPropertiesDataByPolicyId(Integer id) {
        return policyPropertiesDataMapper.selectPolicyPropertiesDataByPolicyId(id);
    }

    @Override
    public List<PolicyPropertiesData> selectPolicyPropertiesDataByCategoryDataId(Integer id) {
        return policyPropertiesDataMapper.selectPolicyPropertiesDataByCategoryDataId(id);
    }
}