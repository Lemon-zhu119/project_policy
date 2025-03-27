package com.ruoyi.web.controller.enterpriseData.dataInspect.service.impl;

import java.util.List;

import com.ruoyi.web.controller.enterpriseData.dataInspect.domain.CompanyData;
import com.ruoyi.web.controller.enterpriseData.dataInspect.mapper.CompanyDataMapper;
import com.ruoyi.web.controller.enterpriseData.dataInspect.service.ICompanyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-25
 */
@Service
public class CompanyDataServiceImpl implements ICompanyDataService
{
    @Autowired
    private CompanyDataMapper companyDataMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public CompanyData selectCompanyDataById(Long id)
    {
        return companyDataMapper.selectCompanyDataById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param companyData 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CompanyData> selectCompanyDataList(CompanyData companyData)
    {
        return companyDataMapper.selectCompanyDataList(companyData);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param companyData 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCompanyData(CompanyData companyData)
    {
        return companyDataMapper.insertCompanyData(companyData);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param companyData 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCompanyData(CompanyData companyData)
    {
        return companyDataMapper.updateCompanyData(companyData);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCompanyDataByIds(Long[] ids)
    {
        return companyDataMapper.deleteCompanyDataByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCompanyDataById(Long id)
    {
        return companyDataMapper.deleteCompanyDataById(id);
    }
}
