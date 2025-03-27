package com.ruoyi.web.controller.policy.service.impl;

import com.ruoyi.web.controller.policy.domain.PolicyCategory;
import com.ruoyi.web.controller.policy.mapper.PolicyCategoryMapper;
import com.ruoyi.web.controller.policy.service.IPolicyCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyCategoryServiceImpl implements IPolicyCategoryService {
    @Autowired
    private PolicyCategoryMapper policyCategoryMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public PolicyCategory selectPolicyCategoryById(Integer id) {
        return policyCategoryMapper.selectPolicyCategoryById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param policyCategory 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<PolicyCategory> selectPolicyCategoryList(PolicyCategory policyCategory) {
        return policyCategoryMapper.selectPolicyCategoryList(policyCategory);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param policyCategory 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertPolicyCategory(PolicyCategory policyCategory) {
        return policyCategoryMapper.insertPolicyCategory(policyCategory);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param policyCategory 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updatePolicyCategory(PolicyCategory policyCategory) {
        return policyCategoryMapper.updatePolicyCategory(policyCategory);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePolicyCategoryByIds(Integer[] ids) {
        return policyCategoryMapper.deletePolicyCategoryByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePolicyCategoryById(Integer id) {
        return policyCategoryMapper.deletePolicyCategoryById(id);
    }

    @Override
    public List<PolicyCategory> selectPolicyCategoryListById(Integer id) {
        return policyCategoryMapper.selectPolicyCategoryListById(id);
    }

    @Override
    public List<PolicyCategory> selectPolicyCategoryListExact(PolicyCategory policyCategory) {
        return policyCategoryMapper.selectPolicyCategoryListExact(policyCategory);
    }

    @Override
    public PolicyCategory selectPolicyCategoryByName(String name) {
        return policyCategoryMapper.selectPolicyCategoryByName(name);
    }

    @Override
    public List<PolicyCategory> selectCategoryListByPolicyId(Integer policyId) {
        return policyCategoryMapper.selectCategoryListByPolicyId(policyId);
    }

    @Override
    public List<PolicyCategory> selectPolicyCategoryListByName(PolicyCategory policyCategory) {
        return policyCategoryMapper.selectPolicyCategoryListByName(policyCategory);
    }
}