package com.ruoyi.web.controller.company.mapper;

import com.ruoyi.web.controller.company.domain.CompanyCategoryRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyCategoryRecordMapper
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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCompanyCategoryRecordById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompanyCategoryRecordByIds(Integer[] ids);
}