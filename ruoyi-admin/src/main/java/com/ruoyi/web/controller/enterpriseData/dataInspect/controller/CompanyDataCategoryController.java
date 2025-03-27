package com.ruoyi.web.controller.enterpriseData.dataInspect.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.enterpriseData.dataInspect.domain.CompanyDataCategory;
import com.ruoyi.web.controller.enterpriseData.dataInspect.service.ICompanyDataCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 指标Controller
 *
 * @author ruoyi
 * @date 2024-10-26
 */
@RestController
@RequestMapping("/system/category")
public class CompanyDataCategoryController extends BaseController
{
    @Autowired
    private ICompanyDataCategoryService companyDataCategoryService;

    /**
     * 查询指标列表
     */
    @PreAuthorize("@ss.hasPermi('system:dictionary:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompanyDataCategory companyDataCategory)
    {
        startPage();
        List<CompanyDataCategory> list = companyDataCategoryService.selectCompanyDataCategoryList(companyDataCategory);
        return getDataTable(list);
    }

    /**
     * 导出指标列表
     */
    @PreAuthorize("@ss.hasPermi('system:dictionary:export')")
    @Log(title = "指标", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompanyDataCategory companyDataCategory)
    {
        List<CompanyDataCategory> list = companyDataCategoryService.selectCompanyDataCategoryList(companyDataCategory);
        ExcelUtil<CompanyDataCategory> util = new ExcelUtil<CompanyDataCategory>(CompanyDataCategory.class);
        util.exportExcel(response, list, "指标数据");
    }

    /**
     * 获取指标详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dictionary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(companyDataCategoryService.selectCompanyDataCategoryById(id));
    }

    /**
     * 新增指标
     */
    @PreAuthorize("@ss.hasPermi('system:dictionary:add')")
    @Log(title = "指标", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompanyDataCategory companyDataCategory)
    {
        return toAjax(companyDataCategoryService.insertCompanyDataCategory(companyDataCategory));
    }

    /**
     * 修改指标
     */
    @PreAuthorize("@ss.hasPermi('system:dictionary:edit')")
    @Log(title = "指标", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompanyDataCategory companyDataCategory)
    {
        return toAjax(companyDataCategoryService.updateCompanyDataCategory(companyDataCategory));
    }

    /**
     * 删除指标
     */
    @PreAuthorize("@ss.hasPermi('system:dictionary:remove')")
    @Log(title = "指标", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(companyDataCategoryService.deleteCompanyDataCategoryByIds(ids));
    }
}