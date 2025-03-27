package com.ruoyi.web.controller.policy.service.impl;

import com.ruoyi.web.controller.policy.domain.PolicyCategory;
import com.ruoyi.web.controller.policy.domain.PolicyCategoryData;
import com.ruoyi.web.controller.policy.mapper.PolicyCategoryDataMapper;
import com.ruoyi.web.controller.policy.mapper.PolicyMapper;
import com.ruoyi.web.controller.policy.service.IPolicyCategoryDataService;
import com.ruoyi.web.controller.policy.domain.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyCategoryDataServiceImpl implements IPolicyCategoryDataService
{
    @Autowired
    private PolicyCategoryDataMapper policyCategoryDataMapper;
    @Autowired
    private PolicyMapper policyMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public PolicyCategoryData selectPolicyCategoryDataById(Integer id)
    {
        return policyCategoryDataMapper.selectPolicyCategoryDataById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param policyCategoryData 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<PolicyCategoryData> selectPolicyCategoryDataList(PolicyCategoryData policyCategoryData)
    {
        return policyCategoryDataMapper.selectPolicyCategoryDataList(policyCategoryData);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param policyCategoryData 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertPolicyCategoryData(PolicyCategoryData policyCategoryData)
    {
        return policyCategoryDataMapper.insertPolicyCategoryData(policyCategoryData);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param policyCategoryData 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updatePolicyCategoryData(PolicyCategoryData policyCategoryData)
    {
        return policyCategoryDataMapper.updatePolicyCategoryData(policyCategoryData);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePolicyCategoryDataByIds(Integer[] ids)
    {
        return policyCategoryDataMapper.deletePolicyCategoryDataByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePolicyCategoryDataById(Integer id)
    {
        return policyCategoryDataMapper.deletePolicyCategoryDataById(id);
    }

    @Override
    public List<PolicyCategoryData> selectPolicyCategoryDataListByCategoryId(Integer id) {
        return policyCategoryDataMapper.selectPolicyCategoryDataListByCategoryId(id);
    }

    @Override
    public List<PolicyCategoryData> selectPolicyCategoryDataListById(Integer id) {
        return policyCategoryDataMapper.selectPolicyCategoryDataListById(id);
    }

    @Override
    public int updatePolicyCategoryDataByCategoryId(PolicyCategoryData policyCategoryData) {
        return policyCategoryDataMapper.updatePolicyCategoryDataByCategoryId(policyCategoryData);
    }

    @Override
    public int deletePolicyCategoryDataByCategoryId(Integer id) {
        return policyCategoryDataMapper.deletePolicyCategoryDataByCategoryId(id);
    }
}