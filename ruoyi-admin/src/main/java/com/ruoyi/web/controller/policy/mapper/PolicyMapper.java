package com.ruoyi.web.controller.policy.mapper;

import com.ruoyi.web.controller.policy.domain.Policy;
import com.ruoyi.web.controller.policy.domain.dto.PolicyModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PolicyMapper {
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

    /**
     * 新增【请填写功能名称】
     *
     * @param policy 【请填写功能名称】
     * @return 结果
     */
    public int insertPolicy(Policy policy);
    public int insertPolicyModel(Policy policy);

    /**
     * 修改【请填写功能名称】
     *
     * @param policy 【请填写功能名称】
     * @return 结果
     */
    public int updatePolicy(Policy policy);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deletePolicyById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePolicyByIds(Long[] ids);

    public List<Policy> selectPolicyListById(Integer id);

    public int insertPolicyModelRecord(@Param("policyId") Integer policyId,
                            @Param("dictionaryId") Integer dictionaryId,
                            @Param("operator") String operator,
                            @Param("threshold") double threshold);


    public int updatePolicyFormula(@Param("policyId") Integer policyId,@Param("formula") String formula);

    public List<PolicyModel> selectPolicyModelListByPolicyId(Integer policyId);

    public List<Policy> selectPolicyByClass(@Param("policyType") String policyType,
                                            @Param("subPolicyType") String subPolicyType);

    public List<Policy> selectPolicyByName(String policyName);

    List<Policy> selectPolicyByPublishDate();
    List<Policy> selectPolicyByPublishDate1();

    List<Policy> selectPolicyByRec();

    List<Policy> selectPolicyByHotLevel();
    List<Policy> selectCustomPolicyList(Policy policy);
}
