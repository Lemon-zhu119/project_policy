package com.ruoyi.web.controller.enterpriseData.dataInspect.mapper;


import com.ruoyi.web.controller.enterpriseData.dataInspect.controller.CompanyDataIndexController;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyDataIndexMapper
{

    public CompanyDataIndexController.CompanyDataIndex selectCompanyDataIndexById(String id);


    public List<CompanyDataIndexController.CompanyDataIndex> selectCompanyDataIndexList(CompanyDataIndexController.CompanyDataIndex companyDataIndex);

}