package com.ruoyi.web.controller.enterpriseData.dataInspect.service;

import com.ruoyi.web.controller.enterpriseData.dataInspect.domain.CompanyDataCategory;

import java.util.List;

public interface ICompanyDataCategoryService
{
    /**
     * 查询指标
     *
     * @param id 指标主键
     * @return 指标
     */
    public CompanyDataCategory selectCompanyDataCategoryById(String id);

    /**
     * 查询指标列表
     *
     * @param companyDataCategory 指标
     * @return 指标集合
     */
    public List<CompanyDataCategory> selectCompanyDataCategoryList(CompanyDataCategory companyDataCategory);

    /**
     * 新增指标
     *
     * @param companyDataCategory 指标
     * @return 结果
     */
    public int insertCompanyDataCategory(CompanyDataCategory companyDataCategory);

    /**
     * 修改指标
     *
     * @param companyDataCategory 指标
     * @return 结果
     */
    public int updateCompanyDataCategory(CompanyDataCategory companyDataCategory);

    /**
     * 批量删除指标
     *
     * @param ids 需要删除的指标主键集合
     * @return 结果
     */
    public int deleteCompanyDataCategoryByIds(String[] ids);

    /**
     * 删除指标信息
     *
     * @param id 指标主键
     * @return 结果
     */
    public int deleteCompanyDataCategoryById(String id);
}