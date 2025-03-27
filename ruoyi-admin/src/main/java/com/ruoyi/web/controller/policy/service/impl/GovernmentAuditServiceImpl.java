package com.ruoyi.web.controller.policy.service.impl;

import com.ruoyi.web.controller.policy.domain.GovernmentAudit;
import com.ruoyi.web.controller.policy.mapper.GovernmentAuditMapper;
import com.ruoyi.web.controller.policy.service.IGovernmentAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GovernmentAuditServiceImpl implements IGovernmentAuditService
{
    @Autowired
    private GovernmentAuditMapper governmentAuditMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public GovernmentAudit selectGovernmentAuditById(Integer id)
    {
        return governmentAuditMapper.selectGovernmentAuditById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param governmentAudit 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<GovernmentAudit> selectGovernmentAuditList(GovernmentAudit governmentAudit)
    {
        return governmentAuditMapper.selectGovernmentAuditList(governmentAudit);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param governmentAudit 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertGovernmentAudit(GovernmentAudit governmentAudit)
    {
        return governmentAuditMapper.insertGovernmentAudit(governmentAudit);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param governmentAudit 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateGovernmentAudit(GovernmentAudit governmentAudit)
    {
        return governmentAuditMapper.updateGovernmentAudit(governmentAudit);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteGovernmentAuditByIds(Integer[] ids)
    {
        return governmentAuditMapper.deleteGovernmentAuditByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteGovernmentAuditById(Integer id)
    {
        return governmentAuditMapper.deleteGovernmentAuditById(id);
    }

    @Override
    public int updateGovernmentAuditDelRecord(GovernmentAudit governmentAudit) {
        return governmentAuditMapper.updateGovernmentAuditDelRecord(governmentAudit);
    }
}