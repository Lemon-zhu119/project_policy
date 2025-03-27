package com.ruoyi.web.controller.enterpriseData.dataInspect.service.impl;

import com.ruoyi.web.controller.enterpriseData.dataInspect.domain.CompanyDataDictionary;
import com.ruoyi.web.controller.enterpriseData.dataInspect.mapper.CompanyDataDictionaryMapper;
import com.ruoyi.web.controller.enterpriseData.dataInspect.service.ICompanyDataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyDataDictionaryServiceImpl implements ICompanyDataDictionaryService
{
    @Autowired
    private CompanyDataDictionaryMapper companyDataDictionaryMapper;

    /**
     * 查询指标
     *
     * @param id 指标主键
     * @return 指标
     */
    @Override
    public CompanyDataDictionary selectCompanyDataDictionaryById(String id)
    {
        return companyDataDictionaryMapper.selectCompanyDataDictionaryById(id);
    }

    /**
     * 查询指标列表
     *
     * @param companyDataDictionary 指标
     * @return 指标
     */
    @Override
    public List<CompanyDataDictionary> selectCompanyDataDictionaryList(CompanyDataDictionary companyDataDictionary)
    {
        return companyDataDictionaryMapper.selectCompanyDataDictionaryList(companyDataDictionary);
    }

    /**
     * 新增指标
     *
     * @param companyDataDictionary 指标
     * @return 结果
     */
    @Override
    public int insertCompanyDataDictionary(CompanyDataDictionary companyDataDictionary)
    {
        return companyDataDictionaryMapper.insertCompanyDataDictionary(companyDataDictionary);
    }

    /**
     * 修改指标
     *
     * @param companyDataDictionary 指标
     * @return 结果
     */
    @Override
    public int updateCompanyDataDictionary(CompanyDataDictionary companyDataDictionary)
    {
        return companyDataDictionaryMapper.updateCompanyDataDictionary(companyDataDictionary);
    }

    /**
     * 批量删除指标
     *
     * @param ids 需要删除的指标主键
     * @return 结果
     */
    @Override
    public int deleteCompanyDataDictionaryByIds(String[] ids)
    {
        return companyDataDictionaryMapper.deleteCompanyDataDictionaryByIds(ids);
    }

    /**
     * 删除指标信息
     *
     * @param id 指标主键
     * @return 结果
     */
    @Override
    public int deleteCompanyDataDictionaryById(String id)
    {
        return companyDataDictionaryMapper.deleteCompanyDataDictionaryById(id);
    }
}
