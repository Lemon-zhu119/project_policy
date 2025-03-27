package com.ruoyi.web.controller.company.mapper;

import com.ruoyi.web.controller.company.domain.CompanyCategoryData;
import com.ruoyi.web.controller.company.domain.dto.CompanyProperty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyPropertyMapper
{

    public List<CompanyProperty> selectCompanyPropertyDataList(CompanyProperty companyProperty);

    public List<CompanyProperty> selectCompanyPropertyDataListById(Integer id);

    public List<CompanyProperty> selectPropertyListExact(CompanyProperty companyProperty);

    public int updatePropertyDataByCategoryId(CompanyCategoryData companyCategoryData);

}