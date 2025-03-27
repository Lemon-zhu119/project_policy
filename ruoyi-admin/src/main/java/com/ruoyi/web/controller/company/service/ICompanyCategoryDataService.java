package com.ruoyi.web.controller.company.service;


import com.ruoyi.web.controller.company.domain.CompanyCategoryData;

import java.util.List;

public interface ICompanyCategoryDataService
{
    public int deleteCompanyCategoryDataByCategoryId(Integer id);
    public CompanyCategoryData selectCompanyCategoryDataById(Integer id);

    public List<CompanyCategoryData> selectCompanyCategoryListDataByCategoryId(Integer id);

    public List<CompanyCategoryData> selectCompanyCategoryListDataById(Integer id);

    public List<CompanyCategoryData> selectCompanyCategoryDataList(CompanyCategoryData companyCategoryData);

    public int insertCompanyCategoryData(CompanyCategoryData companyCategoryData);

    public int updateCompanyCategoryData(CompanyCategoryData companyCategoryData);

    public int deleteCompanyCategoryDataByIds(Integer[] ids);

    public int deleteCompanyCategoryDataById(Integer id);
}