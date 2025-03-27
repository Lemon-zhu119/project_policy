package com.ruoyi.web.controller.submission.mapper;

import com.ruoyi.web.controller.submission.domain.CompanyPolicySubmission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyPolicySubmissionMapper
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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCompanyPolicySubmissionById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompanyPolicySubmissionByIds(Integer[] ids);

    public List<CompanyPolicySubmission> selectCompanyPolicySubmissionDto();
}