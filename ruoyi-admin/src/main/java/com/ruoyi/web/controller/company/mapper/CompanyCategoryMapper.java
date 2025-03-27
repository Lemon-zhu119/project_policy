package com.ruoyi.web.controller.company.mapper;

import com.ruoyi.web.controller.company.domain.CompanyCategory;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyCategoryMapper
{

    public CompanyCategory selectCompanyCategoryById(Integer id);

    public List<CompanyCategory> selectCompanyCategoryListById(Integer id);


    public List<CompanyCategory> selectCompanyCategoryList(CompanyCategory companyCategory);


    public int insertCompanyCategory(CompanyCategory companyCategory);


    public int updateCompanyCategory(CompanyCategory companyCategory);


    public int deleteCompanyCategoryById(Integer id);


    public int deleteCompanyCategoryByIds(Integer[] ids);

    public List<CompanyCategory> selectCompanyCategoryListByName(String name);

    public List<CompanyCategory> selectCompanyCategoryByCompanyId(Integer companyId);
}