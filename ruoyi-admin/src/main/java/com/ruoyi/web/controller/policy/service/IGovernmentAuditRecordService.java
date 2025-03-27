package com.ruoyi.web.controller.policy.service;

import com.ruoyi.web.controller.policy.domain.GovernmentAuditRecord;

import java.util.List;

public interface IGovernmentAuditRecordService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public GovernmentAuditRecord selectGovernmentAuditRecordById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param governmentAuditRecord 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<GovernmentAuditRecord> selectGovernmentAuditRecordList(GovernmentAuditRecord governmentAuditRecord);

    /**
     * 新增【请填写功能名称】
     *
     * @param governmentAuditRecord 【请填写功能名称】
     * @return 结果
     */
    public int insertGovernmentAuditRecord(GovernmentAuditRecord governmentAuditRecord);

    /**
     * 修改【请填写功能名称】
     *
     * @param governmentAuditRecord 【请填写功能名称】
     * @return 结果
     */
    public int updateGovernmentAuditRecord(GovernmentAuditRecord governmentAuditRecord);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteGovernmentAuditRecordByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteGovernmentAuditRecordById(Integer id);

    List<GovernmentAuditRecord> selectGovernmentAuditRecordListByAuditId(Integer auditId);
}