package com.ruoyi.web.controller.policy.service.impl;

import com.ruoyi.web.controller.policy.domain.CompanyPolicySubmissionDocument;
import com.ruoyi.web.controller.policy.mapper.CompanyPolicySubmissionDocumentMapper;
import com.ruoyi.web.controller.policy.service.ICompanyPolicySubmissionDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyPolicySubmissionDocumentServiceImpl implements ICompanyPolicySubmissionDocumentService
{
    @Autowired
    private CompanyPolicySubmissionDocumentMapper companyPolicySubmissionDocumentMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public CompanyPolicySubmissionDocument selectCompanyPolicySubmissionDocumentById(Integer id)
    {
        return companyPolicySubmissionDocumentMapper.selectCompanyPolicySubmissionDocumentById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param companyPolicySubmissionDocument 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CompanyPolicySubmissionDocument> selectCompanyPolicySubmissionDocumentList(CompanyPolicySubmissionDocument companyPolicySubmissionDocument)
    {
        return companyPolicySubmissionDocumentMapper.selectCompanyPolicySubmissionDocumentList(companyPolicySubmissionDocument);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param companyPolicySubmissionDocument 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCompanyPolicySubmissionDocument(CompanyPolicySubmissionDocument companyPolicySubmissionDocument)
    {
        return companyPolicySubmissionDocumentMapper.insertCompanyPolicySubmissionDocument(companyPolicySubmissionDocument);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param companyPolicySubmissionDocument 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCompanyPolicySubmissionDocument(CompanyPolicySubmissionDocument companyPolicySubmissionDocument)
    {
        return companyPolicySubmissionDocumentMapper.updateCompanyPolicySubmissionDocument(companyPolicySubmissionDocument);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCompanyPolicySubmissionDocumentByIds(Integer[] ids)
    {
        return companyPolicySubmissionDocumentMapper.deleteCompanyPolicySubmissionDocumentByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCompanyPolicySubmissionDocumentById(Integer id)
    {
        return companyPolicySubmissionDocumentMapper.deleteCompanyPolicySubmissionDocumentById(id);
    }

    @Override
    public List<CompanyPolicySubmissionDocument> selectCompanyPolicySubmissionDocumentBySubmissionId(Integer id) {
        return companyPolicySubmissionDocumentMapper.selectCompanyPolicySubmissionDocumentBySubmissionId(id);
    }

    @Override
    public int deleteCompanyPolicySubmissionDocumentBySubmissionId(Integer submissionId) {
        return companyPolicySubmissionDocumentMapper.deleteCompanyPolicySubmissionDocumentBySubmissionId(submissionId);
    }

    @Override
    public List<CompanyPolicySubmissionDocument> selectCompanyPolicySubmissionDocumentByName(Integer id, String name) {
        return companyPolicySubmissionDocumentMapper.selectCompanyPolicySubmissionDocumentByName(id,name);
    }
}