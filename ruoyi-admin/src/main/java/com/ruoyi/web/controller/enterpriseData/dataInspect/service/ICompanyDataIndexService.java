package com.ruoyi.web.controller.enterpriseData.dataInspect.service;


import com.ruoyi.web.controller.enterpriseData.dataInspect.controller.CompanyDataIndexController;

import java.util.List;

public interface ICompanyDataIndexService
{
    /**
     * 查询指标
     *
     * @param id 指标主键
     * @return 指标
     */
    public CompanyDataIndexController.CompanyDataIndex selectCompanyDataIndexById(String id);

    public List<CompanyDataIndexController.CompanyDataIndex> selectCompanyDataIndexList(CompanyDataIndexController.CompanyDataIndex companyDataIndex);

//    /**
//     * 新增指标
//     *
//     * @param companyDataIndex 指标
//     * @return 结果
//     */
//    public int insertCompanyDataIndex(CompanyDataIndexController.CompanyDataIndex companyDataIndex);
//
//    /**
//     * 修改指标
//     *
//     * @param companyDataIndex 指标
//     * @return 结果
//     */
//    public int updateCompanyDataIndex(CompanyDataIndexController.CompanyDataIndex companyDataIndex);
//
//    /**
//     * 批量删除指标
//     *
//     * @param ids 需要删除的指标主键集合
//     * @return 结果
//     */
//    public int deleteCompanyDataIndexByIds(Long[] ids);
//
//    /**
//     * 删除指标信息
//     *
//     * @param id 指标主键
//     * @return 结果
//     */
//    public int deleteCompanyDataIndexById(Long id);
}