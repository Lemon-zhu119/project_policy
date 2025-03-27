package com.ruoyi.web.controller.api.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.controller.company.domain.CompanyCategory;
import com.ruoyi.web.controller.company.domain.CompanyCategoryData;
import com.ruoyi.web.controller.company.domain.dto.CompanyDataClass;
import com.ruoyi.web.controller.company.service.ICompanyCategoryDataService;
import com.ruoyi.web.controller.company.service.ICompanyCategoryService;
import com.ruoyi.web.controller.company.service.ICompanyDataClassService;
import com.ruoyi.web.controller.policy.service.IPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/search")
public class SearchController extends BaseController {
//    @PreAuthorize("@ss.hasPermi('system:companyClass:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(Integer policyId) {
//        startPage();
//        List<CompanyDataClass> list = companyDataClassService.selectCompanyDataClassList(companyDataClass);
//        return getDataTable(list);
//    }

    @Autowired
    private IPolicyService policyService;

    @PreAuthorize("@ss.hasPermi('system:companyClass:list')")
    @GetMapping("/list")
    public AjaxResult updateFormula(@RequestParam Integer policyId,@RequestParam String formula) {
        policyService.updatePolicyFormula(policyId,formula);
        return AjaxResult.success();
    }
}