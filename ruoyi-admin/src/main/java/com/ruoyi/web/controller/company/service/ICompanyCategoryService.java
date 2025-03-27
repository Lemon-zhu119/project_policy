package com.ruoyi.web.controller.company.service;


import com.ruoyi.web.controller.company.domain.CompanyCategory;

import java.util.List;

public interface ICompanyCategoryService
{
    public CompanyCategory selectCompanyCategoryById(Integer id);

    public List<CompanyCategory> selectCompanyCategoryListById(Integer id);

    public List<CompanyCategory> selectCompanyCategoryList(CompanyCategory companyCategory);

    public int insertCompanyCategory(CompanyCategory companyCategory);

    public int updateCompanyCategory(CompanyCategory companyCategory);

    public int deleteCompanyCategoryByIds(Integer[] ids);

    public int deleteCompanyCategoryById(Integer id);

    public List<CompanyCategory> selectCompanyCategoryListByName(String name);

    public List<CompanyCategory> selectCompanyCategoryByCompanyId(Integer companyId);

}