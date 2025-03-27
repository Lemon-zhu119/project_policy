package com.ruoyi.web.controller.company.service;



import com.ruoyi.web.controller.company.domain.dto.CompanyDataClass;

import java.util.List;

public interface ICompanyDataClassService
{
    /**
     * 查询指标
     *
     * @param id 指标主键
     * @return 指标
     */
    public CompanyDataClass selectCompanyDataClassById(Integer id);

    public List<CompanyDataClass> selectCompanyDataClassList(CompanyDataClass companyDataClass);
}