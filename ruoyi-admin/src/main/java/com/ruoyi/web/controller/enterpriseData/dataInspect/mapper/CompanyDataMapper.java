package com.ruoyi.web.controller.enterpriseData.dataInspect.mapper;

import com.ruoyi.web.controller.enterpriseData.dataInspect.domain.CompanyData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2024-10-25
 */
@Mapper
public interface CompanyDataMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public CompanyData selectCompanyDataById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param companyData 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CompanyData> selectCompanyDataList(CompanyData companyData);

    /**
     * 新增【请填写功能名称】
     *
     * @param companyData 【请填写功能名称】
     * @return 结果
     */
    public int insertCompanyData(CompanyData companyData);

    /**
     * 修改【请填写功能名称】
     *
     * @param companyData 【请填写功能名称】
     * @return 结果
     */
    public int updateCompanyData(CompanyData companyData);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCompanyDataById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompanyDataByIds(Long[] ids);
}
