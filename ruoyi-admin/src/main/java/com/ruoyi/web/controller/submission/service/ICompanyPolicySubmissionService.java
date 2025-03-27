package com.ruoyi.web.controller.submission.service;

import com.ruoyi.web.controller.submission.domain.CompanyPolicySubmission;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2024-11-01
 */
public interface ICompanyPolicySubmissionService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public CompanyPolicySubmission selectCompanyPolicySubmissionById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param companyPolicySubmission 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CompanyPolicySubmission> selectCompanyPolicySubmissionList(CompanyPolicySubmission companyPolicySubmission);

    /**
     * 新增【请填写功能名称】
     *
     * @param companyPolicySubmission 【请填写功能名称】
     * @return 结果
     */
    public int insertCompanyPolicySubmission(CompanyPolicySubmission companyPolicySubmission);

    /**
     * 修改【请填写功能名称】
     *
     * @param companyPolicySubmission 【请填写功能名称】
     * @return 结果
     */
    public int updateCompanyPolicySubmission(CompanyPolicySubmission companyPolicySubmission);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteCompanyPolicySubmissionByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCompanyPolicySubmissionById(Integer id);

    public List<CompanyPolicySubmission> selectCompanyPolicySubmissionDto();
}