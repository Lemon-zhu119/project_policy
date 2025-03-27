package com.ruoyi.web.controller.company.service.impl;

import com.ruoyi.web.controller.company.domain.CompanyCategoryData;
import com.ruoyi.web.controller.company.domain.dto.CompanyProperty;
import com.ruoyi.web.controller.company.mapper.CompanyPropertyMapper;
import com.ruoyi.web.controller.company.service.ICompanyPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyPropertyServiceImpl implements ICompanyPropertyService
{
    @Autowired
    private CompanyPropertyMapper companyPropertyMapper;

    @Override
    public List<CompanyProperty> selectCompanyPropertyDataListById(Integer id) {
        return companyPropertyMapper.selectCompanyPropertyDataListById(id);
    }

    @Override
    public List<CompanyProperty> selectCompanyPropertyDataList(CompanyProperty companyProperty) {
        return companyPropertyMapper.selectCompanyPropertyDataList(companyProperty);
    }

    @Override
    public List<CompanyProperty> selectPropertyListExact(CompanyProperty companyProperty) {
        return companyPropertyMapper.selectPropertyListExact(companyProperty);
    }

    @Override
    public int updatePropertyDataByCategoryId(CompanyCategoryData companyCategoryData) {
        return companyPropertyMapper.updatePropertyDataByCategoryId(companyCategoryData);
    }
}
