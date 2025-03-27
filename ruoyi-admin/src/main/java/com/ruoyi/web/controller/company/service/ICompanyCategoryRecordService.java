package com.ruoyi.web.controller.company.service;

import com.ruoyi.web.controller.company.domain.CompanyCategoryRecord;

import java.util.List;

public interface ICompanyCategoryRecordService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public CompanyCategoryRecord selectCompanyCategoryRecordById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param companyCategoryRecord 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CompanyCategoryRecord> selectCompanyCategoryRecordList(CompanyCategoryRecord companyCategoryRecord);

    /**
     * 新增【请填写功能名称】
     *
     * @param companyCategoryRecord 【请填写功能名称】
     * @return 结果
     */
    public int insertCompanyCategoryRecord(CompanyCategoryRecord companyCategoryRecord);

    /**
     * 修改【请填写功能名称】
     *
     * @param companyCategoryRecord 【请填写功能名称】
     * @return 结果
     */
    public int updateCompanyCategoryRecord(CompanyCategoryRecord companyCategoryRecord);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteCompanyCategoryRecordByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCompanyCategoryRecordById(Integer id);
}