package com.ruoyi.web.controller.api.controller;

import com.ruoyi.web.controller.api.common.Result;
import com.ruoyi.web.controller.api.domain.*;
import com.ruoyi.web.controller.api.domain.dto.PolicyAPIDto;
import com.ruoyi.web.controller.api.domain.dto.PolicyDtoResult;
import com.ruoyi.web.controller.api.service.*;
import com.ruoyi.web.controller.policy.domain.Policy;
import com.ruoyi.web.controller.policy.service.IPolicyCategoryDataService;
import com.ruoyi.web.controller.policy.service.IPolicyCategoryService;
import com.ruoyi.web.controller.policy.service.IPolicyService;
import com.ruoyi.web.controller.submission.domain.CompanyPolicySubmission;
import com.ruoyi.web.controller.submission.service.ICompanyPolicySubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/policy")
public class PolicyDtoController {
    @Autowired
    private PolicyLibraryService policyLibraryService;
    @Autowired
    private PolicyClassService policyClassService;
    @Autowired
    private EntNatureDataService entNatureDataService;
    @Autowired
    private PolicyMatchService policyMatchService;
    @Autowired
    private CompanySubmissionStatusService companySubmissionStatusService;

    @Autowired
    private IPolicyService policyService;

    @Autowired
    private ICompanyPolicySubmissionService companyPolicySubmissionService;
    @Autowired
    private GovernMatchService governMatchService;
    @Autowired
    private IPolicyCategoryService policyCategoryService;
    @Autowired
    private IPolicyCategoryDataService policyCategoryDataService;

    @PostMapping("/policyinfo")
    public Result<PolicyRes> getUserInfo(
            @RequestBody PolicySelect policySelect
//            @RequestParam List<PolicyDtoResult> categories, @RequestParam String query1,
//                                         @RequestParam(required = false) String query2,
//                                         @RequestParam List<String> timePeriod
//                                         @RequestParam Integer pagenum,
//                                         @RequestParam Integer pagesize
//            @RequestParam(required = false) List<PolicyDtoResult> categories,
//            @RequestParam(required = false) String query2,
//            @RequestParam(required = false) String query1,
//            @RequestParam(required = false) List<String> timePeriod
    ) {
        if (policySelect.getCategories() == null) {
            policySelect.setCategories(new ArrayList<>());
        }
        if (policySelect.getTimePeriod() == null) {
            policySelect.setTimePeriod(new ArrayList<>());
        }
        List<Date> dateList = new ArrayList<>();
        List<PolicyAPIDto> policyAPIDtoList = policyClassService.selectPolicyByClass(policyClassService.findPolicyClassById(1), policySelect.getCategories());
        policyAPIDtoList = policyClassService.sortPolicyByQuery(policyAPIDtoList, policySelect.getQuery1());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 根据需要调整日期格式
        if (!(policySelect.getQuery2() == null || policySelect.getQuery2().equals(""))) {
            policyAPIDtoList = policyClassService.selectPolicyByName(policyAPIDtoList, policySelect.getQuery2());
        }
        for (String dateStr : policySelect.getTimePeriod()) {
            try {
                Date date = dateFormat.parse(dateStr);
                dateList.add(date);
            } catch (ParseException e) {
                e.printStackTrace();
                // 处理解析错误，例如返回错误信息或抛出异常
            }
        }
        if (!(dateList == null || dateList.size() == 0)) {
            policyAPIDtoList = policyClassService.selectPolicyByTime(policyAPIDtoList, dateList);
        }
        PolicyRes policyRes = new PolicyRes();
        policyRes.setTotal(policyAPIDtoList.size());
        policyRes.setRow(policyAPIDtoList);
        return Result.success(policyRes);
    }

    @GetMapping("/getpolicydetail/{id}")
    public Result<PolicyRes> getPolicyById(@PathVariable("id") Integer policyId) {
        List<PolicyAPIDto> policyAPIDtoList = policyClassService.findPolicyClassById(1);
        List<PolicyAPIDto> policyAPIDtos = new ArrayList<>();
        for (PolicyAPIDto policyAPIDto : policyAPIDtoList) {
            if (policyAPIDto.getId().equals(policyId)) {
                policyAPIDtos.add(policyAPIDto);
            }
        }
        PolicyRes policyRes = new PolicyRes(policyAPIDtos.size(), policyAPIDtos);
        return Result.success(policyRes);
    }

    @GetMapping("/vistiedNumber/{id}")
    public Result addHotLevel(@PathVariable("id") Integer policyId) {
        Policy policy = policyService.selectPolicyById(policyId);
        policy.setHotLevel(policy.getHotLevel() + 1);
        int update = policyService.updatePolicy(policy);
        if (update == 1) {
            return Result.success("添加浏览量成功");
        } else {
            return Result.error("添加浏览量失败");
        }
    }

    @GetMapping("/policystatus")
    public Result<PolicyLibrary> getPolicyLibrary() {
        PolicyLibrary policyLibrary = policyLibraryService.getAll();
        return Result.success(policyLibrary);
    }

    @GetMapping("/matchN")
    public Result<MatchN> getMatchN() {
        MatchN matchN = policyLibraryService.getMatchN();
        matchN.setMatchN(matchN.getMatchN() + 1);
        int update = policyLibraryService.updateMatchN(matchN);
        return Result.success(matchN);
    }

    @GetMapping("/userVisitN")
    public Result<UserVisitN> getUserVisitN() {
        UserVisitN userVisitN = policyLibraryService.getUserVisitN();
        userVisitN.setUserVisitN(userVisitN.getUserVisitN() + 1);
        int update = policyLibraryService.updateUserVisitN(userVisitN);
        return Result.success(userVisitN);
    }

    @GetMapping("/policyclassinfo")
    public Result<List<PolicyDtoResult>> getAll() {
        List<PolicyDtoResult> policyDtoResults = new ArrayList<>();
        Map<String, List<String>> maps = new HashMap<>();
        List<PolicyClass> policyClass = policyClassService.getAll();
        for (int i = 0; i < policyClass.size(); i++) {
            List<String> all = new ArrayList<>();
            List<String> list = new ArrayList<>(Arrays.asList(policyClass.get(i).getContent().split(",")));
            Collator collator = Collator.getInstance(Locale.CHINA);
            Collections.sort(list, (o1, o2) -> collator.compare(o1, o2));
            if (policyClass.get(i).getContent().contains("全部") && policyClass.get(i).getContent().contains("其他")) {
                list.remove("全部");
                list.remove("其他");
                list.add(0, "全部");
                list.add("其他");
            } else if (policyClass.get(i).getContent().contains("其他")) {
                list.remove("其他");
                list.add(0, "全部");
                list.add("其他");
            } else if (policyClass.get(i).getContent().contains("全部")) {
                list.remove("全部");
                list.add(0, "全部");
                list.add("其他");
            } else {
                list.add(0, "全部");
                list.add("其他");
            }
            if (policyClass.get(i).getName().equals("applicableArea")) {
                if (policyClass.get(i).getContent().contains("国家级")) {
                    list.remove("国家级");
                    list.add(1, "国家级");
                }
            }
            policyDtoResults.add(new PolicyDtoResult(i, policyClass.get(i).getName(), list));
        }
        return Result.success(policyDtoResults);
    }

    @GetMapping("/policyhobby")
    public Result<List<PolicyDtoResult>> getPolicyHobby(@RequestParam Integer companyId) {
        List<PolicyDtoResult> policyDtoResults = new ArrayList<>();
        List<PolicyClass> policyClassList = new ArrayList<>();
        List<PolicyClass> allHobby = policyClassService.getAllHobby(companyId);
        if (allHobby.get(0).getContent() == null || allHobby.get(0).getContent().equals("")) {
            return Result.success(policyDtoResults);
        }
        String[] hobbiesId = allHobby.get(0).getContent().split(";");
        List<PolicyClass> policyClasses = policyClassService.getAllCategoryData();
        Integer loopId = -1;
        for (String hobbyId : hobbiesId) {
            String[] special = hobbyId.split(":");
            if (special.length > 1) {
                loopId++;
                PolicyClass specialPolicyClass = new PolicyClass(loopId, special[0], special[1]);
                policyClassList.add(specialPolicyClass);
                continue;
            }
            for (PolicyClass policyClass : policyClasses) {
                if (policyClass.getId() == Integer.parseInt(hobbyId)) {
                    policyClassList.add(policyClass);
                }
            }
        }
        outLoop:
        for (PolicyClass policyClass : policyClassList) {
            //特殊处理 全部 和其他
            if (policyClass.getContent().equals("全部") || policyClass.getContent().equals("其他")) {
                List<String> content = new ArrayList<>();
                content.add(policyClass.getContent());
                policyDtoResults.add(new PolicyDtoResult(companyId, policyClass.getName(), content, allHobby.get(0).getUpdateTime()));
                continue;
            }
            //遍历一级目录,把二级目录add到children里
            for (PolicyDtoResult policyDtoResult : policyDtoResults) {
                if (policyClass.getName().equals(policyDtoResult.getName())) {
                    List<String> content = policyDtoResult.getChildren();
                    content.add(policyClass.getContent());
                    policyDtoResult.setChildren(content);
//                        policyDtoResults.add(new PolicyDtoResult(policyClass.getId(),policyClass.getName(),content));
                    continue outLoop;
                }
            }
            //创建一二级目录以供遍历
            List<String> content = new ArrayList<>();
            content.add(policyClass.getContent());
            policyDtoResults.add(new PolicyDtoResult(companyId, policyClass.getName(), content, allHobby.get(0).getUpdateTime()));
        }
//            for (PolicyCategoryData policyCategoryData : policyCategoryDataList) {
//                String key
//                for (Map.Entry<String, String> entry : item.entrySet()) {
//                    String key = entry.getKey();
//                    String value = entry.getValue();
//                    resultMap.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
//                }
//            }

        return Result.success(policyDtoResults);
    }

    //
    @PostMapping("/policyhobby")
    @ResponseBody
    public Result updatePolicyHobby(@RequestBody HobbyRes hobbyRes) {
        List<String> hobbies = new ArrayList<>();
        List<FormData> FormDataList = hobbyRes.getFormData();
        if (FormDataList.isEmpty()) {
            return Result.error("喜好输入错误");
        }
        Integer companyId = hobbyRes.getCompanyId();
        List<PolicyClass> policyClasses = policyClassService.getAllCategoryData();
        outLoop:
        for (FormData formData : FormDataList) {
            if (formData.getChildren() == null || formData.getChildren().isEmpty()) {
                continue;
            }
            for (String content : formData.getChildren()) {
                if (content.equals("全部") || content.equals("其他")) {
                    hobbies.add(formData.getName() + ":" + content);
                    continue outLoop;
                }
            }
            for (String content : formData.getChildren()) {
                for (PolicyClass policyClass : policyClasses) {
                    if (policyClass.getContent().equals(content) && policyClass.getName().equals(formData.getName())) {
                        hobbies.add(String.valueOf(policyClass.getId()));
                    }
                }
            }
        }
        String hobbiesStr = "";
        if (!hobbies.isEmpty()) {
            hobbiesStr = String.join(";", hobbies.stream().map(Object::toString).collect(Collectors.toList()));
        }
        List<PolicyClass> allHobbyDb = policyClassService.getAllHobby(companyId);
        if (allHobbyDb.get(0).getContent() == null) {
            allHobbyDb.get(0).setContent("");
        }
        if (allHobbyDb.get(0).getContent().equals(hobbiesStr)) {
            return Result.success("与之前喜好相同");
        } else {
            PolicyClass allHobby = new PolicyClass(companyId, null, hobbiesStr, new Date());
            policyClassService.updateHobby(allHobby);
            return Result.success("更新成功");
        }
    }

    //测试用接口
    @GetMapping("/inin")
    public Result<List<PolicyAPIDto>> get() {
        return Result.success(policyClassService.findPolicyClassById(1));
    }

    @GetMapping("/entNatureData")
    public Result<List<EntNatureDate>> getAllEntNatureData() {
        return Result.success(entNatureDataService.getAll());
    }

    @GetMapping("/policyMatchList")
    public Result<List<PolicyMatch>> getPolicyMatch() {
        List<PolicyMatch> policyMatchList = policyMatchService.getAll();
        return Result.success(policyMatchList);
    }

    @GetMapping("/goventmatchdata")
    public Result GovernMatch(@RequestParam(required = false) Integer id) {
        List<PolicyAPIDto> list = governMatchService.GovernMatchData(id);
        return Result.success(list);
    }

    @GetMapping("/policydata/{policyId}")
    public Result<PolicyAPIDto> getPolicyDataById(@PathVariable("policyId") Integer policyId) {
        return Result.success(policyClassService.selectPolicyById(policyId).get(0));
    }

    @GetMapping("/policymodel")
    public Result<List<CompanySubmissionStatus>> modelData(@RequestParam("id") Integer id, @RequestParam(required = false) Integer year) {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        List<Policy> policies = policyService.selectCustomModelList(new Policy(1));
        Integer finalYear = year;
        policies = policies.stream()
                .filter(policy -> policy.getName().startsWith(String.valueOf(finalYear)))
                .collect(Collectors.toList());
        List<CompanySubmissionStatus> result = new ArrayList<>();
        for (Policy policy : policies) {
            CompanySubmissionStatus css = new CompanySubmissionStatus();
            List<CompanyPolicySubmission> companyPolicySubmissions = companyPolicySubmissionService.selectCompanyPolicySubmissionList(new CompanyPolicySubmission(id, policy.getId()));
            List<Date> history = new ArrayList<>();
            for (CompanyPolicySubmission companyPolicySubmission : companyPolicySubmissions) {
                history.add(companyPolicySubmission.getDate());
            }
            css.setHistory(history);
            css.setModelId(policy.getId());
            css.setModelName(policy.getName());
            List<Map<String, String>> indicatorName = policyService.c2cNoMatchedIndicator(id, policy, year);
            css.setIndicatorName(indicatorName);
            css.setTotal(indicatorName.size());
            result.add(css);
        }
        return Result.success(result);
    }

    public static class HobbyRes {
        private Integer companyId;
        private List<FormData> formData;

        public HobbyRes() {
        }

        public HobbyRes(Integer companyId, List<FormData> formData, Date updateTime) {
            this.companyId = companyId;
            this.formData = formData;
        }

        @Override
        public String toString() {
            return "HobbyRes{" +
                    "companyId=" + companyId +
                    ", formData=" + formData +
                    '}';
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public List<FormData> getFormData() {
            return formData;
        }

        public void setFormData(List<FormData> formData) {
            this.formData = formData;
        }
    }

    private static class PolicyRes {
        private Integer total;
        private List<PolicyAPIDto> row;

        public PolicyRes(Integer total, List<PolicyAPIDto> row) {
            this.total = total;
            this.row = row;
        }

        public PolicyRes() {
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<PolicyAPIDto> getRow() {
            return row;
        }

        public void setRow(List<PolicyAPIDto> row) {
            this.row = row;
        }

        @Override
        public String toString() {
            return "PolicyRes{" +
                    "total=" + total +
                    ", row=" + row +
                    '}';
        }

    }

//    @GetMapping("/policymodel")
//    public Result<List<CompanySubmissionStatus>>
//    getSubmissionStatusByCompanyId(@RequestParam("id") Integer id) {
//        return Result.success(companySubmissionStatusService.selectSubmissionStatusByCompanyId(id));
//    }

    private static class PolicySelect {
        private List<PolicyDtoResult> categories;
        private String query1;
        private String query2;
        private List<String> timePeriod;

        public PolicySelect(List<PolicyDtoResult> categories, String query1, String query2, List<String> timePeriod) {
            this.categories = categories;
            this.query1 = query1;
            this.query2 = query2;
            this.timePeriod = timePeriod;
        }

        public PolicySelect() {
        }

        @Override
        public String toString() {
            return "PolicySelect{" +
                    "categories=" + categories +
                    ", query1='" + query1 + '\'' +
                    ", query2='" + query2 + '\'' +
                    ", timePeriod=" + timePeriod +
                    '}';
        }

        public List<PolicyDtoResult> getCategories() {
            return categories;
        }

        public void setCategories(List<PolicyDtoResult> categories) {
            this.categories = categories;
        }

        public String getQuery1() {
            return query1;
        }

        public void setQuery1(String query1) {
            this.query1 = query1;
        }

        public String getQuery2() {
            return query2;
        }

        public void setQuery2(String query2) {
            this.query2 = query2;
        }

        public List<String> getTimePeriod() {
            return timePeriod;
        }

        public void setTimePeriod(List<String> timePeriod) {
            this.timePeriod = timePeriod;
        }
    }

    public static class FormData {
        private Integer id;
        private String name;
        private List<String> children;

        public FormData() {
        }

        public FormData(Integer id, String name, List<String> children) {
            this.id = id;
            this.name = name;
            this.children = children;
        }

        public List<String> getChildren() {
            return children;
        }

        public void setChildren(List<String> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "formData{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", children='" + children + '\'' +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


