package com.ruoyi.web.controller.policy.service.impl;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ruoyi.web.controller.company.domain.Company;
import com.ruoyi.web.controller.company.domain.CompanyIndicatorDictionary;
import com.ruoyi.web.controller.company.domain.CompanyIndicatorRecord;
import com.ruoyi.web.controller.company.mapper.CompanyIndicatorRecordMapper;
import com.ruoyi.web.controller.company.mapper.CompanyMapper;
import com.ruoyi.web.controller.company.service.ICompanyIndicatorDictionaryService;
import com.ruoyi.web.controller.policy.domain.Policy;
import com.ruoyi.web.controller.policy.domain.PolicyModelIndicatorRecord;
import com.ruoyi.web.controller.policy.domain.dto.PolicyModel;
import com.ruoyi.web.controller.policy.mapper.PolicyMapper;
import com.ruoyi.web.controller.policy.mapper.PolicyModelIndicatorRecordMapper;
import com.ruoyi.web.controller.policy.service.IPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PolicyServiceImpl implements IPolicyService {
    @Autowired
    private PolicyMapper policyMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyIndicatorRecordMapper companyIndicatorRecordMapper;

    @Autowired
    private PolicyModelIndicatorRecordMapper policyModelIndicatorRecordMapper;

    @Autowired
    private ICompanyIndicatorDictionaryService companyIndicatorDictionaryService;

    /**
     * 解析，拿到数字的位置和值
     *
     * @param str 待解析的字符串
     * @return 数字的位置和值
     */
    private static List<Result> parse(String str) {
        String regex = "\\b(?:\\d+(?:\\.\\d+)?|[\\d.]+(?:[eE][-+]?\\d+)?)\\b"; // 正则表达式，匹配一个或多个连续的数字
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        List<Result> results = new ArrayList<>();
        while (matcher.find()) {
            int position = matcher.start(); // 获取当前匹配的起始位置
            int length = matcher.group().length(); // 获取匹配的数字的长度
            results.add(new Result(matcher.group(), position, length)); // 直接存储匹配的字符串
        }
        return results;
    }

    /**
     * 运行qlExpress
     *
     * @param express 替换后的公式
     * @return 成功与否
     */
    private static boolean runExpress(String express) {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        Object r = null;
        try {
            r = runner.execute(express, context, null, true, false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (boolean) r;
    }

    public static void main(String[] args) {
        List<Result> results = parse("(1&2)|3");

        StringBuilder sb = new StringBuilder("(1&2)|3");
        for (int i = results.size() - 1; i >= 0; i--) {
            Result result = results.get(i);
            String fetchData = "(1=1)";
            sb.replace(result.position, result.position + result.length, fetchData); // 进行替换
        }
        String value = sb.toString()
                .replace("&", "&&")
                .replace("|", "||")
                .replace("=", "==");
        System.out.println(value);
        boolean result = runExpress(value);
        System.out.println(result);
    }

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Policy selectPolicyById(Integer id) {
        return policyMapper.selectPolicyById(id);
    }

    @Override
    public List<Policy> selectPolicyListById(Integer id) {
        return policyMapper.selectPolicyListById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param policy 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Policy> selectPolicyList(Policy policy) {
        List<Policy> policies = policyMapper.selectPolicyList(policy);
        return policies;
    }

    @Override
    public List<Policy> selectCustomModelList(Policy policy) {
        return policyMapper.selectCustomPolicyList(policy);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param policy 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertPolicyModelRecord(Policy policy) {
        //此处逻辑已在前端vue中进行处理
//        String policyDetail = policy.getDocDetail();
//        // 去除尾部空格
//        policyDetail = policyDetail.trim();
//        // 检查字符串末尾是否是分号，如果是，则去掉
//        if (policyDetail.endsWith(";")) {
//            policyDetail = policyDetail.substring(0, policyDetail.length() - 1);
//        }
//        // 使用分号";"作为分隔符来分割字符串
//        String[] parts = policyDetail.split(";");
//        // 计算最后一个分号之后的小字符串数量
//        int count = parts.length;
//        policy.setDocNumber(count);
        return policyMapper.insertPolicyModel(policy);
    }

    public int insertPolicyRecord(Policy policy) {
        return policyMapper.insertPolicy(policy);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param policy 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updatePolicy(Policy policy) {
        return policyMapper.updatePolicy(policy);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePolicyByIds(Long[] ids) {
        return policyMapper.deletePolicyByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePolicyById(Integer id) {
        return policyMapper.deletePolicyById(id);
    }

    @Override
    public int insertPolicyModelRecord(Integer policyId, Integer dictionaryId, String operator, double threshold) {
        return policyMapper.insertPolicyModelRecord(policyId, dictionaryId, operator, threshold);
    }

    @Override
    public int updatePolicyFormula(Integer policyId, String formula) {
        return policyMapper.updatePolicyFormula(policyId, formula);
    }

    @Override
    public List<PolicyModel> selectPolicyModelListByPolicyId(Integer policyId) {
        return policyMapper.selectPolicyModelListByPolicyId(policyId);
    }

    @Override
    public List<Company> selectMatchCompany(Integer policyId, String formula) {

        List<CompanyIndicatorRecord> companyIndicatorRecords = companyIndicatorRecordMapper.selectCompanyIndicatorRecordList(null);
        Set<Integer> companyIdSet = new HashSet<>();
        companyIndicatorRecords.forEach(companyIndicatorRecord -> {
            companyIdSet.add(companyIndicatorRecord.getCompanyId());
        });

        List<Company> resultCompanyList = new ArrayList<>();
        companyIdSet.forEach(id -> {
            CompanyIndicatorRecord companyIndicatorRecord = new CompanyIndicatorRecord();
            companyIndicatorRecord.setCompanyId(id);
            List<CompanyIndicatorRecord> templateCompanyIndicatorRecordList = companyIndicatorRecordMapper
                    .selectCompanyIndicatorRecordList(companyIndicatorRecord);
            List<PolicyModelIndicatorRecord> policyModelIndicatorRecords = policyModelIndicatorRecordMapper.selectPolicyModelIndicatorRecordByPolicyId(policyId);
            boolean result = true;
            for (PolicyModelIndicatorRecord pRecord : policyModelIndicatorRecords) {
                if (!result) {
                    break;
                }
                for (CompanyIndicatorRecord cRecord : templateCompanyIndicatorRecordList) {
                    if (cRecord.getDictionaryId() != pRecord.getIndicatorDictionaryId()) {
                        continue;
                    }
                    if (">".equals(pRecord.getOperator()) && !(cRecord.getThreshold() > pRecord.getThreshold())) {
                        result = false;
                        break;
                    } else if ("<".equals(pRecord.getOperator()) && !(cRecord.getThreshold() < pRecord.getThreshold())) {
                        result = false;
                        break;
                    } else if ("=".equals(pRecord.getOperator()) && !(cRecord.getThreshold() == pRecord.getThreshold())) {
                        result = false;
                        break;
                    }
                }
            }
            if (result) {
                resultCompanyList.add(companyMapper.selectCompanyListById(id).get(0));
            }
        });
        return resultCompanyList;
    }

    @Override
    public List<Company> selectMatchCompanyV2(Integer policyId, String formula) {

        List<CompanyIndicatorRecord> companyIndicatorRecords =
                companyIndicatorRecordMapper.selectCompanyIndicatorRecordList(null);
        Set<Integer> companyIdSet = new HashSet<>();
        companyIndicatorRecords.forEach(companyIndicatorRecord -> {
            companyIdSet.add(companyIndicatorRecord.getCompanyId());
        });

        List<Company> resultCompanyList = new ArrayList<>();
        companyIdSet.forEach(id -> {
            boolean result = false;
            String express = expressEngine(id, formula, policyId);
            try {
                result = runExpress(express);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (result) {
                resultCompanyList.add(companyMapper.selectCompanyListById(id).get(0));
            }
        });
        return resultCompanyList;
    }

    @Override
    public List<Company> selectMatchCompanyV3(Integer policyId, String formula) {

        List<CompanyIndicatorRecord> companyIndicatorRecords =
                companyIndicatorRecordMapper.selectCompanyIndicatorRecordList(null);
        Set<Integer> companyIdSet = new HashSet<>();
        companyIndicatorRecords.forEach(companyIndicatorRecord -> {
            companyIdSet.add(companyIndicatorRecord.getCompanyId());
        });

        List<Company> resultCompanyList = new ArrayList<>();
        companyIdSet.forEach(id -> {
            boolean result = false;
            String express = expressEngine(id, formula, policyId);
            try {
                result = runExpress(express);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (result) {
                List<Company> companies = companyMapper.selectCompanyListById(id);
                if(!companies.isEmpty()){
                    resultCompanyList.add(companies.get(0));
                }
            }
        });
        return resultCompanyList;
    }

    @Override
    public List<Policy> selectPolicyByClass(String policyType, String subPolicyType) {
        return policyMapper.selectPolicyByClass(policyType, subPolicyType);
    }

    @Override
    public List<PolicyModelIndicatorRecord> selectPolicyModelIndicatorRecordByPolicyId(Integer id) {
        return policyModelIndicatorRecordMapper.selectPolicyModelIndicatorRecordByPolicyId(id);
    }

    @Override
    public List<Policy> selectPolicyByName(String policyName) {
        return policyMapper.selectPolicyByName(policyName);
    }

    public List<Policy> selectPolicyByPublishDate() {
        return policyMapper.selectPolicyByPublishDate();
    }

    public List<Policy> selectPolicyByPublishDate1() {
        return policyMapper.selectPolicyByPublishDate1();
    }

    @Override
    public List<Policy> selectPolicyByRec() {
        return policyMapper.selectPolicyByRec();
    }

    @Override
    public List<Policy> selectPolicyByHotLevel() {
        return policyMapper.selectPolicyByHotLevel();
    }

    /**
     * 把任意一条cRecord转成运算公式
     *
     * @param id        dictionary_id
     * @param companyId company_id
     * @return 单一的运算公式
     */
    private String fetch(Integer id, Integer companyId, Integer policyId) {
        Policy policy=policyMapper.selectPolicyById(policyId);
        Integer year =Integer.parseInt(policy.getName().substring(0,4));
        List<CompanyIndicatorRecord> companyIndicatorRecords = companyIndicatorRecordMapper.selectCompanyIndicatorRecordListByCompanyIdAndYearV1(id,year);

        CompanyIndicatorRecord cRecord = new CompanyIndicatorRecord();
        for (CompanyIndicatorRecord companyIndicatorRecord : companyIndicatorRecords) {
            if (companyIndicatorRecord.getCompanyId() == companyId) {
                cRecord = companyIndicatorRecord;
                break;
            }
        }

        PolicyModelIndicatorRecord pRecord = policyModelIndicatorRecordMapper
                .selectPolicyModelIndicatorRecord(policyId, id);

        String complexFormula = pRecord.getComplexFormula();

        if (complexFormula != null && !complexFormula.isEmpty()) {
            List<Result> results = parse(complexFormula);
            StringBuilder sb = new StringBuilder(complexFormula);
            for (int i = 0; i <= results.size() - 1; i++) {
                int dictionaryId = Integer.parseInt(results.get(i).number);
                CompanyIndicatorRecord companyIndicatorRecord = companyIndicatorRecordMapper.selectCompanyIndicatorRecordList
                                (new CompanyIndicatorRecord(null, companyId, null, dictionaryId, null))
                        .get(0);
                Double threshold =companyIndicatorRecord.getThreshold();
                sb.replace(results.get(i).position, results.get(i).position + results.get(i).length, threshold.toString()); // 进行替换
                results = parse(sb.toString());
            }
            return "(" + sb.toString() + pRecord.getOperator() + pRecord.getThreshold() + ")";
        } else {
            if (pRecord.getId() != null && cRecord.getId() != null) {
                return "(" + cRecord.getThreshold().toString()
                        + pRecord.getOperator()
                        + pRecord.getThreshold().toString() + ")";
            }
            return "0";
        }
    }

    /**
     * 替换公式的索引为具体要求
     *
     * @param companyId companyId
     * @param formula   公式
     * @return 替换后的结果
     */
    private String expressEngine(Integer companyId, String formula, Integer policyId) {
        List<Result> results = parse(formula);
        StringBuilder sb = new StringBuilder(formula);

        for (int i = results.size() - 1; i >= 0; i--) {
            Result result = results.get(i);
            String fetchData;
            try {
                Integer indicatorDictionaryId = policyModelIndicatorRecordMapper.selectPolicyModelIndicatorRecordById(Integer.valueOf(result.number)).getIndicatorDictionaryId();
                fetchData = fetch(indicatorDictionaryId, companyId, policyId);
                sb.replace(result.position, result.position + result.length, fetchData); // 进行替换
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("~~~~~~~~~~~~最后匹配的公式：" + sb.toString() + "~~~~~~~~~~~~");
        return sb.toString().replace("&", "&&").replace("|", "||");
    }

    @Override
    public List<Map<String, String>> c2cNoMatchedIndicator(Integer companyId, Policy policy, Integer year) {
        String formula = policy.getFormula(); //formula的数字代表pmir的id
        List<Map<String, String>> result = new ArrayList<>();
        if (formula != null) {
            List<Result> parse = parse(formula);
            for (Result r : parse) {
                PolicyModelIndicatorRecord policyModelIndicatorRecord = policyModelIndicatorRecordMapper
                        .selectPolicyModelIndicatorRecordById(Integer.valueOf(r.number));
                List<CompanyIndicatorRecord> companyIndicatorRecordList = companyIndicatorRecordMapper
                        .selectCompanyIndicatorRecordList(new CompanyIndicatorRecord(companyId, policyModelIndicatorRecord.getIndicatorDictionaryId(),year));
                List<CompanyIndicatorRecord> companyIndicatorRecords=new ArrayList<>();
                for(CompanyIndicatorRecord companyIndicatorRecord:companyIndicatorRecordList){
                    if(companyIndicatorRecord.getYear().equals(year)&&companyIndicatorRecord.getThreshold()!=null){
                        companyIndicatorRecords.add(companyIndicatorRecord);
                    }
                }
                if (!companyIndicatorRecords.isEmpty()) {
                    String complexFormula = policyModelIndicatorRecord.getComplexFormula();
                    if (complexFormula == null||complexFormula.equals("")) {
                        String stringBuilder = companyIndicatorRecords.get(0).getThreshold() +
                                policyModelIndicatorRecord.getOperator() +
                                policyModelIndicatorRecord.getThreshold();
                        boolean isSuccess = runExpress(stringBuilder);
                        if (!isSuccess) {
                            StringBuilder expectSb = new StringBuilder();
                            CompanyIndicatorDictionary companyIndicatorDictionary = companyIndicatorDictionaryService.selectCompanyIndicatorDictionaryById(policyModelIndicatorRecord.getIndicatorDictionaryId());
                            expectSb.append(companyIndicatorDictionary.getName())
                                    .append(policyModelIndicatorRecord.getOperator())
                                    .append(policyModelIndicatorRecord.getThreshold());
                            Map<String, String> map = new HashMap<>();
                            map.put("expectIndicator", expectSb.toString());
                            String yourSb = companyIndicatorDictionary.getName() +
                                    "=" +
                                    companyIndicatorRecords.get(0).getThreshold();
                            map.put("yourIndicator", yourSb);
                            result.add(map);
                        }
                    } else {
                        List<Result> parseComplex = parse(complexFormula);
                        StringBuilder sb = new StringBuilder();
                        Double yourIndicatorValue = 0d;
                        Map<String, String> map = new HashMap<>();
                        StringBuilder indicatorName = new StringBuilder();
                        for (Result resultComplex : parseComplex) {
                            indicatorName.append(companyIndicatorDictionaryService.selectCompanyIndicatorDictionaryById(Integer.valueOf(resultComplex.number)).getName()).append("+");
                            List<CompanyIndicatorRecord> cirs = companyIndicatorRecordMapper.selectCompanyIndicatorRecordList(new CompanyIndicatorRecord(companyId, Integer.valueOf(resultComplex.number)));
                            if (!cirs.isEmpty()) {
                                if (cirs.get(0).getThreshold() != null) {
                                    yourIndicatorValue += cirs.get(0).getThreshold();
                                }
                            }
                        }
                        indicatorName.setLength(indicatorName.length() - 1);
                        sb.append(yourIndicatorValue)
                                .append(policyModelIndicatorRecord.getOperator())
                                .append(policyModelIndicatorRecord.getThreshold());
                        boolean isSuccess = runExpress(sb.toString());
                        if (!isSuccess) {
                            String expectSb = indicatorName +
                                    policyModelIndicatorRecord.getOperator() +
                                    policyModelIndicatorRecord.getThreshold();
                            map.put("expectIndicator", expectSb);
                            String yourSb = indicatorName +
                                    "=" +
                                    yourIndicatorValue;
                            map.put("yourIndicator", yourSb);
                            result.add(map);
                        }
                    }
                } else {
                    Map<String, String> map = new HashMap<>();
                    StringBuilder expectSb = new StringBuilder();
                    CompanyIndicatorDictionary companyIndicatorDictionary = companyIndicatorDictionaryService.selectCompanyIndicatorDictionaryById(policyModelIndicatorRecord.getIndicatorDictionaryId());
                    expectSb.append(companyIndicatorDictionary.getName())
                            .append(policyModelIndicatorRecord.getOperator())
                            .append(policyModelIndicatorRecord.getThreshold());
                    map.put("expectIndicator", expectSb.toString());
                    map.put("yourIndicator", "NULL");
                    result.add(map);
                }
            }
        }
        return result;
    }

    static class Result {
        String number; // 存储数字，可能是整数或小数
        int position;
        int length;

        public Result(String number, int position, int length) {
            this.number = number;
            this.position = position;
            this.length = length;
        }
    }
}

