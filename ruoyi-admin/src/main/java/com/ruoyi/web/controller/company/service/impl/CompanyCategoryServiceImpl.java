package com.ruoyi.web.controller.company.service.impl;

import com.ruoyi.web.controller.company.domain.CompanyCategory;
import com.ruoyi.web.controller.company.mapper.CompanyCategoryMapper;
import com.ruoyi.web.controller.company.service.ICompanyCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyCategoryServiceImpl implements ICompanyCategoryService
{
    @Autowired
    private CompanyCategoryMapper companyCategoryMapper;

    @Override
    public List<CompanyCategory> selectCompanyCategoryListById(Integer id) {
        return companyCategoryMapper.selectCompanyCategoryListById(id);
    }

    /**
     * 查询指标
     *
     * @param id 指标主键
     * @return 指标
     */
    @Override
    public CompanyCategory selectCompanyCategoryById(Integer id)
    {
        return companyCategoryMapper.selectCompanyCategoryById(id);
    }

    /**
     * 查询指标列表
     *
     * @param companyCategory 指标
     * @return 指标
     */
    @Override
    public List<CompanyCategory> selectCompanyCategoryList(CompanyCategory companyCategory)
    {
        return companyCategoryMapper.selectCompanyCategoryList(companyCategory);
    }

    /**
     * 新增指标
     *
     * @param companyCategory 指标
     * @return 结果
     */
    @Override
    public int insertCompanyCategory(CompanyCategory companyCategory)
    {
        return companyCategoryMapper.insertCompanyCategory(companyCategory);
    }

    /**
     * 修改指标
     *
     * @param companyCategory 指标
     * @return 结果
     */
    @Override
    public int updateCompanyCategory(CompanyCategory companyCategory)
    {
        return companyCategoryMapper.updateCompanyCategory(companyCategory);
    }

    /**
     * 批量删除指标
     *
     * @param ids 需要删除的指标主键
     * @return 结果
     */
    @Override
    public int deleteCompanyCategoryByIds(Integer[] ids)
    {
        return companyCategoryMapper.deleteCompanyCategoryByIds(ids);
    }

    /**
     * 删除指标信息
     *
     * @param id 指标主键
     * @return 结果
     */
    @Override
    public int deleteCompanyCategoryById(Integer id)
    {
        return companyCategoryMapper.deleteCompanyCategoryById(id);
    }

    @Override
    public List<CompanyCategory> selectCompanyCategoryListByName(String name) {
        return companyCategoryMapper.selectCompanyCategoryListByName(name);
    }

    @Override
    public List<CompanyCategory> selectCompanyCategoryByCompanyId(Integer companyId) {
        return companyCategoryMapper.selectCompanyCategoryByCompanyId(companyId);
    }
}
