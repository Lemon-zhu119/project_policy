package com.ruoyi.web.controller.company.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.controller.company.domain.*;
import com.ruoyi.web.controller.company.domain.dto.CompanyIndicator;
import com.ruoyi.web.controller.company.domain.dto.CompanyProperty;
import com.ruoyi.web.controller.company.service.*;
import com.ruoyi.web.controller.policy.domain.PolicyCategory;
import com.ruoyi.web.controller.policy.domain.PolicyCategoryData;
import com.ruoyi.web.controller.policy.domain.PolicyPropertiesData;
import com.ruoyi.web.controller.policy.domain.dto.PolicyDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/system/companyDetailData")
public class CompanyDetailController extends BaseController {

    @Autowired
    private ICompanyPropertyService companyDetailService;

    @Autowired
    private ICompanyIndicatorService companyIndicatorService;

    @Autowired
    private ICompanyCategoryRecordService companyCategoryRecordService;

    @Autowired
    private ICompanyCategoryDataService companyCategoryDataService;

    @Autowired
    private ICompanyCategoryService companyCategoryService;

    @Autowired
    private ICompanyIndicatorRecordService companyIndicatorRecordService;

    @Autowired
    private ICompanyIndicatorDictionaryService companyIndicatorDictionaryService;


    @PreAuthorize("@ss.hasPermi('system:companyDetailData:list')")
    @GetMapping("/propertyList")
    public TableDataInfo propertyList(CompanyProperty companyProperty) {
        startPage();
        return getDataTable(companyDetailService.selectCompanyPropertyDataList(companyProperty));
    }

    @PreAuthorize("@ss.hasPermi('system:companyDetailData:list')")
    @GetMapping("/indicatorList")
    public TableDataInfo indicatorList(CompanyIndicator companyIndicator) {
        startPage();
        return getDataTable(companyIndicatorService.selectCompanyIndicatorList(companyIndicator));
    }

    @PreAuthorize("@ss.hasPermi('system:companyDetailData:list')")
    @GetMapping("/propertyListById")
    public TableDataInfo propertyListById(Integer id) {
        startPage();
        return getDataTable(companyDetailService.selectCompanyPropertyDataListById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:companyDetailData:list')")
    @GetMapping("/indicatorListById")
    public TableDataInfo indicatorListById(Integer id) {
//        startPage();
        return getDataTable(companyIndicatorService.selectCompanyIndicatorListByCompanyId(id));
    }

    @PreAuthorize("@ss.hasPermi('system:companyDetailData:list')")
    @GetMapping("/propertyListByDtoId")
    public TableDataInfo propertyListByDtoId(Integer id) {
        List<CompanyCategoryData> companyCategoryData = new ArrayList<>();
        companyCategoryData.add(companyCategoryDataService.selectCompanyCategoryDataById(companyCategoryRecordService.selectCompanyCategoryRecordById(id).getCategoryDataId()));
        startPage();
        return getDataTable(companyCategoryData);
    }

    @PreAuthorize("@ss.hasPermi('system:companyDetailData:list')")
    @GetMapping("/indicatorListByDtoId")
    public TableDataInfo indicatorListByDtoId(Integer id) {
        List<CompanyIndicator> companyIndicators = new ArrayList<>();
        companyIndicators.add(companyIndicatorService.selectCompanyIndicatorListById(id));
        startPage();
        return getDataTable(companyIndicators);
    }

    @PreAuthorize("@ss.hasPermi('system:companyDetailData:edit')")
    @Log(title = "property_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/property")
    public AjaxResult propertyEdit(@RequestBody CompanyProperty companyProperty) {
        CompanyCategoryData companyCategoryData=new CompanyCategoryData();
        companyCategoryData.setContent(companyProperty.getContent());
        List<CompanyCategoryData> companyCategoryDataList=companyCategoryDataService.selectCompanyCategoryDataList(companyCategoryData);
        List<CompanyCategoryRecord> companyCategoryRecords=companyCategoryRecordService.selectCompanyCategoryRecordList(new CompanyCategoryRecord(null,companyProperty.getCompanyId(),companyProperty.getId(),null));
        if(companyCategoryDataList.isEmpty()){
            companyCategoryDataService.insertCompanyCategoryData(new CompanyCategoryData(null, companyProperty.getCategoryId(), companyProperty.getContent(),1));
            List<CompanyCategoryData> companyCategoryData1=companyCategoryDataService.selectCompanyCategoryDataList(new CompanyCategoryData(null,null,null,null));
            return toAjax(companyCategoryRecordService.updateCompanyCategoryRecord(new CompanyCategoryRecord(companyCategoryRecords.get(0).getId(),companyProperty.getCompanyId(),companyCategoryData1.get(companyCategoryData1.size()-1).getId(),1)));
        }else {
            return toAjax(companyCategoryRecordService.updateCompanyCategoryRecord(new CompanyCategoryRecord(companyCategoryRecords.get(0).getId(),companyProperty.getCompanyId(),companyCategoryDataList.get(0).getId(),1)));
        }
    }
    @PreAuthorize("@ss.hasPermi('system:companyDetailData:edit')")
    @Log(title = "indicator_edit", businessType = BusinessType.UPDATE)
    @PutMapping("/edit/indicator")
    public AjaxResult indicatorEdit(@RequestBody CompanyIndicatorRecord companyIndicatorRecord) {
        return toAjax(companyIndicatorRecordService.updateCompanyIndicatorRecord(companyIndicatorRecord));
    }

    @PreAuthorize("@ss.hasPermi('system:companyDetailData:add')")
    @Log(title = "indicator_add", businessType = BusinessType.INSERT)
    @PostMapping("/add/indicator")
    public AjaxResult addIndicator(@RequestBody CompanyIndicator companyIndicator) {
        List<CompanyIndicator> companyIndicators = companyIndicatorService.selectIndicatorListExact(companyIndicator);
        if (companyIndicators.size() != 0) {
            return AjaxResult.error("指标名称重复");
        }
        CompanyIndicatorDictionary companyIndicatorDictionary = companyIndicatorDictionaryService.selectCompanyIndicatorDictionaryByName(companyIndicator.getName());
        return toAjax(companyIndicatorRecordService.insertCompanyIndicatorRecord(new CompanyIndicatorRecord(null, companyIndicator.getCompanyId(), null, companyIndicatorDictionary.getId(),companyIndicator.getThreshold())));
    }

    @PreAuthorize("@ss.hasPermi('system:companyDetailData:add')")
    @Log(title = "property_add", businessType = BusinessType.INSERT)
    @PostMapping("/add/property")
    public AjaxResult addProperty(@RequestBody CompanyProperty companyProperty) {
        List<CompanyProperty> companyProperties = companyDetailService.selectPropertyListExact(companyProperty);
        if (companyProperties.size() != 0) {
            System.out.println(companyProperties);
            System.out.println(companyCategoryRecordService.selectCompanyCategoryRecordById(companyProperties.get(0).getId()));
            return toAjax(companyDetailService.updatePropertyDataByCategoryId(new CompanyCategoryData(null, companyCategoryRecordService.selectCompanyCategoryRecordById(companyProperties.get(0).getId()).getCategoryDataId(), companyProperty.getContent(), null)));
        }
        List<CompanyCategory> companyCategories = companyCategoryService.selectCompanyCategoryListByName(companyProperty.getName());
        if (companyCategories.size() == 0) {
            companyCategoryService.insertCompanyCategory(new CompanyCategory(null, null, companyProperty.getName(), 1));
        }
        CompanyCategoryData companyCategoryData=new CompanyCategoryData();
        companyCategoryData.setCategoryId(companyProperty.getCategoryId());
        companyCategoryData.setContent(companyProperty.getContent());
        List<CompanyCategoryData> companyCategoryDataList=companyCategoryDataService.selectCompanyCategoryDataList(companyCategoryData);
        if(companyCategoryDataList.isEmpty()){
            companyCategoryDataService.insertCompanyCategoryData(new CompanyCategoryData(null, companyCategoryService.selectCompanyCategoryListByName(companyProperty.getName()).get(0).getId(), companyProperty.getContent(), 1));
            List<CompanyCategoryData> companyCategoryData1 = companyCategoryDataService.selectCompanyCategoryDataList(new CompanyCategoryData(null, null, null, null));
            return toAjax(companyCategoryRecordService.insertCompanyCategoryRecord(new CompanyCategoryRecord(null, companyProperty.getCompanyId(), companyCategoryData1.get(companyCategoryData1.size() - 1).getId(), null)));
        }else{
            return toAjax(companyCategoryRecordService.insertCompanyCategoryRecord(new CompanyCategoryRecord(null, companyProperty.getCompanyId(),companyCategoryDataList.get(0).getId(),  null)));
        }
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:remove')")
    @Log(title = "property_remove", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/property/{id}")
    public AjaxResult removeProperty(@PathVariable Integer id) {
        return toAjax(companyCategoryRecordService.deleteCompanyCategoryRecordById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:policyContent:remove')")
    @Log(title = "indicator_remove", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/indicator/{id}")
    public AjaxResult removeIndicator(@PathVariable Integer id) {
        return toAjax(companyIndicatorRecordService.deleteCompanyIndicatorRecordById(id));
    }

}