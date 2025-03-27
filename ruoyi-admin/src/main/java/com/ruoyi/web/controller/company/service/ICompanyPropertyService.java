package com.ruoyi.web.controller.company.service;


import com.ruoyi.web.controller.company.domain.CompanyCategoryData;
import com.ruoyi.web.controller.company.domain.dto.CompanyProperty;

import java.util.List;

public interface ICompanyPropertyService
{
    public List<CompanyProperty> selectCompanyPropertyDataList(CompanyProperty companyProperty);

    public List<CompanyProperty> selectCompanyPropertyDataListById(Integer id);

    public List<CompanyProperty> selectPropertyListExact(CompanyProperty companyProperty);

    public int updatePropertyDataByCategoryId(CompanyCategoryData companyCategoryData);
}