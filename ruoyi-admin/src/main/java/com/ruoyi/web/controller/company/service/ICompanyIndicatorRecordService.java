package com.ruoyi.web.controller.company.service;

import com.ruoyi.web.controller.api.domain.dto.CompanyIndicatorRecordMap;
import com.ruoyi.web.controller.company.domain.CompanyIndicatorRecord;

import java.util.List;
import java.util.Map;

public interface ICompanyIndicatorRecordService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public CompanyIndicatorRecord selectCompanyIndicatorRecordById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param companyIndicatorRecord 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CompanyIndicatorRecord> selectCompanyIndicatorRecordList(CompanyIndicatorRecord companyIndicatorRecord);

    /**
     * 新增【请填写功能名称】
     *
     * @param companyIndicatorRecord 【请填写功能名称】
     * @return 结果
     */
    public int insertCompanyIndicatorRecord(CompanyIndicatorRecord companyIndicatorRecord);

    /**
     * 修改【请填写功能名称】
     *
     * @param companyIndicatorRecord 【请填写功能名称】
     * @return 结果
     */
    public int updateCompanyIndicatorRecord(CompanyIndicatorRecord companyIndicatorRecord);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteCompanyIndicatorRecordByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCompanyIndicatorRecordById(Integer id);

    public List<CompanyIndicatorRecord> selectCompanyIndicatorRecordByDicId(Integer id);

    List<CompanyIndicatorRecordMap> selectCompanyIndicatorRecordListByCompanyId(Integer id);

    int insertManageLevel(Integer companyId, Integer dictionaryId, String threshold);

    List<CompanyIndicatorRecordMap> selectCompanyIndicatorRecordListByCompanyIdAndYear(Integer id, Integer year);
}
