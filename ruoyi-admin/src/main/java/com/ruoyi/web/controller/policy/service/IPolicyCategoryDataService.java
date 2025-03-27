package com.ruoyi.web.controller.policy.service;

import com.ruoyi.web.controller.company.domain.CompanyCategory;
import com.ruoyi.web.controller.policy.domain.PolicyCategory;
import com.ruoyi.web.controller.policy.domain.PolicyCategoryData;

import java.util.List;

public interface IPolicyCategoryDataService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public PolicyCategoryData selectPolicyCategoryDataById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param policyCategoryData 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<PolicyCategoryData> selectPolicyCategoryDataList(PolicyCategoryData policyCategoryData);

    /**
     * 新增【请填写功能名称】
     *
     * @param policyCategoryData 【请填写功能名称】
     * @return 结果
     */
    public int insertPolicyCategoryData(PolicyCategoryData policyCategoryData);

    /**
     * 修改【请填写功能名称】
     *
     * @param policyCategoryData 【请填写功能名称】
     * @return 结果
     */
    public int updatePolicyCategoryData(PolicyCategoryData policyCategoryData);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deletePolicyCategoryDataByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deletePolicyCategoryDataById(Integer id);

    public List<PolicyCategoryData> selectPolicyCategoryDataListById(Integer id);

    public List<PolicyCategoryData> selectPolicyCategoryDataListByCategoryId(Integer id);

    public int updatePolicyCategoryDataByCategoryId(PolicyCategoryData policyCategoryData);

    int deletePolicyCategoryDataByCategoryId(Integer id);
}