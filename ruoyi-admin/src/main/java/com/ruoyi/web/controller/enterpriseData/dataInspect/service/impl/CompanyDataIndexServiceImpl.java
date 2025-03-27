package com.ruoyi.web.controller.enterpriseData.dataInspect.service.impl;
import com.ruoyi.web.controller.enterpriseData.dataInspect.controller.CompanyDataIndexController;
import com.ruoyi.web.controller.enterpriseData.dataInspect.mapper.CompanyDataIndexMapper;
import com.ruoyi.web.controller.enterpriseData.dataInspect.service.ICompanyDataIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyDataIndexServiceImpl implements ICompanyDataIndexService
{
    @Autowired
    private CompanyDataIndexMapper companyDataIndexMapper;

    @Override
    public CompanyDataIndexController.CompanyDataIndex selectCompanyDataIndexById(String id)
    {
        return companyDataIndexMapper.selectCompanyDataIndexById(id);
    }


    @Override
    public List<CompanyDataIndexController.CompanyDataIndex> selectCompanyDataIndexList(CompanyDataIndexController.CompanyDataIndex companyDataIndex)
    {
        return companyDataIndexMapper.selectCompanyDataIndexList(companyDataIndex);
    }

//    /**
//     * 新增指标
//     *
//     * @param companyDataIndex 指标
//     * @return 结果
//     */
//    @Override
//    public int insertCompanyDataIndex(CompanyDataIndexController.CompanyDataIndex companyDataIndex)
//    {
//        return companyDataIndexMapper.insertCompanyDataIndex(companyDataIndex);
//    }
//
//    /**
//     * 修改指标
//     *
//     * @param companyDataIndex 指标
//     * @return 结果
//     */
//    @Override
//    public int updateCompanyDataIndex(CompanyDataIndexController.CompanyDataIndex companyDataIndex)
//    {
//        return companyDataIndexMapper.updateCompanyDataIndex(companyDataIndex);
//    }
//
//    /**
//     * 批量删除指标
//     *
//     * @param ids 需要删除的指标主键
//     * @return 结果
//     */
//    @Override
//    public int deleteCompanyDataIndexByIds(Long[] ids)
//    {
//        return companyDataIndexMapper.deleteCompanyDataIndexByIds(ids);
//    }
//
//    /**
//     * 删除指标信息
//     *
//     * @param id 指标主键
//     * @return 结果
//     */
//    @Override
//    public int deleteCompanyDataIndexById(Long id)
//    {
//        return companyDataIndexMapper.deleteCompanyDataIndexById(id);
//    }
}
