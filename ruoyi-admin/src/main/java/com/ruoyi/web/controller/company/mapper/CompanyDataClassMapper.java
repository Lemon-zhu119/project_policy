package com.ruoyi.web.controller.company.mapper;


import com.ruoyi.web.controller.company.domain.dto.CompanyDataClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyDataClassMapper
{

    public CompanyDataClass selectCompanyDataClassById(Integer id);


    public List<CompanyDataClass> selectCompanyDataClassList(CompanyDataClass companyDataClass);

}