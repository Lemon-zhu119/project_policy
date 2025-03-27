package com.ruoyi.web.controller.enterpriseData.dataInspect.controller;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.enterpriseData.dataInspect.domain.CompanyDataCategory;
import com.ruoyi.web.controller.enterpriseData.dataInspect.domain.CompanyDataDictionary;
import com.ruoyi.web.controller.enterpriseData.dataInspect.service.ICompanyDataCategoryService;
import com.ruoyi.web.controller.enterpriseData.dataInspect.service.ICompanyDataDictionaryService;
import com.ruoyi.web.controller.enterpriseData.dataInspect.service.ICompanyDataIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * 指标Controller
 *
 * @author ruoyi
 * @date 2024-10-26
 */
@RestController
@RequestMapping("/system/enterpriseIndex")
public class CompanyDataIndexController extends BaseController
{
    @Autowired
    private ICompanyDataIndexService companyDataIndexService;

    @Autowired
    private ICompanyDataCategoryService companyDataCategoryService;

    @Autowired
    private ICompanyDataDictionaryService companyDataDictionary;

    /**
     * 查询指标列表
     */
    @PreAuthorize("@ss.hasPermi('system:enterpriseIndex:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompanyDataIndex companyDataIndex)
    {
        startPage();
        List<CompanyDataIndex> list = companyDataIndexService.selectCompanyDataIndexList(companyDataIndex);
        return getDataTable(list);
    }

    /**
     * 导出指标列表
     */
    @PreAuthorize("@ss.hasPermi('system:enterpriseIndex:export')")
    @Log(title = "指标", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompanyDataIndex companyDataIndex)
    {
        List<CompanyDataIndex> list = companyDataIndexService.selectCompanyDataIndexList(companyDataIndex);
        ExcelUtil<CompanyDataIndex> util = new ExcelUtil<CompanyDataIndex>(CompanyDataIndex.class);
        util.exportExcel(response, list, "指标数据");
    }

    /**
     * 获取指标详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:enterpriseIndex:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(companyDataIndexService.selectCompanyDataIndexById(id));
    }

    /**
     * 新增指标
     */
    @PreAuthorize("@ss.hasPermi('system:enterpriseIndex:add')")
    @Log(title = "指标", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompanyDataIndex companyDataIndex)
    {
        String categoryId = UUID.randomUUID().toString();
        String Id = UUID.randomUUID().toString();
        companyDataIndex.setCategoryId(categoryId);
        companyDataIndex.setId(Id);
        int categoryInsertResult = companyDataCategoryService.insertCompanyDataCategory(new CompanyDataCategory(companyDataIndex.categoryId, companyDataIndex.keyName));
        int dictionaryInsertResult = companyDataDictionary.insertCompanyDataDictionary(new CompanyDataDictionary(companyDataIndex.id,companyDataIndex.name,companyDataIndex.categoryId));
        return toAjax(categoryInsertResult & dictionaryInsertResult);
    }

    /**
     * 修改指标
     */
    @PreAuthorize("@ss.hasPermi('system:enterpriseIndex:edit')")
    @Log(title = "指标", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompanyDataIndex companyDataIndex)
    {
        return toAjax(companyDataCategoryService.updateCompanyDataCategory(new CompanyDataCategory(companyDataIndex.categoryId, companyDataIndex.keyName)) & companyDataDictionary.updateCompanyDataDictionary(new CompanyDataDictionary(companyDataIndex.id,companyDataIndex.name,companyDataIndex.categoryId)));
    }

    /**
     * 删除指标
     */
    @PreAuthorize("@ss.hasPermi('system:dictionary:remove')")
    @Log(title = "指标", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        List<String> categoryIds=new ArrayList<>();
        for (String id : ids) {
            categoryIds.add(companyDataDictionary.selectCompanyDataDictionaryById(id).getCategoryId());
        }

        return toAjax(companyDataCategoryService.deleteCompanyDataCategoryByIds(categoryIds.toArray(new String[0])) & companyDataDictionary.deleteCompanyDataDictionaryByIds(ids));
    }

    public static class CompanyDataIndex extends BaseEntity {
        private static final long serialVersionUID = 1L;

        private String id;

        /** 数据字典名称 */
        @Excel(name = "数据字典名称")
        private String name;

        /** 数据类别名称 */
        @Excel(name = "数据类别名称")
        private String keyName;

        /** 外键，关联数据类别表 */
        @Excel(name = "外键，关联数据类别表")
        private String categoryId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKeyName() {
            return keyName;
        }

        public void setKeyName(String keyName) {
            this.keyName = keyName;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public CompanyDataIndex(String id, String name, String keyName, String categoryId) {
            this.id = id;
            this.name = name;
            this.keyName = keyName;
            this.categoryId = categoryId;
        }

        public CompanyDataIndex() {
        }

        @Override
        public String toString() {
            return "CompanyDataIndex{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", keyName='" + keyName + '\'' +
                    ", categoryId=" + categoryId +
                    '}';
        }
    }
}