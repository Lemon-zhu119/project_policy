package com.ruoyi.web.controller.company.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/companyClass")
public class CompanyDataClassController extends BaseController {
    @Autowired
    private ICompanyDataClassService companyDataClassService;

    @Autowired
    private ICompanyCategoryDataService companyCategoryDataService;

    @Autowired
    private ICompanyCategoryService companyCategoryService;


    @PreAuthorize("@ss.hasPermi('system:companyClass:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompanyDataClass companyDataClass) {
        startPage();
        List<CompanyDataClass> list = companyDataClassService.selectCompanyDataClassList(companyDataClass);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:companyClass:list')")
    @GetMapping("/categoryList")
    public TableDataInfo categoryList(CompanyCategory companyCategory) {
        startPage();
        List<CompanyCategory> list = companyCategoryService.selectCompanyCategoryList(companyCategory);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:companyClass:list')")
    @GetMapping("/categoryDataListByCategoryId")
    public TableDataInfo categoryDataListByCategoryId(@RequestParam Integer id) {
        startPage();
        return getDataTable(companyCategoryDataService.selectCompanyCategoryListDataByCategoryId(id));
    }

    @PreAuthorize("@ss.hasPermi('system:companyClass:list')")
    @GetMapping("/categoryDataListById")
    public TableDataInfo categoryDataListById(@RequestParam Integer id) {
        startPage();
        return getDataTable(companyCategoryDataService.selectCompanyCategoryListDataById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:companyClass:list')")
    @GetMapping("/categoryListById")
    public TableDataInfo categoryListById(@RequestParam Integer id) {
        startPage();
        return getDataTable(companyCategoryService.selectCompanyCategoryListById(id));
    }

    /**
     * 按id删除company_category_data中的数据
     *
     * @param id id
     * @return 是否成功
     */
    @PreAuthorize("@ss.hasPermi('system:companyClass:remove')")
    @Log(title = "category_data_remove", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/categoryData/{id}")
    public AjaxResult removeCategoryData(@PathVariable Integer id) {
        return toAjax(companyCategoryDataService.deleteCompanyCategoryDataById(id));
    }
//    @PreAuthorize("@ss.hasPermi('system:companyClass:remove')")
//    @Log(title = "category_data_removeBatch", businessType = BusinessType.DELETE)
//    @DeleteMapping("/delBatch/categoryData")
//    public AjaxResult removeCategoryDataBatch(@RequestBody List<Integer> ids)
//    {
//
//        return toAjax(companyCategoryDataService.deleteCompanyCategoryDataById(id));
//    }

    /**
     * 按id删除company_category_data中的数据
     *
     * @param id id
     * @return 是否成功
     */
    @PreAuthorize("@ss.hasPermi('system:companyClass:remove')")
    @Log(title = "category_remove", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/category/{id}")
    public AjaxResult removeCategory(@PathVariable Integer id) {
        List<CompanyCategoryData> companyCategoryDataList = companyCategoryDataService.selectCompanyCategoryListDataByCategoryId(id);
        if (companyCategoryDataList.isEmpty()) {
            return toAjax(companyCategoryService.deleteCompanyCategoryById(id));
        } else {

            return toAjax(companyCategoryDataService.deleteCompanyCategoryDataByCategoryId(id) & companyCategoryService.deleteCompanyCategoryById(id));
        }
    }

    /**
     * 修改company_category_data中的数据
     *
     * @param companyCategoryData 对应的单行数据
     * @return 是否成功
     */
    @PreAuthorize("@ss.hasPermi('system:companyClass:edit')")
    @Log(title = "category_data_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/categoryData")
    public AjaxResult categoryDataEdit(@RequestBody CompanyCategoryData companyCategoryData) {
        return toAjax(companyCategoryDataService.updateCompanyCategoryData(companyCategoryData));
    }

    /**
     * 增加company_category_data中的数据
     *
     * @param companyCategoryData 对应的单行数据
     * @return 是否成功
     */
    @PreAuthorize("@ss.hasPermi('system:companyClass:add')")
    @Log(title = "category_data_add", businessType = BusinessType.INSERT)
    @PostMapping("/add/categoryData")
    public AjaxResult addCategoryData(@RequestBody CompanyCategoryData companyCategoryData) {
        return toAjax(companyCategoryDataService.insertCompanyCategoryData(companyCategoryData));
    }

    /**
     * 增加company_category中的数据
     *
     * @param companyCategory 对应的单行数据
     * @return 是否成功
     */
    @PreAuthorize("@ss.hasPermi('system:companyClass:add')")
    @Log(title = "category_add", businessType = BusinessType.INSERT)
    @PostMapping("/add/category")
    public AjaxResult addCategory(@RequestBody CompanyCategory companyCategory) {
        System.out.println(companyCategory);
        return toAjax(companyCategoryService.insertCompanyCategory(companyCategory));
    }

    /**
     * 修改company_category中的数据
     *
     * @param companyCategory 对应的单行数据
     * @return 是否成功
     */
    @PreAuthorize("@ss.hasPermi('system:companyClass:edit')")
    @Log(title = "category_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/category")
    public AjaxResult categoryEdit(@RequestBody CompanyCategory companyCategory) {
        return toAjax(companyCategoryService.updateCompanyCategory(companyCategory));
    }
}