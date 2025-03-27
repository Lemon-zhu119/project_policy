package com.ruoyi.web.controller.company.service.impl;

import com.ruoyi.web.controller.company.domain.CompanyCategoryRecord;
import com.ruoyi.web.controller.company.mapper.CompanyCategoryRecordMapper;
import com.ruoyi.web.controller.company.service.ICompanyCategoryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyCategoryRecordServiceImpl implements ICompanyCategoryRecordService
{
    @Autowired
    private CompanyCategoryRecordMapper companyCategoryRecordMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public CompanyCategoryRecord selectCompanyCategoryRecordById(Integer id)
    {
        return companyCategoryRecordMapper.selectCompanyCategoryRecordById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param companyCategoryRecord 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CompanyCategoryRecord> selectCompanyCategoryRecordList(CompanyCategoryRecord companyCategoryRecord)
    {
        return companyCategoryRecordMapper.selectCompanyCategoryRecordList(companyCategoryRecord);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param companyCategoryRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCompanyCategoryRecord(CompanyCategoryRecord companyCategoryRecord)
    {
        return companyCategoryRecordMapper.insertCompanyCategoryRecord(companyCategoryRecord);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param companyCategoryRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCompanyCategoryRecord(CompanyCategoryRecord companyCategoryRecord)
    {
        return companyCategoryRecordMapper.updateCompanyCategoryRecord(companyCategoryRecord);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCompanyCategoryRecordByIds(Integer[] ids)
    {
        return companyCategoryRecordMapper.deleteCompanyCategoryRecordByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCompanyCategoryRecordById(Integer id)
    {
        return companyCategoryRecordMapper.deleteCompanyCategoryRecordById(id);
    }
}
