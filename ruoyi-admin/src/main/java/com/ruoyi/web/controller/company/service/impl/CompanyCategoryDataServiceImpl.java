package com.ruoyi.web.controller.company.service.impl;

import com.ruoyi.web.controller.company.domain.CompanyCategoryData;
import com.ruoyi.web.controller.company.mapper.CompanyCategoryDataMapper;
import com.ruoyi.web.controller.company.service.ICompanyCategoryDataService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyCategoryDataServiceImpl implements ICompanyCategoryDataService
{
    @Autowired
    private CompanyCategoryDataMapper companyCategoryDataMapper;

    @Override
    public int deleteCompanyCategoryDataByCategoryId(Integer id) {
        return companyCategoryDataMapper.deleteCompanyCategoryDataByCategoryId(id);
    }

    @Override
    public List<CompanyCategoryData> selectCompanyCategoryListDataById(Integer id) {
        return companyCategoryDataMapper.selectCompanyCategoryDataListById(id);
    }

    @Override
    public CompanyCategoryData selectCompanyCategoryDataById(Integer id)
    {
        return companyCategoryDataMapper.selectCompanyCategoryDataById(id);
    }

    @Override
    public List<CompanyCategoryData> selectCompanyCategoryListDataByCategoryId(Integer id) {
        return companyCategoryDataMapper.selectCompanyCategoryDataListByCategoryId(id);
    }

    @Override
    public List<CompanyCategoryData> selectCompanyCategoryDataList(CompanyCategoryData companyCategoryData)
    {
        return companyCategoryDataMapper.selectCompanyCategoryDataList(companyCategoryData);
    }

    @Override
    public int insertCompanyCategoryData(CompanyCategoryData companyCategoryData)
    {
        return companyCategoryDataMapper.insertCompanyCategoryData(companyCategoryData);
    }

    @Override
    public int updateCompanyCategoryData(CompanyCategoryData companyCategoryData)
    {
        return companyCategoryDataMapper.updateCompanyCategoryData(companyCategoryData);
    }

    @Override
    public int deleteCompanyCategoryDataByIds(Integer[] ids)
    {
        return companyCategoryDataMapper.deleteCompanyCategoryDataByIds(ids);
    }

    @Override
    public int deleteCompanyCategoryDataById(Integer id)
    {
        return companyCategoryDataMapper.deleteCompanyCategoryDataById(id);
    }
}
