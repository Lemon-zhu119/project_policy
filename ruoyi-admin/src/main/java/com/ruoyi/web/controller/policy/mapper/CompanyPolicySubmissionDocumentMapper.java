package com.ruoyi.web.controller.policy.mapper;

import com.ruoyi.web.controller.policy.domain.CompanyPolicySubmissionDocument;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyPolicySubmissionDocumentMapper
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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCompanyPolicySubmissionDocumentById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompanyPolicySubmissionDocumentByIds(Integer[] ids);

    public List<CompanyPolicySubmissionDocument> selectCompanyPolicySubmissionDocumentBySubmissionId(Integer id);

    int deleteCompanyPolicySubmissionDocumentBySubmissionId(Integer submissionId);

    List<CompanyPolicySubmissionDocument> selectCompanyPolicySubmissionDocumentByName(@Param("id") Integer id, @Param("name") String name);
}
