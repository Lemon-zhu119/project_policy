package com.ruoyi.web.controller.policy.service;

import com.ruoyi.web.controller.policy.domain.GovernmentAudit;

import java.util.List;

public interface IGovernmentAuditService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public GovernmentAudit selectGovernmentAuditById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param governmentAudit 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<GovernmentAudit> selectGovernmentAuditList(GovernmentAudit governmentAudit);

    /**
     * 新增【请填写功能名称】
     *
     * @param governmentAudit 【请填写功能名称】
     * @return 结果
     */
    public int insertGovernmentAudit(GovernmentAudit governmentAudit);

    /**
     * 修改【请填写功能名称】
     *
     * @param governmentAudit 【请填写功能名称】
     * @return 结果
     */
    public int updateGovernmentAudit(GovernmentAudit governmentAudit);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteGovernmentAuditByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteGovernmentAuditById(Integer id);

    public int  updateGovernmentAuditDelRecord(GovernmentAudit governmentAudit);
}