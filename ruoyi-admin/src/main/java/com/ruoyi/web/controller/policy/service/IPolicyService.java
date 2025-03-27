package com.ruoyi.web.controller.policy.service;

import com.ruoyi.web.controller.company.domain.Company;
import com.ruoyi.web.controller.policy.domain.Policy;
import com.ruoyi.web.controller.policy.domain.PolicyModelIndicatorRecord;
import com.ruoyi.web.controller.policy.domain.dto.PolicyModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IPolicyService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Policy selectPolicyById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param policy 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Policy> selectPolicyList(Policy policy);
    public List<Policy> selectCustomModelList(Policy policy);

    /**
     * 新增【请填写功能名称】
     *
     * @param policy 【请填写功能名称】
     * @return 结果
     */
    public int insertPolicyModelRecord(Policy policy);
    public int insertPolicyRecord(Policy policy);

    /**
     * 修改【请填写功能名称】
     *
     * @param policy 【请填写功能名称】
     * @return 结果
     */
    public int updatePolicy(Policy policy);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deletePolicyByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deletePolicyById(Integer id);

    public List<Policy> selectPolicyListById(Integer id);

    public int insertPolicyModelRecord(Integer policyId, Integer dictionaryId, String operator, double threhold);

    public int updatePolicyFormula(Integer policyId,String formula);

    public List<PolicyModel> selectPolicyModelListByPolicyId(Integer policyId);

    public List<Company> selectMatchCompany(Integer policyId,String formula);

    public List<PolicyModelIndicatorRecord> selectPolicyModelIndicatorRecordByPolicyId(Integer id);

    public List<Company> selectMatchCompanyV2(Integer policyId, String formula);

    public List<Company> selectMatchCompanyV3(Integer policyId, String formula);

    public List<Policy> selectPolicyByClass(@Param("policyType") String policyType,
                                            @Param("subPolicyType") String subPolicyType);

    public List<Policy> selectPolicyByName(String policyName);

    public List<Policy> selectPolicyByPublishDate();

    List<Policy> selectPolicyByPublishDate1();

    List<Policy> selectPolicyByRec();

    List<Policy> selectPolicyByHotLevel();

    List<Map<String, String>> c2cNoMatchedIndicator(Integer companyId, Policy policy, Integer year);
}
