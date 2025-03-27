package com.ruoyi.web.controller.api.controller;

import com.ruoyi.web.controller.api.common.Result;
import com.ruoyi.web.controller.api.domain.Dictionary;
import com.ruoyi.web.controller.api.domain.dto.CompanyDataDetail;
import com.ruoyi.web.controller.api.domain.dto.CompanyIndicatorRecordMap;
import com.ruoyi.web.controller.api.service.IDictionaryService;
import com.ruoyi.web.controller.company.domain.Company;
import com.ruoyi.web.controller.company.domain.CompanyIndicatorRecord;
import com.ruoyi.web.controller.company.service.ICompanyIndicatorDictionaryService;
import com.ruoyi.web.controller.company.service.ICompanyIndicatorRecordService;
import com.ruoyi.web.controller.company.service.ICompanyService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

import static org.apache.ibatis.ognl.OgnlOps.convertValue;

@RestController
@RequestMapping("/api/policy")
public class EnterpriseController {
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IDictionaryService dictionaryService;
    @Autowired
    private ICompanyIndicatorRecordService companyIndicatorRecordService;
    @Autowired
    private ICompanyIndicatorDictionaryService companyIndicatorDictionaryService;

    @GetMapping("/enterprisedata")
    public Result<Map<String, Object>> getUserInfo(@RequestParam Integer id, @RequestParam(required = false) Integer year) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        Integer localYear = LocalDate.now().getYear();
        Company company = companyService.selectCompanyListById(id).get(0);
        Map<String, Object> dictionary = new HashMap<>();
        company.setPreAddress(Arrays.asList(company.getAddress().split(";")));
        company.setAddress(company.getAddress().replace(";", ""));
        List<Dictionary> dictionaries = dictionaryService.getAll();
        for (Dictionary dictionary1 : dictionaries) {
            dictionary.put(dictionary1.getName(), dictionary1.getKey());
        }
        List<CompanyIndicatorRecordMap> companyIndicatorRecordMaps = companyIndicatorRecordService.selectCompanyIndicatorRecordListByCompanyIdAndYear(id, year);
        List<CompanyIndicatorRecordMap> companyIndicatorRecordLoopMaps=new ArrayList<>();
        if(companyIndicatorRecordMaps.isEmpty()){
            int loopYear = year;
//        如果查询年份>当前年份
            if (year > localYear) {
                //从查询年份一直找到2020年
                for (; loopYear >= localYear-5; loopYear--) {
                    //只要找到有记录就把遍历的年到查询年直接全部补全，并且存在一个逻辑：
                    //只要查到有年份的记录，那就把从那一年一直插，插到查询年
                    //并且在insert完之后进行查询来验证
                    companyIndicatorRecordLoopMaps = companyIndicatorRecordService.selectCompanyIndicatorRecordListByCompanyIdAndYear(id, loopYear);
                    if (!companyIndicatorRecordLoopMaps.isEmpty()) {
                        break;
                    }
                }
                if(loopYear>=localYear-5){
                    loopYear++;
                    for(; loopYear <= year; loopYear++){
                        for(CompanyIndicatorRecordMap companyIndicatorRecordMap:companyIndicatorRecordLoopMaps){
                            Object dictionaryId=companyIndicatorDictionaryService.selectCompanyIndicatorDictionaryByKey(companyIndicatorRecordMap.getKey());
                            CompanyIndicatorRecord companyIndicatorRecord=companyIndicatorRecordService.selectCompanyIndicatorRecordList(new CompanyIndicatorRecord(null,id,null,(Integer) dictionaryId,null,null,null,loopYear-1)).get(0);
                            companyIndicatorRecord.setYear(loopYear);
                            companyIndicatorRecordService.insertCompanyIndicatorRecord(companyIndicatorRecord);
                        }
                    }
                }
            } else if (year < localYear) {
                for (; loopYear <= localYear+5; loopYear++) {
                    //只要找到有记录就把遍历的年到查询年直接全部补全，并且存在一个逻辑：
                    //只要查到有年份的记录，那就把从那一年一直插，插到查询年
                    //并且在insert完之后进行查询来验证
                    companyIndicatorRecordLoopMaps = companyIndicatorRecordService.selectCompanyIndicatorRecordListByCompanyIdAndYear(id, loopYear);
                    if (!companyIndicatorRecordLoopMaps.isEmpty()) {
                        break;
                    }
                }
                if(loopYear<=localYear+5){
                    loopYear--;
                    for(; loopYear >= year; loopYear--){
                        for(CompanyIndicatorRecordMap companyIndicatorRecordMap:companyIndicatorRecordLoopMaps){
                            Object dictionaryId=companyIndicatorDictionaryService.selectCompanyIndicatorDictionaryByKey(companyIndicatorRecordMap.getKey());
                            CompanyIndicatorRecord companyIndicatorRecord=companyIndicatorRecordService.selectCompanyIndicatorRecordList(new CompanyIndicatorRecord(null,id,null,(Integer) dictionaryId,null,null,null,loopYear+1)).get(0);
                            companyIndicatorRecord.setYear(loopYear);
                            companyIndicatorRecordService.insertCompanyIndicatorRecord(companyIndicatorRecord);
                        }
                    }
                }
            }
        }
        //如果查询年份>当前年份
//        if (year > localYear) {
//            //从查询年份一直找到2020年
//            for (; loopYear >= 2020; loopYear--) {
//                //只要找到有记录就把遍历的年到查询年直接全部补全，并且存在一个逻辑：
//                //当查询几年前的数据有比当前年数更多的绑定时则覆盖绑定
//                companyIndicatorRecordLoopMaps = companyIndicatorRecordService.selectCompanyIndicatorRecordListByCompanyIdAndYear(id, loopYear);
//                if (companyIndicatorRecordLoopMaps.size() > companyIndicatorRecordMaps.size()) {
//                    companyIndicatorRecordMaps = companyIndicatorRecordService.selectCompanyIndicatorRecordListByCompanyIdAndYear(id, loopYear);
//                    break;
//                }
//            }
//            if(loopYear>=2020){
//                for(; loopYear <= year; loopYear--){
//
//                }
//            }
//        } else if (year < localYear) {
//            for (; loopYear <= 2028; loopYear++) {
//                companyIndicatorRecordLoopMaps = companyIndicatorRecordService.selectCompanyIndicatorRecordListByCompanyIdAndYear(id, loopYear);
//                if (companyIndicatorRecordLoopMaps.size() > companyIndicatorRecordMaps.size()) {
//                    companyIndicatorRecordMaps = companyIndicatorRecordService.selectCompanyIndicatorRecordListByCompanyIdAndYear(id, loopYear);
//                    break;
//                }
//            }
//        }
        companyIndicatorRecordMaps=companyIndicatorRecordService.selectCompanyIndicatorRecordListByCompanyIdAndYear(id, year);
        Map<String, Object> companyMap = PropertyUtils.describe(company);
        Map<String, Object> companyDictionary = new HashMap<>();
        int i = 0;
        for (Map.Entry<String, Object> entry : companyMap.entrySet()) {
            i++;
            companyDictionary.put(entry.getKey(), entry.getValue());
        }
        i = 0;
        outerLoop:
        for (Map.Entry<String, Object> entry : dictionary.entrySet()) {
            i++;
            for (CompanyIndicatorRecordMap companyIndicatorRecordMap : companyIndicatorRecordMaps) {
                if (entry.getValue().equals(companyIndicatorRecordMap.getKey())) {
//                    if (entry.getValue().equals("manage_level")) {
//                        String value = companyIndicatorRecordMap.getThreshold();
//                        String[] values = value.split(";");
//                        List<Double> doubleList = Arrays.stream(values)
//                                .map(Double::parseDouble) // 将每个字符串转换为double
//                                .collect(Collectors.toList()); // 收集结果到List
//                        companyDictionary.put("manage_level", doubleList);
//                        continue outerLoop;
//                    } else {
                    List<String> in = new ArrayList<>();
                    in.add("hangYe_xiShu");
                    in.add("product_domain_metrics");
                    in.add("leading_product_market_share");
                    if (in.contains(entry.getValue().toString())) {
                        Double threshold = Double.parseDouble(companyIndicatorRecordMap.getThreshold());
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        String formattedThreshold = decimalFormat.format(threshold);
                        companyDictionary.put(companyIndicatorRecordMap.getKey(), formattedThreshold);
                        continue outerLoop;
                    } else {
                        String TOR= (String) companyIndicatorRecordMap.getThresholdOrRemark();
                        if(companyIndicatorRecordMap.getThreshold()!=null){
                            Double DTOR=Double.parseDouble(TOR);
                            companyDictionary.put(companyIndicatorRecordMap.getKey(), DTOR.intValue());
                        }else {
                            companyDictionary.put(companyIndicatorRecordMap.getKey(), TOR);
                        }
                        continue outerLoop;
//                    }
                    }
                }
            }
            companyDictionary.put((String) entry.getValue(), null);
        }
        System.out.println(companyDictionary);
        return Result.success(companyDictionary);
    }

        @PostMapping("/enterprisedata")
//    @ResponseBody
    public Result insertCompany(@RequestBody Map<String, Object> indicatorMap) throws IntrospectionException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<String> record = new ArrayList<>();
        Company company = new Company();
        Integer year=LocalDate.now().getYear();
        CompanyDataDetail companyDataDetail = new CompanyDataDetail();
        Map<String, Object> loopMap=new HashMap<>();
        loopMap.putAll(indicatorMap);
        boolean yearProcessed = false;
        boolean idProcessed = false;
        for (Map.Entry<String, Object> entry : indicatorMap.entrySet()) {
            if (entry.getKey().equals("year") && !yearProcessed) {
                year = Integer.parseInt(String.valueOf(entry.getValue()));
                loopMap.remove("year");
                yearProcessed = true;
            } else if (entry.getKey().equals("id") && !idProcessed) {
                companyDataDetail.setCompanyId(Integer.parseInt(String.valueOf(entry.getValue())));
                loopMap.remove("id");
               idProcessed = true;
            }
            if (yearProcessed && idProcessed) {
                break; // 两个条件都处理完毕，退出循环
            }
        }
        companyDataDetail.setIndicatorMap(loopMap);
        if (companyDataDetail.getIndicatorMap().isEmpty() || companyDataDetail.getCompanyId() == null) {
            return Result.error("IndicatorMap或companyId输入有误请查看");
        }
        for (Map.Entry<String, Object> entry : companyDataDetail.getIndicatorMap().entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            Object dictionaryId = companyIndicatorDictionaryService.selectCompanyIndicatorDictionaryByKey(entry.getKey());
            List<String> remarkList = Arrays.asList(new String[]{
                    "enterprise_business",
                    "lead_field_circumstance",
                    "ent_guiMo",
                    "lead_filed",
                    "institution_level",
                    "direct_market_brand_advantage"
            });
            CompanyIndicatorRecord companyIndicatorRecord = new CompanyIndicatorRecord(null, companyDataDetail.getCompanyId(), null, (Integer) dictionaryId, null, null, null,year);
            List<CompanyIndicatorRecord> companyIndicatorRecordDb = companyIndicatorRecordService.selectCompanyIndicatorRecordList(companyIndicatorRecord);
            if (dictionaryId != null) {
                if (remarkList.contains(entry.getKey())) {
                    companyIndicatorRecord.setRemark(String.valueOf(entry.getValue()));
                } else {
                    companyIndicatorRecord.setThreshold(Double.parseDouble((String.valueOf(entry.getValue()))));
                }
                if (companyIndicatorRecordDb.isEmpty()) {
                    int insert = companyIndicatorRecordService.insertCompanyIndicatorRecord(companyIndicatorRecord);
                    if (insert == 1) {
                        record.add("插入" + entry.getKey() + "为" + entry.getValue());
                    } else {
                        return Result.error("提交成功插入失败");
                    }
                } else {
                    companyIndicatorRecord.setId(companyIndicatorRecordDb.get(0).getId());
                    int update = companyIndicatorRecordService.updateCompanyIndicatorRecord(companyIndicatorRecord);
                    if (update == 1) {
                        record.add("修改" + entry.getKey() + "为" + entry.getValue());
                    } else {
                        return Result.error("提交成功更新失败");
                    }
                }
            } else {
                String key = entry.getKey();
                Object value = entry.getValue();
                PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(Company.class).getPropertyDescriptors();
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    if (propertyDescriptor.getName().equals(entry.getKey())) {
                        try {
                            // 获取属性类型
                            Class<?> propertyType = propertyDescriptor.getPropertyType();
                            // 尝试将 value 转换为属性类型
                            Object convertedValue = convertValue(value, propertyType);
                            // 使用 PropertyUtils.setProperty 设置属性值
                            PropertyUtils.setProperty(company, key, convertedValue);
                            record.add("赋值了" + key + "=" + value + ",但我不知道成不成功");
                            if (company.getPreAddress() != null) {
                                company.setAddress(String.join(";", company.getPreAddress()));
                            }
                        } catch (Exception e) {
                            // 处理转换或设置属性值时的错误
                            System.out.println("赋值 " + key + " 时出现错误：" + e.getMessage());
                        }
                        break; // 找到匹配的属性，跳出循环
                    }
                }
            }
        }
        if (companyService.selectCompanyList(new Company(companyDataDetail.getCompanyId())).isEmpty()) {
            int insert = companyService.insertCompany(company);
            if (insert == 1) {
                record.add("基础属性插入成功");
            } else {
                return Result.error("基础属性插入失败");
            }
        } else {
            company.setId(companyDataDetail.getCompanyId());
            int update = companyService.updateCompany(company);
            if (update == 1) {
                record.add("基础属性修改成功");
            } else {
                return Result.error("基础属性修改失败");
            }
        }
        System.out.println(record);
        return Result.success(record);
    }
}
