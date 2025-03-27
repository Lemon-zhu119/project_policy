package com.ruoyi.web.controller.company.mapper;

import com.ruoyi.web.controller.company.domain.CompanyReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyReportMapper {
    CompanyReport selectCompanyReportByYear(@Param("year") String year, @Param("companyId") Integer companyId);

    int updateCompanyReport(CompanyReport companyReport);

    int insertCompanyReport(CompanyReport companyReport);
}
