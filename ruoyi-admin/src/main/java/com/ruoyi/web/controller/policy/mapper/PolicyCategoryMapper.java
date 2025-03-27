package com.ruoyi.web.controller.policy.mapper;

import com.ruoyi.web.controller.company.domain.CompanyCategory;
import com.ruoyi.web.controller.policy.domain.PolicyCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PolicyCategoryMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public PolicyCategory selectPolicyCategoryById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param policyCategory 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<PolicyCategory> selectPolicyCategoryList(PolicyCategory policyCategory);

    /**
     * 新增【请填写功能名称】
     *
     * @param policyCategory 【请填写功能名称】
     * @return 结果
     */
    public int insertPolicyCategory(PolicyCategory policyCategory);

    /**
     * 修改【请填写功能名称】
     *
     * @param policyCategory 【请填写功能名称】
     * @return 结果
     */
    public int updatePolicyCategory(PolicyCategory policyCategory);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deletePolicyCategoryById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePolicyCategoryByIds(Integer[] ids);

    public List<PolicyCategory> selectPolicyCategoryListById(Integer id);

    public List<PolicyCategory> selectPolicyCategoryListExact(PolicyCategory policyCategory);

    public PolicyCategory selectPolicyCategoryByName(String name);

    public List<PolicyCategory> selectCategoryListByPolicyId(Integer policyId);

    List<PolicyCategory> selectPolicyCategoryListByName(PolicyCategory policyCategory);
}