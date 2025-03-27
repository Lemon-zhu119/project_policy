package com.ruoyi.web.controller.company.service;

import com.ruoyi.web.controller.company.domain.CompanyReport;

public interface ICompanyReportService {

    CompanyReport selectCompanyReportByYear(String year, Integer companyId);

    int updateCompanyReport(CompanyReport companyReport);

    int insertCompanyReport(CompanyReport companyReport);
}
