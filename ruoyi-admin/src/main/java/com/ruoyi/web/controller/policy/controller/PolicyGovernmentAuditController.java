package com.ruoyi.web.controller.policy.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.controller.ast.BitwiseParser;
import com.ruoyi.web.controller.company.domain.Company;
import com.ruoyi.web.controller.company.service.ICompanyIndicatorDictionaryService;
import com.ruoyi.web.controller.policy.domain.*;
import com.ruoyi.web.controller.policy.domain.dto.PolicyDetail;
import com.ruoyi.web.controller.policy.domain.dto.PolicyGovernmentAudit;
import com.ruoyi.web.controller.policy.service.*;
import com.ruoyi.web.controller.submission.domain.CompanyPolicySubmission;
import com.ruoyi.web.controller.submission.domain.dto.CompanyPolicySubmissionGa;
import com.ruoyi.web.controller.submission.service.ICompanyPolicySubmissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/system/policyGovernmentAudit")
public class PolicyGovernmentAuditController extends BaseController {

    @Autowired
    private IPolicyGovernmentAuditService policyGovernmentAuditService;

    @Autowired
    private IPolicyService policyService;

    @Autowired
    private IGovernmentAuditService governmentAuditService;

    @Autowired
    private ICompanyPolicySubmissionService companyPolicySubmissionService;

    @Autowired
    private ICompanyPolicySubmissionDocumentService companyPolicySubmissionDocumentService;


    @PreAuthorize("@ss.hasPermi('system:policyGovernmentAudit:list')")
    @GetMapping("/policyGovernmentAuditList")
    public TableDataInfo policyGovernmentAuditList(PolicyGovernmentAudit policyGovernmentAudit) {
        startPage();
        return getDataTable(policyGovernmentAuditService.selectPolicyGovernmentAuditList(policyGovernmentAudit));
    }

    @PreAuthorize("@ss.hasPermi('system:policyGovernmentAudit:list')")
    @GetMapping("/policyList")
    public TableDataInfo policyList(Policy policy) {
        startPage();
        return getDataTable(policyService.selectPolicyList(policy));
    }

    @PreAuthorize("@ss.hasPermi('system:policyGovernmentAudit:edit')")
    @Log(title = "policy_government_audit_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/policyGovernmentAudit")
    public AjaxResult policyGovernmentAuditEdit(@RequestBody PolicyGovernmentAudit policyGovernmentAudit) {
        GovernmentAudit governmentAudit = new GovernmentAudit(policyGovernmentAudit.getId(), policyGovernmentAudit.getSubmissionId(),
                policyGovernmentAudit.getAuditDepartment(),
                policyGovernmentAudit.getAuditStatus(), policyGovernmentAudit.getAuditAdvice(),
                policyGovernmentAudit.getAuditDate());
        return AjaxResult.success(governmentAuditService.updateGovernmentAudit(governmentAudit));
    }

    @PreAuthorize("@ss.hasPermi('system:policyGovernmentAudit:add')")
    @Log(title = "policy_government_audit_add", businessType = BusinessType.INSERT)
    @PostMapping("/add/policyGovernmentAudit")
    public AjaxResult addPolicyGovernmentAudit(@RequestBody PolicyGovernmentAudit policyGovernmentAudit) {
        List<Policy> policies = policyService.selectPolicyByName(policyGovernmentAudit.getName());
        GovernmentAudit governmentAudit = new GovernmentAudit(null, policies.get(0).getId(),
                policyGovernmentAudit.getAuditDepartment(), policyGovernmentAudit.getAuditStatus(),
                policyGovernmentAudit.getAuditAdvice(), policyGovernmentAudit.getAuditDate());
        return AjaxResult.success(governmentAuditService.insertGovernmentAudit(governmentAudit));
    }

    @PreAuthorize("@ss.hasPermi('system:document:list')")
    @GetMapping("/documentList")
    public TableDataInfo documentList(Integer id) {
        startPage();
        return getDataTable(companyPolicySubmissionDocumentService.selectCompanyPolicySubmissionDocumentBySubmissionId(id));
    }

    @PreAuthorize("@ss.hasPermi('system:policyGovernmentAudit:edit')")
    @Log(title = "company_policy_submission_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/companyPolicySubmission")
    public AjaxResult companyPolicySubmissionEdit(@RequestBody CompanyPolicySubmissionGa companyPolicySubmissionGa) {
        Date date = new Date();
        CompanyPolicySubmission companyPolicySubmission=new CompanyPolicySubmission();
        BeanUtils.copyProperties(companyPolicySubmissionGa,companyPolicySubmission);
        companyPolicySubmission.setDate(date);
        companyPolicySubmission.setProgressDescription(companyPolicySubmissionGa.getAuditAdvice());
        companyPolicySubmission.setSubmissionStatus(String.valueOf(companyPolicySubmissionGa.getAuditStatus()));
        GovernmentAudit governmentAudit=new GovernmentAudit();
        if(companyPolicySubmission.getSubmissionStatus().equals("0")){
            companyPolicySubmission.setMaterialStatus(String.valueOf(1));
        }
        int update= companyPolicySubmissionService.updateCompanyPolicySubmission(companyPolicySubmission);
        if(update==1&&companyPolicySubmission.getSubmissionStatus().equals("1")){
            governmentAudit.setId(null);
            governmentAudit.setAuditDepartment(companyPolicySubmissionGa.getAuditDepartment());
            governmentAudit.setSubmissionId(companyPolicySubmissionGa.getSubmissionId());
//            governmentAudit.setAuditAdvice(companyPolicySubmissionGa.getAuditAdvice());
            governmentAudit.setAuditDate(date);
//            governmentAudit.setAuditStatus(companyPolicySubmissionGa.getAuditStatus());
            int insert=governmentAuditService.insertGovernmentAudit(governmentAudit);
            if(insert==1){
                return AjaxResult.success("申报状态更新成功且进入审核流程");
            }else {
                return AjaxResult.error("申报状态更新成功,但未进入审核流程");
            }
        }else if (update==1){
            return AjaxResult.success("申报状态更新成功");
        }else {
            return AjaxResult.error("申报更新失败");
        }
    }
}
