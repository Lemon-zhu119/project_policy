package com.ruoyi.web.controller.policy.service.impl;

import com.ruoyi.web.controller.policy.domain.PolicyModelIndicatorRecord;
import com.ruoyi.web.controller.policy.mapper.PolicyModelIndicatorRecordMapper;
import com.ruoyi.web.controller.policy.service.IPolicyModelIndicatorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyModelIndicatorRecordServiceImpl implements IPolicyModelIndicatorRecordService {
    @Autowired
    private PolicyModelIndicatorRecordMapper policyModelIndicatorRecordMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public PolicyModelIndicatorRecord selectPolicyModelIndicatorRecordById(Integer id) {
        return policyModelIndicatorRecordMapper.selectPolicyModelIndicatorRecordById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param policyModelIndicatorRecord 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<PolicyModelIndicatorRecord> selectPolicyModelIndicatorRecordList(PolicyModelIndicatorRecord policyModelIndicatorRecord) {
        return policyModelIndicatorRecordMapper.selectPolicyModelIndicatorRecordList(policyModelIndicatorRecord);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param policyModelIndicatorRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertPolicyModelIndicatorRecord(PolicyModelIndicatorRecord policyModelIndicatorRecord) {
        return policyModelIndicatorRecordMapper.insertPolicyModelIndicatorRecord(policyModelIndicatorRecord);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param policyModelIndicatorRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updatePolicyModelIndicatorRecord(PolicyModelIndicatorRecord policyModelIndicatorRecord) {
        return policyModelIndicatorRecordMapper.updatePolicyModelIndicatorRecord(policyModelIndicatorRecord);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePolicyModelIndicatorRecordByIds(Integer[] ids) {
        return policyModelIndicatorRecordMapper.deletePolicyModelIndicatorRecordByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePolicyModelIndicatorRecordById(Integer id) {
        return policyModelIndicatorRecordMapper.deletePolicyModelIndicatorRecordById(id);
    }

    @Override
    public int deletePolicyModelIndicatorRecordByPID(Integer id) {
        return policyModelIndicatorRecordMapper.deletePolicyModelIndicatorRecordByPID(id);
    }

    @Override
    public PolicyModelIndicatorRecord selectPolicyModelIndicatorRecordByPID(Integer id) {
        return policyModelIndicatorRecordMapper.selectPolicyModelIndicatorRecordByPID(id);
    }

    @Override
    public PolicyModelIndicatorRecord selectPolicyModelIndicatorRecord(Integer policyId, Integer indicatorDictionaryId) {
        return policyModelIndicatorRecordMapper.selectPolicyModelIndicatorRecord(policyId, indicatorDictionaryId);
    }
}