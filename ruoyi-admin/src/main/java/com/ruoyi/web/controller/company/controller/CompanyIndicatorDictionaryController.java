package com.ruoyi.web.controller.company.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.company.domain.CompanyIndicatorDictionary;
import com.ruoyi.web.controller.company.service.ICompanyIndicatorDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/system/dictionary")
public class CompanyIndicatorDictionaryController extends BaseController
{
    @Autowired
    private ICompanyIndicatorDictionaryService companyIndicatorDictionaryService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:dictionary:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompanyIndicatorDictionary companyIndicatorDictionary)
    {
        startPage();
        List<CompanyIndicatorDictionary> list = companyIndicatorDictionaryService.selectCompanyIndicatorDictionaryList(companyIndicatorDictionary);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:dictionary:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompanyIndicatorDictionary companyIndicatorDictionary)
    {
        List<CompanyIndicatorDictionary> list = companyIndicatorDictionaryService.selectCompanyIndicatorDictionaryList(companyIndicatorDictionary);
        ExcelUtil<CompanyIndicatorDictionary> util = new ExcelUtil<CompanyIndicatorDictionary>(CompanyIndicatorDictionary.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dictionary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(companyIndicatorDictionaryService.selectCompanyIndicatorDictionaryById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:dictionary:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompanyIndicatorDictionary companyIndicatorDictionary)
    {
        return toAjax(companyIndicatorDictionaryService.insertCompanyIndicatorDictionary(companyIndicatorDictionary));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:dictionary:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompanyIndicatorDictionary companyIndicatorDictionary)
    {
        return toAjax(companyIndicatorDictionaryService.updateCompanyIndicatorDictionary(companyIndicatorDictionary));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:dictionary:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(companyIndicatorDictionaryService.deleteCompanyIndicatorDictionaryByIds(ids));
    }
}