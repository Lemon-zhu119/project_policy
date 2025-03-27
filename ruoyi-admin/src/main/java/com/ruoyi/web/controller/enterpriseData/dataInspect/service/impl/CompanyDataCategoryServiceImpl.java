package com.ruoyi.web.controller.enterpriseData.dataInspect.service.impl;

import com.ruoyi.web.controller.enterpriseData.dataInspect.domain.CompanyDataCategory;
import com.ruoyi.web.controller.enterpriseData.dataInspect.mapper.CompanyDataCategoryMapper;
import com.ruoyi.web.controller.enterpriseData.dataInspect.service.ICompanyDataCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyDataCategoryServiceImpl implements ICompanyDataCategoryService
{
    @Autowired
    private CompanyDataCategoryMapper companyDataCategoryMapper;

    /**
     * 查询指标
     *
     * @param id 指标主键
     * @return 指标
     */
    @Override
    public CompanyDataCategory selectCompanyDataCategoryById(String id)
    {
        return companyDataCategoryMapper.selectCompanyDataCategoryById(id);
    }

    /**
     * 查询指标列表
     *
     * @param companyDataCategory 指标
     * @return 指标
     */
    @Override
    public List<CompanyDataCategory> selectCompanyDataCategoryList(CompanyDataCategory companyDataCategory)
    {
        return companyDataCategoryMapper.selectCompanyDataCategoryList(companyDataCategory);
    }

    /**
     * 新增指标
     *
     * @param companyDataCategory 指标
     * @return 结果
     */
    @Override
    public int insertCompanyDataCategory(CompanyDataCategory companyDataCategory)
    {
        return companyDataCategoryMapper.insertCompanyDataCategory(companyDataCategory);
    }

    /**
     * 修改指标
     *
     * @param companyDataCategory 指标
     * @return 结果
     */
    @Override
    public int updateCompanyDataCategory(CompanyDataCategory companyDataCategory)
    {
        return companyDataCategoryMapper.updateCompanyDataCategory(companyDataCategory);
    }

    /**
     * 批量删除指标
     *
     * @param ids 需要删除的指标主键
     * @return 结果
     */
    @Override
    public int deleteCompanyDataCategoryByIds(String[] ids)
    {
        return companyDataCategoryMapper.deleteCompanyDataCategoryByIds(ids);
    }

    /**
     * 删除指标信息
     *
     * @param id 指标主键
     * @return 结果
     */
    @Override
    public int deleteCompanyDataCategoryById(String id)
    {
        return companyDataCategoryMapper.deleteCompanyDataCategoryById(id);
    }
}
