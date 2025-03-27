package com.ruoyi.web.controller.company.mapper;

import com.ruoyi.web.controller.company.domain.dto.CompanyIndicator;
import com.ruoyi.web.controller.company.domain.dto.CompanyProperty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyIndicatorMapper
{

    public List<CompanyIndicator> selectCompanyIndicatorList(CompanyIndicator companyIndicator);

    public List<CompanyIndicator> selectCompanyIndicatorListByCompanyId(Integer id);

    public CompanyIndicator selectCompanyIndicatorListById(Integer id);

    public List<CompanyIndicator> selectIndicatorListExact(CompanyIndicator companyIndicator);
}