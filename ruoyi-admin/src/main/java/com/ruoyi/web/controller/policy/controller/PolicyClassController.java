package com.ruoyi.web.controller.policy.controller;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.policy.domain.PolicyCategory;
import com.ruoyi.web.controller.policy.domain.PolicyCategoryData;
import com.ruoyi.web.controller.policy.domain.PolicyPropertiesData;
import com.ruoyi.web.controller.policy.service.IPolicyCategoryDataService;
import com.ruoyi.web.controller.policy.service.IPolicyCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

@RestController
@RequestMapping("/system/policyClass")
public class PolicyClassController extends BaseController {

    @Autowired
    private IPolicyCategoryService policyCategoryService;

    @Autowired
    private IPolicyCategoryDataService policyCategoryDataService;


    @PreAuthorize("@ss.hasPermi('system:policyClass:list')")
    @GetMapping("/categoryList")
    public TableDataInfo categoryList(PolicyCategory policyCategory)
    {
        startPage();
        return getDataTable(policyCategoryService.selectPolicyCategoryList(policyCategory));
    }

    @PreAuthorize("@ss.hasPermi('system:policyClass:list')")
    @GetMapping("/categoryListById")
    public TableDataInfo categoryListById(@RequestParam Integer id)
    {
        startPage();
        return getDataTable(policyCategoryService.selectPolicyCategoryListById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:policyClass:list')")
    @GetMapping("/categoryDataListById")
    public TableDataInfo categoryDataListById(@RequestParam Integer id)
    {
        startPage();
        return getDataTable(policyCategoryDataService.selectPolicyCategoryDataListById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:policyClass:list')")
    @GetMapping("/categoryDataListByCategoryId")
    public TableDataInfo categoryDataListByCategoryId(@RequestParam Integer id)
    {
        startPage();
        return getDataTable(policyCategoryDataService.selectPolicyCategoryDataListByCategoryId(id));
    }

    @PreAuthorize("@ss.hasPermi('system:policyClass:remove')")
    @Log(title = "policy_data_remove", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/categoryData/{id}")
    public AjaxResult removeCategoryData(@PathVariable Integer id)
    {
        return toAjax(policyCategoryDataService.deletePolicyCategoryDataById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:policyClass:remove')")
    @Log(title = "category_remove", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/category/{id}")
    public AjaxResult removeCategory(@PathVariable Integer id)
    {
        List<PolicyCategoryData> policyCategoryDataList=policyCategoryDataService.selectPolicyCategoryDataListByCategoryId(id);
        if(policyCategoryDataList.isEmpty()){
            return toAjax(policyCategoryService.deletePolicyCategoryById(id));
        }else {
            return toAjax(policyCategoryService.deletePolicyCategoryById(id) & policyCategoryDataService.deletePolicyCategoryDataByCategoryId(id));
        }

    }
    @PreAuthorize("@ss.hasPermi('system:policyClass:remove')")
    @Log(title = "category_removeBatch", businessType = BusinessType.DELETE)
    @DeleteMapping("/delBatch/policyCategory")
    public AjaxResult removePolicyBatch(@RequestBody List<Integer> ids) {
        for(Integer id :ids){
            return toAjax(policyCategoryService.deletePolicyCategoryById(id) & policyCategoryDataService.deletePolicyCategoryDataByCategoryId(id));
        }
        return AjaxResult.success("删除成功");
    }

    @PreAuthorize("@ss.hasPermi('system:policyClass:edit')")
    @Log(title = "category_data_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/categoryData")
    public AjaxResult categoryDataEdit(@RequestBody PolicyCategoryData policyCategoryData)
    {
        return toAjax(policyCategoryDataService.updatePolicyCategoryData(policyCategoryData));
    }

    @PreAuthorize("@ss.hasPermi('system:policyClass:add')")
    @Log(title = "category_data_add", businessType = BusinessType.INSERT)
    @PostMapping("/add/categoryData")
    public AjaxResult addCategoryData(@RequestBody PolicyCategoryData policyCategoryData)
    {
        return toAjax(policyCategoryDataService.insertPolicyCategoryData(policyCategoryData));
    }

    @PreAuthorize("@ss.hasPermi('system:policyClass:add')")
    @Log(title = "category_add", businessType = BusinessType.INSERT)
    @PostMapping("/add/category")
    public AjaxResult addCategory(@RequestBody PolicyCategory policyCategory)
    {
        return toAjax(policyCategoryService.insertPolicyCategory(policyCategory));
    }

    @PreAuthorize("@ss.hasPermi('system:policyClass:edit')")
    @Log(title = "category_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/category")
    public AjaxResult categoryEdit(@RequestBody PolicyCategory policyCategory)
    {
        return toAjax(policyCategoryService.updatePolicyCategory(policyCategory));
    }
}
