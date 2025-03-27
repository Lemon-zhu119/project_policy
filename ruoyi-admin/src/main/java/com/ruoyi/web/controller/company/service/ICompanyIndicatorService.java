package com.ruoyi.web.controller.company.service;


import com.ruoyi.web.controller.company.domain.dto.CompanyIndicator;
import com.ruoyi.web.controller.company.domain.dto.CompanyProperty;

import java.util.List;

public interface ICompanyIndicatorService
{
    public List<CompanyIndicator> selectCompanyIndicatorList(CompanyIndicator companyIndicator);

    public List<CompanyIndicator> selectCompanyIndicatorListByCompanyId(Integer id);

    public CompanyIndicator selectCompanyIndicatorListById(Integer id);

    public List<CompanyIndicator> selectIndicatorListExact(CompanyIndicator companyIndicator);
}