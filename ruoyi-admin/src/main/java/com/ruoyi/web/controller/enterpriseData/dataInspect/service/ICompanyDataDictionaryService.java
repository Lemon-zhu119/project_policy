package com.ruoyi.web.controller.enterpriseData.dataInspect.service;

import com.ruoyi.web.controller.enterpriseData.dataInspect.domain.CompanyDataDictionary;

import java.util.List;

public interface ICompanyDataDictionaryService
{
    /**
     * 查询指标
     *
     * @param id 指标主键
     * @return 指标
     */
    public CompanyDataDictionary selectCompanyDataDictionaryById(String id);

    /**
     * 查询指标列表
     *
     * @param companyDataDictionary 指标
     * @return 指标集合
     */
    public List<CompanyDataDictionary> selectCompanyDataDictionaryList(CompanyDataDictionary companyDataDictionary);

    /**
     * 新增指标
     *
     * @param companyDataDictionary 指标
     * @return 结果
     */
    public int insertCompanyDataDictionary(CompanyDataDictionary companyDataDictionary);

    /**
     * 修改指标
     *
     * @param companyDataDictionary 指标
     * @return 结果
     */
    public int updateCompanyDataDictionary(CompanyDataDictionary companyDataDictionary);

    /**
     * 批量删除指标
     *
     * @param ids 需要删除的指标主键集合
     * @return 结果
     */
    public int deleteCompanyDataDictionaryByIds(String[] ids);

    /**
     * 删除指标信息
     *
     * @param id 指标主键
     * @return 结果
     */
    public int deleteCompanyDataDictionaryById(String id);
}