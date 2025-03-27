package com.ruoyi.web.controller.policy.service.impl;

import com.ruoyi.web.controller.policy.domain.GovernmentAuditRecord;
import com.ruoyi.web.controller.policy.mapper.GovernmentAuditRecordMapper;
import com.ruoyi.web.controller.policy.service.IGovernmentAuditRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GovernmentAuditRecordServiceImpl implements IGovernmentAuditRecordService
{
    @Autowired
    private GovernmentAuditRecordMapper governmentAuditRecordMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public GovernmentAuditRecord selectGovernmentAuditRecordById(Integer id)
    {
        return governmentAuditRecordMapper.selectGovernmentAuditRecordById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param governmentAuditRecord 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<GovernmentAuditRecord> selectGovernmentAuditRecordList(GovernmentAuditRecord governmentAuditRecord)
    {
        return governmentAuditRecordMapper.selectGovernmentAuditRecordList(governmentAuditRecord);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param governmentAuditRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertGovernmentAuditRecord(GovernmentAuditRecord governmentAuditRecord)
    {
        return governmentAuditRecordMapper.insertGovernmentAuditRecord(governmentAuditRecord);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param governmentAuditRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateGovernmentAuditRecord(GovernmentAuditRecord governmentAuditRecord)
    {
        return governmentAuditRecordMapper.updateGovernmentAuditRecord(governmentAuditRecord);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteGovernmentAuditRecordByIds(Integer[] ids)
    {
        return governmentAuditRecordMapper.deleteGovernmentAuditRecordByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteGovernmentAuditRecordById(Integer id)
    {
        return governmentAuditRecordMapper.deleteGovernmentAuditRecordById(id);
    }

    @Override
    public List<GovernmentAuditRecord> selectGovernmentAuditRecordListByAuditId(Integer auditId) {
        return governmentAuditRecordMapper.selectGovernmentAuditRecordListByAuditId(auditId);
    }
}