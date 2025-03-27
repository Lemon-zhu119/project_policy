package com.ruoyi.web.controller.submission.service.impl;

import com.ruoyi.web.controller.submission.domain.CompanyPolicySubmission;
import com.ruoyi.web.controller.submission.mapper.CompanyPolicySubmissionMapper;
import com.ruoyi.web.controller.submission.service.ICompanyPolicySubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-11-01
 */
@Service
public class CompanyPolicySubmissionServiceImpl implements ICompanyPolicySubmissionService
{
    @Autowired
    private CompanyPolicySubmissionMapper companyPolicySubmissionMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public CompanyPolicySubmission selectCompanyPolicySubmissionById(Integer id)
    {
        return companyPolicySubmissionMapper.selectCompanyPolicySubmissionById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param companyPolicySubmission 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CompanyPolicySubmission> selectCompanyPolicySubmissionList(CompanyPolicySubmission companyPolicySubmission)
    {
        return companyPolicySubmissionMapper.selectCompanyPolicySubmissionList(companyPolicySubmission);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param companyPolicySubmission 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCompanyPolicySubmission(CompanyPolicySubmission companyPolicySubmission)
    {
        return companyPolicySubmissionMapper.insertCompanyPolicySubmission(companyPolicySubmission);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param companyPolicySubmission 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCompanyPolicySubmission(CompanyPolicySubmission companyPolicySubmission)
    {
        return companyPolicySubmissionMapper.updateCompanyPolicySubmission(companyPolicySubmission);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCompanyPolicySubmissionByIds(Integer[] ids)
    {
        return companyPolicySubmissionMapper.deleteCompanyPolicySubmissionByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCompanyPolicySubmissionById(Integer id)
    {
        return companyPolicySubmissionMapper.deleteCompanyPolicySubmissionById(id);
    }

    @Override
    public List<CompanyPolicySubmission> selectCompanyPolicySubmissionDto() {
        return companyPolicySubmissionMapper.selectCompanyPolicySubmissionDto();
    }
}