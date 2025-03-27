package com.ruoyi.web.controller.policy.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.controller.policy.domain.GovernmentAudit;
import com.ruoyi.web.controller.policy.domain.GovernmentAuditRecord;
import com.ruoyi.web.controller.policy.domain.Policy;
import com.ruoyi.web.controller.policy.service.ICompanyPolicySubmissionDocumentService;
import com.ruoyi.web.controller.policy.service.IGovernmentAuditRecordService;
import com.ruoyi.web.controller.policy.service.IGovernmentAuditService;
import com.ruoyi.web.controller.policy.service.IPolicyService;
import com.ruoyi.web.controller.submission.service.ICompanyPolicySubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/governmentAuditRecord")
public class PolicyGovernmentAuditRecordController extends BaseController {

    @Autowired
    private IPolicyService policyService;
    @Autowired
    private IGovernmentAuditService governmentAuditService;

    @Autowired
    private IGovernmentAuditRecordService governmentAuditRecordService;

    @Autowired
    private ICompanyPolicySubmissionService companyPolicySubmissionService;

    @Autowired
    private ICompanyPolicySubmissionDocumentService companyPolicySubmissionDocumentService;


    @PreAuthorize("@ss.hasPermi('system:governmentAuditRecord:list')")
    @GetMapping("/governmentAuditRecordList")
    public TableDataInfo governmentAuditRecordList(GovernmentAuditRecord governmentAuditRecord) {
        startPage();
        return getDataTable(governmentAuditRecordService.selectGovernmentAuditRecordList(governmentAuditRecord));
    }

    @PreAuthorize("@ss.hasPermi('system:governmentAuditRecord:list')")
    @GetMapping("/policyList")
    public TableDataInfo policyList(Policy policy) {
        startPage();
        return getDataTable(policyService.selectPolicyList(policy));
    }

    @PreAuthorize("@ss.hasPermi('system:governmentAuditRecord:list')")
    @GetMapping("/selectGovernmentAuditRecordById")
    public AjaxResult policyListById(@RequestParam Integer id) {
        return AjaxResult.success(governmentAuditRecordService.selectGovernmentAuditRecordById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:governmentAuditRecord:list')")
    @GetMapping("/selectGovernmentAuditRecordByAuditId")
    public TableDataInfo selectGovernmentAuditRecordByAuditId(@RequestParam Integer auditId) {
        startPage();
        return getDataTable(governmentAuditRecordService.selectGovernmentAuditRecordListByAuditId(auditId));
    }

    @PreAuthorize("@ss.hasPermi('system:governmentAuditRecord:edit')")
    @Log(title = "policy_government_audit_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/governmentAuditRecord")
    public AjaxResult governmentAuditRecordEdit(@RequestBody GovernmentAuditRecord governmentAuditRecord) {
        GovernmentAudit governmentAuditDb = governmentAuditService.selectGovernmentAuditById(governmentAuditRecord.getAuditId());
        GovernmentAuditRecord governmentAuditRecordDb = governmentAuditRecordService.selectGovernmentAuditRecordById(governmentAuditRecord.getId());
        if (governmentAuditDb.getAuditStatus() == 1 && governmentAuditRecordDb.getAuditStatus() == 1 && governmentAuditRecord.getAuditStatus() != 1) {
            governmentAuditService.updateGovernmentAuditDelRecord(new GovernmentAudit(governmentAuditRecordDb.getAuditId(), null, null, null, null, null));
            governmentAuditRecordService.updateGovernmentAuditRecord(governmentAuditRecord);
            return AjaxResult.success("返回审核中成功");
        } else if (governmentAuditRecord.getAuditStatus() == 1) {
            governmentAuditRecordService.updateGovernmentAuditRecord(governmentAuditRecord);
            governmentAuditService.updateGovernmentAudit(new GovernmentAudit(governmentAuditRecord.getAuditId(), null, null, governmentAuditRecord.getAuditStatus(), governmentAuditRecord.getAuditAdvice(), null, governmentAuditRecord.getAuditDate()));
            return AjaxResult.success("修改为已完结成功");
        } else {
            governmentAuditRecordService.updateGovernmentAuditRecord(governmentAuditRecord);
            return AjaxResult.success("修改状态成功");
        }
    }

    @PreAuthorize("@ss.hasPermi('system:governmentAuditRecord:add')")
    @Log(title = "government_audit_record_add", businessType = BusinessType.INSERT)
    @PostMapping("/add/governmentAuditRecord")
    public AjaxResult addGovernmentAuditRecord(@RequestBody GovernmentAuditRecord governmentAuditRecord) {
        int insert = governmentAuditRecordService.insertGovernmentAuditRecord(governmentAuditRecord);
        if (insert == 1) {
            if (governmentAuditRecord.getAuditStatus() == 1) {
                governmentAuditService.updateGovernmentAudit(new GovernmentAudit(governmentAuditRecord.getAuditId(), null, null, governmentAuditRecord.getAuditStatus(), governmentAuditRecord.getAuditAdvice(), null, governmentAuditRecord.getAuditDate()));
            }
            return AjaxResult.success(insert);
        } else {
            return AjaxResult.error("插入失败");
        }
    }

    @PreAuthorize("@ss.hasPermi('system:document:list')")
    @GetMapping("/documentList")
    public TableDataInfo documentList(Integer id) {
        startPage();
        return getDataTable(companyPolicySubmissionDocumentService.selectCompanyPolicySubmissionDocumentBySubmissionId(id));
    }

    @PreAuthorize("@ss.hasPermi('system:document:delete')")
    @DeleteMapping("/del/governmentAuditRecord/{id}")
    public AjaxResult removeGovernmentAuditRecord(@PathVariable Integer id) {
        GovernmentAuditRecord governmentAuditRecord = governmentAuditRecordService.selectGovernmentAuditRecordById(id);
        if (governmentAuditRecord != null) {
            if (governmentAuditRecord.getAuditStatus() == 1) {
                GovernmentAudit governmentAudit = governmentAuditService.selectGovernmentAuditById(governmentAuditRecord.getAuditId());
                int del = governmentAuditRecordService.deleteGovernmentAuditRecordById(id);
                if (del == 1) {
                    return AjaxResult.success(governmentAuditService.updateGovernmentAuditDelRecord(new GovernmentAudit(governmentAudit.getId(), null, null, null, null, null)));
                } else {
                    return AjaxResult.error("删除错误");
                }
            } else {
                return toAjax(governmentAuditRecordService.deleteGovernmentAuditRecordById(id));
            }
        } else {
            return AjaxResult.error("删除失败");
        }
    }

//    @PreAuthorize("@ss.hasPermi('system:governmentAuditRecord:edit')")
//    @Log(title = "company_policy_submission_edit", businessType = BusinessType.UPDATE)
//    @PutMapping("/edit/companyPolicySubmission")
//    public AjaxResult companyPolicySubmissionEdit(@RequestBody CompanyPolicySubmissionGa companyPolicySubmissionGa) {
//        Date date = new Date();
//        CompanyPolicySubmission companyPolicySubmission=new CompanyPolicySubmission();
//        BeanUtils.copyProperties(companyPolicySubmissionGa,companyPolicySubmission);
//        companyPolicySubmission.setDate(date);
//        companyPolicySubmission.setProgressDescription(companyPolicySubmissionGa.getAuditAdvice());
//        companyPolicySubmission.setSubmissionStatus(String.valueOf(companyPolicySubmissionGa.getAuditStatus()));
//        GovernmentAuditRecord governmentAuditRecord=new GovernmentAuditRecord();
//        int update= companyPolicySubmissionService.updateCompanyPolicySubmission(companyPolicySubmission);
//        if(update==1&&companyPolicySubmission.getSubmissionStatus().equals("1")){
////            governmentAuditRecord.setId(null);
////            governmentAuditRecord.setAuditDepartment(companyPolicySubmissionGa.getAuditDepartment());
////            governmentAuditRecord.setSubmissionId(companyPolicySubmissionGa.getSubmissionId());
////            governmentAuditRecord.setAuditAdvice(companyPolicySubmissionGa.getAuditAdvice());
////            governmentAuditRecord.setAuditDate(date);
////            governmentAuditRecord.setAuditStatus(companyPolicySubmissionGa.getAuditStatus());
////            int insert=governmentAuditRecordService.insertGovernmentAuditRecord(governmentAuditRecord);
//            return AjaxResult.success("申报状态更新成功");
//        }else if (update==1){
//            return AjaxResult.success("申报状态更新成功");
//        }else {
//            return AjaxResult.error("申报更新失败");
//        }
//    }
}
