package com.ruoyi.web.controller.submission.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.controller.submission.service.ICompanyPolicySubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.ruoyi.common.utils.PageUtils.startPage;

@RestController
@RequestMapping("/system/policySubmission")
public class CompanyPolicySubmissionController extends BaseController {

    @Autowired
    private ICompanyPolicySubmissionService companyPolicySubmissionService;
    ;

    @PreAuthorize("@ss.hasPermi('system:policySubmission:list')")
    @GetMapping("/list")
    public TableDataInfo policySubmissionList()
    {
        return getDataTable(companyPolicySubmissionService.selectCompanyPolicySubmissionDto());
    }

    @PreAuthorize("@ss.hasPermi('system:policySubmission:remove')")
    @Log(title = "policy_submission_remove", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/policySubmission/{id}")
    public AjaxResult removeCategoryData(@PathVariable Integer id)
    {
        return toAjax(companyPolicySubmissionService.deleteCompanyPolicySubmissionById(id));
    }

}
