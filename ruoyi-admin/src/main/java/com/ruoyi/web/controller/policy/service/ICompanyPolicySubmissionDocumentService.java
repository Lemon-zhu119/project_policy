package com.ruoyi.web.controller.policy.service;

import com.ruoyi.web.controller.policy.domain.CompanyPolicySubmissionDocument;

import java.util.List;

public interface ICompanyPolicySubmissionDocumentService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public CompanyPolicySubmissionDocument selectCompanyPolicySubmissionDocumentById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param companyPolicySubmissionDocument 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CompanyPolicySubmissionDocument> selectCompanyPolicySubmissionDocumentList(CompanyPolicySubmissionDocument companyPolicySubmissionDocument);

    /**
     * 新增【请填写功能名称】
     *
     * @param companyPolicySubmissionDocument 【请填写功能名称】
     * @return 结果
     */
    public int insertCompanyPolicySubmissionDocument(CompanyPolicySubmissionDocument companyPolicySubmissionDocument);

    /**
     * 修改【请填写功能名称】
     *
     * @param companyPolicySubmissionDocument 【请填写功能名称】
     * @return 结果
     */
    public int updateCompanyPolicySubmissionDocument(CompanyPolicySubmissionDocument companyPolicySubmissionDocument);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteCompanyPolicySubmissionDocumentByIds(Integer[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCompanyPolicySubmissionDocumentById(Integer id);

    public List<CompanyPolicySubmissionDocument> selectCompanyPolicySubmissionDocumentBySubmissionId(Integer id);

    int deleteCompanyPolicySubmissionDocumentBySubmissionId(Integer submissionId);

    List<CompanyPolicySubmissionDocument> selectCompanyPolicySubmissionDocumentByName(Integer id, String name);
}