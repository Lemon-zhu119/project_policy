package com.ruoyi.web.controller.company.service.impl;
import com.ruoyi.web.controller.company.domain.dto.CompanyDataClass;
import com.ruoyi.web.controller.company.mapper.CompanyDataClassMapper;
import com.ruoyi.web.controller.company.service.ICompanyDataClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyDataClassServiceImpl implements ICompanyDataClassService
{
    @Autowired
    private CompanyDataClassMapper companyDataClassMapper;

    @Override
    public CompanyDataClass selectCompanyDataClassById(Integer id)
    {
        return companyDataClassMapper.selectCompanyDataClassById(id);
    }


    @Override
    public List<CompanyDataClass> selectCompanyDataClassList(CompanyDataClass companyDataClass)
    {
        return companyDataClassMapper.selectCompanyDataClassList(companyDataClass);
    }
}
