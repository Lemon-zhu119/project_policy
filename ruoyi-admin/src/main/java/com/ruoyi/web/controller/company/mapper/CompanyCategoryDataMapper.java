package com.ruoyi.web.controller.company.mapper;

import com.ruoyi.web.controller.company.domain.CompanyCategoryData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyCategoryDataMapper
{

    public CompanyCategoryData selectCompanyCategoryDataById(Integer id);

    public List<CompanyCategoryData> selectCompanyCategoryDataListByCategoryId(Integer id);

    public List<CompanyCategoryData> selectCompanyCategoryDataListById(Integer id);

    public int deleteCompanyCategoryDataByCategoryId(Integer id);


    public List<CompanyCategoryData> selectCompanyCategoryDataList(CompanyCategoryData companyCategoryData);


    public int insertCompanyCategoryData(CompanyCategoryData companyCategoryData);


    public int updateCompanyCategoryData(CompanyCategoryData companyCategoryData);


    public int deleteCompanyCategoryDataById(Integer id);


    public int deleteCompanyCategoryDataByIds(Integer[] ids);
}