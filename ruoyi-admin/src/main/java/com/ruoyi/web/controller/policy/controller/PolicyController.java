package com.ruoyi.web.controller.policy.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.controller.ast.BitwiseParser;
import com.ruoyi.web.controller.company.domain.Company;
import com.ruoyi.web.controller.company.service.ICompanyIndicatorDictionaryService;
import com.ruoyi.web.controller.company.service.ICompanyService;
import com.ruoyi.web.controller.policy.domain.*;
import com.ruoyi.web.controller.policy.domain.dto.PolicyDetail;
import com.ruoyi.web.controller.policy.domain.dto.PolicyModel;
import com.ruoyi.web.controller.policy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/system/policyContent")
public class PolicyController extends BaseController {

    @Autowired
    private IPolicyService policyService;

    @Autowired
    private IPolicyPropertiesDataService policyPropertiesDataService;

    @Autowired
    private IPolicyCategoryDataService policyCategoryDataService;

    @Autowired
    private IPolicyDetailService policyDetailService;

    @Autowired
    private ICompanyIndicatorDictionaryService companyIndicatorDictionaryService;

    @Autowired
    private IPolicyModelIndicatorRecordService policyModelIndicatorRecordService;

    @Autowired
    private IPolicyCategoryService policyCategoryService;

    @Autowired
    private ICompanyService companyService;

    @PreAuthorize("@ss.hasPermi('system:policyContent:list')")
    @GetMapping("/policyList")
    public TableDataInfo policyList(Policy policy) {
        startPage();
        return getDataTable(policyService.selectPolicyList(policy));
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:list')")
    @GetMapping("/customModelList")
    public TableDataInfo customModelList(Policy policy) {
//        startPage();
        Integer year= LocalDate.now().getYear();
        List<Policy> policies = policyService.selectCustomModelList(new Policy(1));
        Integer finalYear = year;
        policies = policies.stream()
                .filter(policyDB -> policyDB.getName().startsWith(String.valueOf(finalYear)))
                .collect(Collectors.toList());
        return getDataTable(policies);
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:edit')")
    @Log(title = "policy_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/policy")
    public AjaxResult policyEdit(@RequestBody Policy policy) {
        return toAjax(policyService.updatePolicy(policy));
    }

    @PreAuthorize("@ss.hasPermi('system:policyModelDocDetail:edit')")
    @Log(title = "policyModelDocDetail_edit", businessType = BusinessType.UPDATE)
    @GetMapping("/edit/policyModelDocDetail")
    public AjaxResult updatePolicyModelDocDetail(@RequestParam Integer policyId, @RequestParam String docDetail) {
        Policy policy =new Policy();
        policy.setId(policyId);
        policy.setDocDetail(docDetail);
        List<String> docDetailList= Arrays.asList(docDetail.split(";"));
        policy.setDocNumber(docDetailList.size());
        return toAjax(policyService.updatePolicy(policy));
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:add')")
    @Log(title = "policy_add", businessType = BusinessType.INSERT)
    @PostMapping("/add/policy")
    public AjaxResult addPolicy(@RequestBody Policy policy) {
        return toAjax(policyService.insertPolicyRecord(policy));
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:add')")
    @Log(title = "policyModel_add", businessType = BusinessType.INSERT)
    @PostMapping("/add/policyModelRecord")
    public AjaxResult addPolicyModel(@RequestBody Policy policy) {
        return toAjax(policyService.insertPolicyModelRecord(policy));
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:list')")
    @GetMapping("/policyListById")
    public TableDataInfo policyListById(@RequestParam Integer id) {
        startPage();
        return getDataTable(policyService.selectPolicyListById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:remove')")
    @Log(title = "policy_remove", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/policy/{id}")
    public AjaxResult removePolicy(@PathVariable Integer id) {
        List<PolicyPropertiesData> policyPropertiesDataList = policyPropertiesDataService.selectPolicyPropertiesDataByPolicyId(id);
        policyPropertiesDataList.forEach((policyPropertiesData -> {
            policyCategoryDataService.deletePolicyCategoryDataById(policyPropertiesData.getPolicyCategoryDataId());
        }));
        return toAjax(policyService.deletePolicyById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:remove')")
    @Log(title = "policy_removeBatch", businessType = BusinessType.DELETE)
    @DeleteMapping("/delBatch/policy")
    public AjaxResult removePolicyBatch(@RequestBody List<Integer> ids) {
        for(Integer id :ids){
            List<PolicyPropertiesData> policyPropertiesDataList = policyPropertiesDataService.selectPolicyPropertiesDataByPolicyId(id);
            policyPropertiesDataList.forEach((policyPropertiesData -> {
                policyCategoryDataService.deletePolicyCategoryDataById(policyPropertiesData.getPolicyCategoryDataId());
            }));
            policyService.deletePolicyById(id);
        }
        return AjaxResult.success("删除成功");
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:list')")
    @GetMapping("/detailList")
    public TableDataInfo detailList(Integer id) {
        startPage();
        List<PolicyPropertiesData> policyPropertiesDataList = policyPropertiesDataService.selectPolicyPropertiesDataByPolicyId(id);
        return getDataTable(policyDetailService.selectPolicyDetailListByPolicyId(id));
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:edit')")
    @Log(title = "policy_detail_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/policyDetail")
    public AjaxResult policyDetailEdit(@RequestBody PolicyDetail policyDetail) {
        PolicyCategoryData policyCategoryData = policyCategoryDataService.selectPolicyCategoryDataById(policyDetail.getPolicyCategoryDataId());
        if (policyCategoryData == null) {
            return AjaxResult.error("二级目录绑定错误");
        }
        List<PolicyCategoryData> policyCategoryDataList = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, policyCategoryData.getPolicyCategoryId(), policyDetail.getContent(), null));
        if (policyCategoryDataList.isEmpty()) {
            policyCategoryDataService.insertPolicyCategoryData(new PolicyCategoryData(null, policyCategoryData.getPolicyCategoryId(), policyDetail.getContent(), null));
            List<PolicyCategoryData> policyCategoryDatas = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, null, null, null));
            return toAjax(policyPropertiesDataService.updatePolicyPropertiesData(new PolicyPropertiesData(policyDetail.getPolicyPropertiesDataId(), policyDetail.getPolicyId(), policyCategoryDatas.get(policyCategoryDatas.size() - 1).getId(), null)));
        } else {
            return toAjax(policyPropertiesDataService.updatePolicyPropertiesData(new PolicyPropertiesData(policyDetail.getPolicyPropertiesDataId(), policyDetail.getPolicyId(), policyCategoryDataList.get(0).getId(), null)));
        }
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:add')")
    @Log(title = "policy_detail_add", businessType = BusinessType.INSERT)
    @PostMapping("/add/policyDetail")
    public AjaxResult addPolicyDetail(@RequestBody PolicyDetail policyDetail) {
        List<PolicyDetail> policyDetails = policyDetailService.selectPolicyDetailListExact(policyDetail);
        if (policyDetails.size() != 0) {
            return toAjax(policyCategoryDataService.updatePolicyCategoryData(new PolicyCategoryData(null, policyDetails.get(0).getId(), policyDetail.getContent(), null)));
        }
        List<PolicyCategory> policyCategories = policyCategoryService.selectPolicyCategoryListExact(new PolicyCategory(null, policyDetail.getName(), null));
        if (policyCategories.size() == 0) {
            policyCategoryService.insertPolicyCategory(new PolicyCategory(null, policyDetail.getName(), 1));
            PolicyCategory policyCategory = policyCategoryService.selectPolicyCategoryByName(policyDetail.getName());
            if (policyCategory != null) {
                policyCategories.add(0, policyCategory);
            }
        }
        PolicyCategoryData policyCategoryData = new PolicyCategoryData(null, policyCategories.get(0).getId(), policyDetail.getContent(), 1);
        List<PolicyCategoryData> policyCategoryDataList = policyCategoryDataService.selectPolicyCategoryDataList(policyCategoryData);
        if (policyCategoryDataList.isEmpty()) {
            policyCategoryDataService.insertPolicyCategoryData(policyCategoryData);
            List<PolicyCategoryData> policyCategoryDatas = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, null, null, null));
            return toAjax(policyPropertiesDataService.insertPolicyPropertiesData(new PolicyPropertiesData(null, policyDetail.getPolicyId(), policyCategoryDatas.get(policyCategoryDatas.size() - 1).getId(), null)));
        } else {
            return toAjax(policyPropertiesDataService.insertPolicyPropertiesData(new PolicyPropertiesData(null, policyDetail.getPolicyId(), policyCategoryDataList.get(0).getId(), null)));

        }

    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:remove')")
    @Log(title = "policy_detail_remove", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/policyDetail/{id}")
    public AjaxResult removePolicyDetail(@PathVariable Integer id) {
        return toAjax(policyPropertiesDataService.deletePolicyPropertiesDataById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:add')")
    @Log(title = "policy_model_add", businessType = BusinessType.INSERT)
    @PostMapping("/add/policyModel")
    public AjaxResult addPolicyModelRecord(@RequestParam Integer policyId, @RequestParam Integer dictionaryId,
                                           @RequestParam String operator, @RequestParam double threshold
    ) {
        policyModelIndicatorRecordService.insertPolicyModelIndicatorRecord(new PolicyModelIndicatorRecord(null, policyId, dictionaryId, HtmlUtils.htmlUnescape(operator), threshold, null));
        PolicyModelIndicatorRecord policyModelIndicatorRecord = policyModelIndicatorRecordService.selectPolicyModelIndicatorRecord(policyId, dictionaryId);

        String formula = policyService.selectPolicyById(policyId).getFormula();
        if (formula == null || formula.isEmpty()) {
            formula = String.valueOf(policyModelIndicatorRecord.getId());
        } else {
            formula += "&" + policyModelIndicatorRecord.getId();
        }
        policyService.updatePolicyFormula(policyId, formula);
        return AjaxResult.success();
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:list')")
    @GetMapping("/modelDetailList")
    public TableDataInfo modelDetailList(Integer id) {
        startPage();
        List<PolicyModel> policyModels = policyService.selectPolicyModelListByPolicyId(id);
        policyModels.forEach(policyModel -> {
            if (policyModel.getComplexFormula() != null) {
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(policyModel.getComplexFormula());
                List<String> categoryName = new ArrayList<>();

                while (matcher.find()) {
                    categoryName.add(companyIndicatorDictionaryService.selectCompanyIndicatorDictionaryById(Integer.parseInt(matcher.group())).getName());
                }
                policyModel.setComplexFormulaList(categoryName);
            }
        });
        return getDataTable(policyModels);
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:edit')")
    @Log(title = "policy_formula_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/policyFormula")
    public AjaxResult policyFormulaEdit(@RequestBody Policy policy) {
        System.out.println(policy);
        String result = null;
        try {
            result = BitwiseParser.ast(policy.getFormula());
            if (result.equals("Success")) {
                policyService.updatePolicyFormula(policy.getId(), policy.getFormula());
                return AjaxResult.success(result);
            }
        } catch (RuntimeException r) {
            result = r.getMessage();
        }
        return AjaxResult.error("公式语法错误：" + result);
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:list')")
    @GetMapping("/dataItemList")
    public TableDataInfo dataItemList(Integer policyId) {
        return getDataTable(companyIndicatorDictionaryService.selectCompanyIndicatorDictionaryListDistinct(policyId));
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:remove')")
    @Log(title = "policy_model_remove", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/model/{id}")
    public AjaxResult removePolicyModel(@PathVariable Integer id) {

        Policy policy = policyService.selectPolicyById(policyModelIndicatorRecordService.selectPolicyModelIndicatorRecordById(id).getPolicyId());

        String formula = policy.getFormula();
        if (formula == null || formula.isEmpty()) {
            return toAjax(policyModelIndicatorRecordService.deletePolicyModelIndicatorRecordById(id));
        }
        int lastIndex = formula.lastIndexOf("&");
        String newFormula;
        if (lastIndex == -1) {
            newFormula = "";
        } else {
            newFormula = updateFormula(formula, String.valueOf(id));
        }
        policyService.updatePolicyFormula(policy.getId(), newFormula);
        return toAjax(policyModelIndicatorRecordService.deletePolicyModelIndicatorRecordById(id));
    }
    @PreAuthorize("@ss.hasPermi('system:policyModelContent:remove')")
    @Log(title = "policy_policyModel_remove", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/policyModel/{id}")
    public AjaxResult removeModel(@PathVariable Integer id) {
        return toAjax(policyService.deletePolicyById(id));
    }
    @PreAuthorize("@ss.hasPermi('system:policyContent:list')")
    @GetMapping("/customModelFutureList")
    public TableDataInfo customFutureModelList(Policy policy) {
//        startPage();
        Integer year= LocalDate.now().getYear()+1;
        List<Policy> policies = policyService.selectCustomModelList(new Policy(1));
        Integer finalYear = year;
        policies = policies.stream()
                .filter(policyDB -> policyDB.getName().startsWith(String.valueOf(finalYear)))
                .collect(Collectors.toList());
        return getDataTable(policies);
    }
    @PreAuthorize("@ss.hasPermi('system:policyContent:edit')")
    @Log(title = "policy_model_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/policyModel")
    public AjaxResult policyModelEdit(@RequestBody PolicyModelIndicatorRecord policyModelIndicatorRecord) {
        policyModelIndicatorRecord.setOperator(HtmlUtils.htmlUnescape(policyModelIndicatorRecord.getOperator()));
        return toAjax(policyModelIndicatorRecordService.updatePolicyModelIndicatorRecord(policyModelIndicatorRecord));
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:list')")
    @GetMapping("/matchCompanyList")
    public TableDataInfo matchCompanyList(@RequestParam Integer policyId, @RequestParam(required = false) String formula) {
        if (formula == null || formula.isEmpty()) {
            return getDataTable(companyService.selectCompanyList(null));
        }
        List<Company> companies = policyService.selectMatchCompanyV3(policyId, formula);
        return getDataTable(companies);
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:list')")
    @GetMapping("/policyCategoryList")
    public TableDataInfo policyCategoryList(Integer id) {
        return getDataTable(policyCategoryService.selectCategoryListByPolicyId(id));
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:edit')")
    @Log(title = "complex_formula_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/complexFormula")
    public AjaxResult complexFormulaEdit(@RequestBody PolicyModel policyModel) {
        List<String> complexFormulaList = policyModel.getComplexFormulaList();
        List<String> idList = new ArrayList<>();
        for (String cf : complexFormulaList) {
            Integer id = companyIndicatorDictionaryService.selectCompanyIndicatorDictionaryByName(cf).getId();
            idList.add(String.valueOf(id));
        }
        String complexFormula = String.join("+", idList);
        PolicyModelIndicatorRecord policyModelIndicatorRecord = new PolicyModelIndicatorRecord();
        policyModelIndicatorRecord.setId(policyModel.getId());
        policyModelIndicatorRecord.setComplexFormula(complexFormula);
        return toAjax(policyModelIndicatorRecordService.updatePolicyModelIndicatorRecord(policyModelIndicatorRecord));
    }
    private String updateFormula(String formula,String id){
        String result = formula;
        // 处理各种可能的位置
        result = result.replaceAll(id + "&", "")  // 处理 "id&..."
                .replaceAll("&" + id, "")   // 处理 "...&id"
                .replaceAll("^" + id + "$", ""); // 处理只有一个id的情况
        return result;
    }
}
