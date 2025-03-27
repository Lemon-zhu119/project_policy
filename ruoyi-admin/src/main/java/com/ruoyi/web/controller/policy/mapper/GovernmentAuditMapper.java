package com.ruoyi.web.controller.policy.mapper;

import com.ruoyi.web.controller.policy.domain.GovernmentAudit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GovernmentAuditMapper
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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteGovernmentAuditById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGovernmentAuditByIds(Integer[] ids);

    int updateGovernmentAuditDelRecord(GovernmentAudit governmentAudit);
}