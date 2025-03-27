package com.ruoyi.web.controller.policy.mapper;

import com.ruoyi.web.controller.policy.domain.PolicyModelIndicatorRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PolicyModelIndicatorRecordMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public PolicyModelIndicatorRecord selectPolicyModelIndicatorRecordById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param policyModelIndicatorRecord 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<PolicyModelIndicatorRecord> selectPolicyModelIndicatorRecordList(PolicyModelIndicatorRecord policyModelIndicatorRecord);

    /**
     * 新增【请填写功能名称】
     *
     * @param policyModelIndicatorRecord 【请填写功能名称】
     * @return 结果
     */
    public int insertPolicyModelIndicatorRecord(PolicyModelIndicatorRecord policyModelIndicatorRecord);

    /**
     * 修改【请填写功能名称】
     *
     * @param policyModelIndicatorRecord 【请填写功能名称】
     * @return 结果
     */
    public int updatePolicyModelIndicatorRecord(PolicyModelIndicatorRecord policyModelIndicatorRecord);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deletePolicyModelIndicatorRecordById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePolicyModelIndicatorRecordByIds(Integer[] ids);

    public int deletePolicyModelIndicatorRecordByPID(Integer id);

    public PolicyModelIndicatorRecord selectPolicyModelIndicatorRecordByPID(Integer id);

    public List<PolicyModelIndicatorRecord> selectPolicyModelIndicatorRecordByPolicyId(Integer id);

    public PolicyModelIndicatorRecord selectPolicyModelIndicatorRecord(@Param("policyId") Integer policyId
            ,@Param("indicatorDictionaryId") Integer indicatorDictionaryId);
}