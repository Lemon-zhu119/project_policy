package com.ruoyi.web.controller.company.service.impl;

import com.ruoyi.web.controller.company.domain.dto.CompanyIndicator;
import com.ruoyi.web.controller.company.domain.dto.CompanyProperty;
import com.ruoyi.web.controller.company.mapper.CompanyIndicatorMapper;
import com.ruoyi.web.controller.company.mapper.CompanyPropertyMapper;
import com.ruoyi.web.controller.company.service.ICompanyIndicatorService;
import com.ruoyi.web.controller.company.service.ICompanyPropertyService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyIndicatorServiceImpl implements ICompanyIndicatorService
{
    @Autowired
    private CompanyIndicatorMapper companyIndicatorMapper;

    @Override
    public List<CompanyIndicator> selectCompanyIndicatorList(CompanyIndicator companyIndicator) {
        return companyIndicatorMapper.selectCompanyIndicatorList(companyIndicator);
    }

    @Override
    public List<CompanyIndicator> selectCompanyIndicatorListByCompanyId(Integer id) {
        return companyIndicatorMapper.selectCompanyIndicatorListByCompanyId(id);
    }

    @Override
    public CompanyIndicator selectCompanyIndicatorListById(Integer id) {
        return companyIndicatorMapper.selectCompanyIndicatorListById(id);
    }

    @Override
    public List<CompanyIndicator> selectIndicatorListExact(CompanyIndicator companyIndicator) {
        return companyIndicatorMapper.selectIndicatorListExact(companyIndicator);
    }
}
