package com.ruoyi.web.controller.company.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.controller.company.domain.Company;
import com.ruoyi.web.controller.company.service.ICompanyCategoryService;
import com.ruoyi.web.controller.company.service.ICompanyIndicatorDictionaryService;
import com.ruoyi.web.controller.company.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/companyDetail")
public class CompanyDataDetailController extends BaseController {


    @Autowired
    private ICompanyService companyService;

    @Autowired
    private ICompanyCategoryService companyCategoryService;

    @Autowired
    private ICompanyIndicatorDictionaryService companyIndicatorDictionaryService;

    @PreAuthorize("@ss.hasPermi('system:companyDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(Company company) {
        startPage();
        companyService.selectCompanyList(company).forEach(System.out::println);
        return getDataTable(companyService.selectCompanyList(company));
    }

    @PreAuthorize("@ss.hasPermi('system:companyDetail:edit')")
    @Log(title = "company_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/company")
    public AjaxResult companyEdit(@RequestBody Company company) {
        return toAjax(companyService.updateCompany(company));
    }

    @PreAuthorize("@ss.hasPermi('system:companyDetail:add')")
    @Log(title = "company_add", businessType = BusinessType.INSERT)
    @PostMapping("/add/company")
    public AjaxResult addCompany(@RequestBody Company company) {
        return toAjax(companyService.insertCompany(company));
    }

    @PreAuthorize("@ss.hasPermi('system:companyDetail:remove')")
    @Log(title = "company_remove", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/company/{id}")
    public AjaxResult removeCompany(@PathVariable Integer id) {
        return toAjax(companyService.deleteCompanyById(id));
    }
    @PreAuthorize("@ss.hasPermi('system:companyDetail:remove')")
    @Log(title = "company_removeBatch", businessType = BusinessType.DELETE)
    @DeleteMapping("/delBatch/company")
    public AjaxResult removeCompanyBatch(@RequestBody List<Integer> ids) {
        for(Integer id:ids){
            toAjax(companyService.deleteCompanyById(id));
        }
        return AjaxResult.success("删除成功");
    }

    @PreAuthorize("@ss.hasPermi('system:companyDetail:list')")
    @GetMapping("/companyListById")
    public TableDataInfo companyListById(@RequestParam Integer id) {
        startPage();
        return getDataTable(companyService.selectCompanyListById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:companyDetail:list')")
    @GetMapping("/companyCategoryByCompanyId")
    public TableDataInfo categoryListByCompanyId(@RequestParam Integer id) {
        return getDataTable(companyCategoryService.selectCompanyCategoryByCompanyId(id));
    }

    @PreAuthorize("@ss.hasPermi('system:companyDetail:list')")
    @GetMapping("/companyIndicatorDictionaryByCompanyId")
    public TableDataInfo indicatorDictionaryListByCompanyId(@RequestParam Integer id) {
        return getDataTable(companyIndicatorDictionaryService.selectCompanyIndicatorDictionaryByCompanyId(id));
    }
}
