package com.ruoyi.web.controller.enterpriseData.dataInspect.mapper;

import com.ruoyi.web.controller.enterpriseData.dataInspect.domain.CompanyDataCategory;
import com.ruoyi.web.controller.enterpriseData.dataInspect.domain.CompanyDataCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyDataCategoryMapper
{

    public CompanyDataCategory selectCompanyDataCategoryById(String id);


    public List<CompanyDataCategory> selectCompanyDataCategoryList(CompanyDataCategory companyDataCategory);


    public int insertCompanyDataCategory(CompanyDataCategory companyDataCategory);


    public int updateCompanyDataCategory(CompanyDataCategory companyDataCategory);


    public int deleteCompanyDataCategoryById(String id);


    public int deleteCompanyDataCategoryByIds(String[] ids);
}