package com.ruoyi.web.controller.company.service.impl;

import com.ruoyi.web.controller.company.domain.CompanyReport;
import com.ruoyi.web.controller.company.mapper.CompanyReportMapper;
import com.ruoyi.web.controller.company.service.ICompanyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyReportServiceImpl implements ICompanyReportService {
    @Autowired
    private CompanyReportMapper companyReportMapper;
    @Override
    public CompanyReport selectCompanyReportByYear(String year, Integer companyId) {
        return companyReportMapper.selectCompanyReportByYear(year,companyId);
    }

    @Override
    public int updateCompanyReport(CompanyReport companyReport) {
        return companyReportMapper.updateCompanyReport(companyReport);
    }

    @Override
    public int insertCompanyReport(CompanyReport companyReport) {
        return companyReportMapper.insertCompanyReport(companyReport);
    }
}
