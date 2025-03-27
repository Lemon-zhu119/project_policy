package com.ruoyi.web.controller.company.service.impl;

import com.ruoyi.web.controller.api.domain.dto.CompanyIndicatorRecordMap;
import com.ruoyi.web.controller.company.domain.CompanyIndicatorRecord;
import com.ruoyi.web.controller.company.mapper.CompanyIndicatorRecordMapper;
import com.ruoyi.web.controller.company.service.ICompanyIndicatorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CompanyIndicatorRecordServiceImpl implements ICompanyIndicatorRecordService
{
    @Autowired
    private CompanyIndicatorRecordMapper companyIndicatorRecordMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public CompanyIndicatorRecord selectCompanyIndicatorRecordById(Integer id)
    {
        return companyIndicatorRecordMapper.selectCompanyIndicatorRecordById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param companyIndicatorRecord 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CompanyIndicatorRecord> selectCompanyIndicatorRecordList(CompanyIndicatorRecord companyIndicatorRecord)
    {
        return companyIndicatorRecordMapper.selectCompanyIndicatorRecordList(companyIndicatorRecord);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param companyIndicatorRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCompanyIndicatorRecord(CompanyIndicatorRecord companyIndicatorRecord)
    {
        return companyIndicatorRecordMapper.insertCompanyIndicatorRecord(companyIndicatorRecord);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param companyIndicatorRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCompanyIndicatorRecord(CompanyIndicatorRecord companyIndicatorRecord)
    {
        return companyIndicatorRecordMapper.updateCompanyIndicatorRecord(companyIndicatorRecord);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCompanyIndicatorRecordByIds(Integer[] ids)
    {
        return companyIndicatorRecordMapper.deleteCompanyIndicatorRecordByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCompanyIndicatorRecordById(Integer id)
    {
        return companyIndicatorRecordMapper.deleteCompanyIndicatorRecordById(id);
    }

    @Override
    public List<CompanyIndicatorRecord> selectCompanyIndicatorRecordByDicId(Integer id) {
        return companyIndicatorRecordMapper.selectCompanyIndicatorRecordByDicId(id);
    }

    @Override
    public List<CompanyIndicatorRecordMap> selectCompanyIndicatorRecordListByCompanyId(Integer id) {
        return companyIndicatorRecordMapper.selectCompanyIndicatorRecordListByCompanyId(id);
    }

    @Override
    public int insertManageLevel(Integer companyId, Integer dictionaryId, String threshold) {
        return companyIndicatorRecordMapper.insertManageLevel(companyId,dictionaryId,threshold);
    }

    @Override
    public List<CompanyIndicatorRecordMap> selectCompanyIndicatorRecordListByCompanyIdAndYear(Integer id, Integer year) {
        return companyIndicatorRecordMapper.selectCompanyIndicatorRecordListByCompanyIdAndYear(id,year);
    }
}