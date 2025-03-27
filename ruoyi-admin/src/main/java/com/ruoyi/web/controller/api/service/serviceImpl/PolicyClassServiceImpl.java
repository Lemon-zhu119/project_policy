package com.ruoyi.web.controller.api.service.serviceImpl;

import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.web.controller.api.domain.PolicyClass;
import com.ruoyi.web.controller.api.domain.PolicyClassAPI;
import com.ruoyi.web.controller.api.domain.dto.PolicyAPIDto;
import com.ruoyi.web.controller.api.domain.dto.PolicyDtoResult;
import com.ruoyi.web.controller.api.mapper.PolicyClassMapper;
import com.ruoyi.web.controller.api.service.PolicyClassService;
import com.ruoyi.web.controller.policy.domain.PolicyCategory;
import com.ruoyi.web.controller.policy.domain.PolicyCategoryData;
import com.ruoyi.web.controller.policy.domain.PolicyPropertiesData;
import com.ruoyi.web.controller.policy.service.IPolicyCategoryDataService;
import com.ruoyi.web.controller.policy.service.IPolicyCategoryService;
import com.ruoyi.web.controller.policy.service.IPolicyPropertiesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PolicyClassServiceImpl implements PolicyClassService {
    @Autowired
    private PolicyClassMapper policyClassMapper;
    @Autowired
    private IPolicyCategoryService policyCategoryService;
    @Autowired
    private IPolicyCategoryDataService policyCategoryDateService;
    @Autowired
    private IPolicyPropertiesDataService policyPropertiesDataService;

    @Override
    public List<PolicyClass> getAll() {

        return policyClassMapper.getAll();
    }

    @Override
    public List<PolicyAPIDto> findPolicyClassById(Integer id) {
        List<PolicyClassAPI> policyClassAPIS = policyClassMapper.findPolicyClassById(id);
        System.out.println(policyClassAPIS);
        List<PolicyAPIDto> policyAPIDtos = new ArrayList<>();
        PolicyAPIDto policyAPIDto = new PolicyAPIDto();
        int oldIndex = policyClassAPIS.get(0).getId();
        BeanUtils.copyProperties(policyClassAPIS.get(0), policyAPIDto);
        for (int i = 0; i < policyClassAPIS.size(); i++) {
            int index = policyClassAPIS.get(i).getId();
            if (index != oldIndex) {
                System.out.println(oldIndex);
                policyAPIDto.setPolicyName(policyAPIDto.getName());
                policyAPIDto.setPolicyConditions(policyAPIDto.getPolicyCondition());
                policyAPIDto.setPolicyContent(policyAPIDto.getContent());
                if (policyAPIDto.getDeadTime() == null) {
                    policyAPIDto.setDeadTime("2099-12-30");
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");// 根据你的日期格式创建DateTimeFormatter对象
                LocalDate date = LocalDate.parse(policyAPIDto.getDeadTime(), formatter);
                Date dateString = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
                policyAPIDto.setExpireDate(dateString);
                policyAPIDtos.add(policyAPIDto);
                policyAPIDto = new PolicyAPIDto();
                BeanUtils.copyProperties(policyClassAPIS.get(i), policyAPIDto);
                oldIndex = index;
            }
            if (policyClassAPIS.get(i).getCategoryName() == null) {
                continue;
            }
            switch (policyClassAPIS.get(i).getCategoryName()) {
                case "leaderDepartment":
                    policyAPIDto.setLeaderDepartment(policyClassAPIS.get(i).getCategoryContent());
                    break;
                case "applicableArea":
                    policyAPIDto.setApplicableArea(policyClassAPIS.get(i).getCategoryContent());
                    break;
                case "policyClass":
                    policyAPIDto.setPolicyClass(policyClassAPIS.get(i).getCategoryContent());
                    break;
                case "policyType":
                    policyAPIDto.setPolicyType(policyClassAPIS.get(i).getCategoryContent());
                    break;
                case "declarationStatus":
                    policyAPIDto.setDeclarationStatus(policyClassAPIS.get(i).getCategoryContent());
                    break;
                case "releaseTime":
                    policyAPIDto.setReleaseTime(policyClassAPIS.get(i).getCategoryContent());
                    break;
            }
        }
        policyAPIDto.setPolicyName(policyAPIDto.getName() != null ? policyAPIDto.getName() : null);
        policyAPIDto.setPolicyConditions(policyAPIDto.getFormula() != null ? policyAPIDto.getFormula() : null);
        policyAPIDto.setPolicyFileUrl(policyAPIDto.getFileUrl() != null ? policyAPIDto.getFileUrl() : null);
        policyAPIDto.setPolicyContent(policyAPIDto.getContent() != null ? policyAPIDto.getContent() : null);
        policyAPIDtos.add(policyAPIDto);
        policyAPIDtos.sort(Comparator.comparing(PolicyAPIDto::getPublishDate).reversed());
        return policyAPIDtos;
    }

    @Override
    public List<PolicyAPIDto> selectPolicyByClass(List<PolicyAPIDto> policyAPIDtoList, List<PolicyDtoResult> policyDtoResultList) {
        outLoop:
        for (PolicyDtoResult policyDtoResult : policyDtoResultList) {
            Set<Integer> idSet = new HashSet<>();
            for (String subPolicyType : policyDtoResult.getChildren()) {
                if (subPolicyType.equals("全部")) {
                    for (PolicyAPIDto policyAPIDto : policyAPIDtoList) {
                        idSet.add(policyAPIDto.getId());
                    }
                    break;
                } else if (subPolicyType.equals("其他")) {
                    List<PolicyCategory> policyCategories = policyCategoryService.selectPolicyCategoryListByName(new PolicyCategory(null, policyDtoResult.getName(), null));
                    List<PolicyCategoryData> policyCategoryDataList = policyCategoryDateService.selectPolicyCategoryDataList(new PolicyCategoryData(null, policyCategories.get(0).getId(), null, null));
                    Set<Integer> idSetRemove = new HashSet<>();
                    for (PolicyCategoryData policyCategoryData : policyCategoryDataList) {
                        if (policyCategoryData.getContent().equals("全部")) {
                            continue;
                        }
                        List<PolicyPropertiesData> policyPropertiesDataList = policyPropertiesDataService.selectPolicyPropertiesDataByCategoryDataId(policyCategoryData.getId());
                        if (policyCategoryData.getContent().equals("其他")) {
                            for (PolicyPropertiesData policyPropertiesData : policyPropertiesDataList) {
                                idSet.add(policyPropertiesData.getPolicyId());
                            }
                        } else {
                            for (PolicyPropertiesData policyPropertiesData : policyPropertiesDataList) {
                                idSetRemove.add(policyPropertiesData.getPolicyId());
                            }
                        }
                    }
                    for (PolicyAPIDto policyAPIDto : policyAPIDtoList) {
                        if (!idSetRemove.contains(policyAPIDto.getId())) {
                            idSet.add(policyAPIDto.getId());
                        }
                    }
                } else {
                    List<PolicyCategoryData> policyCategoryDataList1 = policyCategoryDateService.selectPolicyCategoryDataList(new PolicyCategoryData(null, null, subPolicyType, null));
                    if (!policyCategoryDataList1.isEmpty()) {
                        List<PolicyPropertiesData> policyPropertiesDataList = policyPropertiesDataService.selectPolicyPropertiesDataByCategoryDataId(policyCategoryDataList1.get(0).getId());
                        for(PolicyPropertiesData policyPropertiesData:policyPropertiesDataList){
                            idSet.add(policyPropertiesData.getPolicyId());
                        }
                    }
                }
            }
            List<PolicyAPIDto> LoopPolicyApiDtoList = new ArrayList<>();
            for (PolicyAPIDto policyAPIDto : policyAPIDtoList) {
                if (idSet.contains(policyAPIDto.getId())) {
                    LoopPolicyApiDtoList.add(policyAPIDto);
                }
            }
            policyAPIDtoList.clear();
            policyAPIDtoList.addAll(LoopPolicyApiDtoList);
        }
        return policyAPIDtoList;
    }
    @Override
    public List<PolicyAPIDto> selectPolicyByTime(List<PolicyAPIDto> policyAPIDtoList,List<Date> timePeriod) {
        List<PolicyAPIDto> policyAPIDtos = policyAPIDtoList;
        List<PolicyAPIDto> apiDtoList = new ArrayList<>();
        for (PolicyAPIDto policyAPIDto : policyAPIDtos) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 根据实际格式调整
            Date publishDateObj = policyAPIDto.getPublishDate();
            for (int i = 0; i < timePeriod.size() - 1; i++) {
                if (!publishDateObj.before(timePeriod.get(i)) && !publishDateObj.after(timePeriod.get(i + 1))) {
                    apiDtoList.add(policyAPIDto);
                }
            }
        }
        return apiDtoList;
    }

    @Override
    public List<PolicyAPIDto> selectPolicyById(Integer id) {
        List<PolicyAPIDto> policyAPIDtos = findPolicyClassById(1);
        List<PolicyAPIDto> apiDtoList = new ArrayList<>();
        for (PolicyAPIDto policyAPIDto : policyAPIDtos) {
            if (policyAPIDto.getId().equals(id)) {
                apiDtoList.add(policyAPIDto);
            }
        }
        return apiDtoList;
    }

    @Override
    public List<PolicyAPIDto> selectPolicyByName(List<PolicyAPIDto> policyAPIDtoList, String policyName) {
        List<PolicyAPIDto> policyAPIDtos = policyAPIDtoList;
        List<PolicyAPIDto> apiDtoList = new ArrayList<>();
        for (PolicyAPIDto policyAPIDto : policyAPIDtos) {
            if (policyAPIDto.getName() != null && policyAPIDto.getName().contains(policyName)) {
                apiDtoList.add(policyAPIDto);
            }
        }
        return apiDtoList;
    }

    @Override
    public List<PolicyAPIDto> sortPolicyByQuery(List<PolicyAPIDto> policyAPIDtoList,String query) {
        List<PolicyAPIDto> policyAPIDtos =policyAPIDtoList;
        List<PolicyAPIDto> apiDtoList = new ArrayList<>();
        PolicyAPIDto policyAPIDto;
        if(query.equals("推荐度")){
            for (int i = 0; i < policyAPIDtos.size(); i++) {
                Integer max = i;
                Integer MaxRecLevelObj = policyAPIDtos.get(i).getRecLevel();
                for (int j = i + 1; j < policyAPIDtos.size(); j++) {
                    Integer nowRecLevelObj = policyAPIDtos.get(j).getRecLevel();
                    if (nowRecLevelObj > MaxRecLevelObj) {
                        max = j;
                        MaxRecLevelObj = nowRecLevelObj;
                    }
                }
                if (max != i) {
                    policyAPIDto = policyAPIDtos.get(i);
                    policyAPIDtos.set(i, policyAPIDtos.get(max));
                    policyAPIDtos.set(max, policyAPIDto);
                    apiDtoList.add(policyAPIDtos.get(i));
                } else {
                    apiDtoList.add(policyAPIDtos.get(max));
                }
            }
        }else if(query.equals("发布时间")){
            for (int i = 0; i < policyAPIDtos.size(); i++) {
                Integer max = i;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 根据实际格式调整
                Date MaxPublishDateObj = policyAPIDtos.get(i).getPublishDate();
                for (int j = i + 1; j < policyAPIDtos.size(); j++) {
                    Date nowPublishDateObj = policyAPIDtos.get(j).getPublishDate();
                    if (nowPublishDateObj.after(MaxPublishDateObj)) {
                        max = j;
                        MaxPublishDateObj = nowPublishDateObj;
                    }
                }
                if (max != i) {
                    policyAPIDto = policyAPIDtos.get(i);
                    policyAPIDtos.set(i, policyAPIDtos.get(max));
                    policyAPIDtos.set(max, policyAPIDto);
                    apiDtoList.add(policyAPIDtos.get(i));
                }
            }
            if (apiDtoList.isEmpty()) {
                return policyAPIDtos;
            }
        }else if(query.equals("浏览量")){
            for (int i = 0; i < policyAPIDtos.size(); i++) {
                Integer max = i;
                Integer MaxHotLevelObj = policyAPIDtos.get(i).getHotLevel();
                for (int j = i + 1; j < policyAPIDtos.size(); j++) {
                    Integer nowHotLevelObj = policyAPIDtos.get(j).getHotLevel();
                    if (nowHotLevelObj > MaxHotLevelObj) {
                        max = j;
                        MaxHotLevelObj = nowHotLevelObj;
                    }
                }
                if (max != i) {
                    policyAPIDto = policyAPIDtos.get(i);
                    policyAPIDtos.set(i, policyAPIDtos.get(max));
                    policyAPIDtos.set(max, policyAPIDto);
                    apiDtoList.add(policyAPIDtos.get(i));
                } else {
                    apiDtoList.add(policyAPIDtos.get(max));
                }
            }
        }

        return apiDtoList;
    }

    @Override
    public List<PolicyAPIDto> selectPolicyByIsNew(Integer isNew) throws ParseException {
        List<PolicyAPIDto> policyAPIDtos = findPolicyClassById(1);
        System.out.println(policyAPIDtos);
        List<PolicyAPIDto> apiDtoList = new ArrayList<>();
        PolicyAPIDto policyAPIDto = new PolicyAPIDto();
        if (isNew == 1) {
            for (int i = 0; i < 5; i++) {
                Integer max = i;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 根据实际格式调整
                Date MaxPublishDateObj = policyAPIDtos.get(i).getPublishDate();
                for (int j = i + 1; j < policyAPIDtos.size(); j++) {
                    Date nowPublishDateObj = policyAPIDtos.get(j).getPublishDate();
                    if (nowPublishDateObj.after(MaxPublishDateObj)) {
                        max = j;
                        MaxPublishDateObj = nowPublishDateObj;
                    }
                }
                if (max != i) {
                    policyAPIDto = policyAPIDtos.get(i);
                    policyAPIDtos.set(i, policyAPIDtos.get(max));
                    policyAPIDtos.set(max, policyAPIDto);
                    apiDtoList.add(policyAPIDtos.get(i));
                } else {
                    apiDtoList.add(policyAPIDtos.get(i));
                }
            }
            return apiDtoList;
        } else {
            for (int i = 0; i < policyAPIDtos.size(); i++) {
                Integer max = i;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 根据实际格式调整
                Date MaxPublishDateObj = policyAPIDtos.get(i).getPublishDate();
                for (int j = i + 1; j < policyAPIDtos.size(); j++) {
                    Date nowPublishDateObj = policyAPIDtos.get(j).getPublishDate();
                    if (nowPublishDateObj.after(MaxPublishDateObj)) {
                        max = j;
                        MaxPublishDateObj = nowPublishDateObj;
                    }
                }
                if (max != i) {
                    policyAPIDto = policyAPIDtos.get(i);
                    policyAPIDtos.set(i, policyAPIDtos.get(max));
                    policyAPIDtos.set(max, policyAPIDto);
                    apiDtoList.add(policyAPIDtos.get(i));
                }
            }
            if (apiDtoList.isEmpty()) {
                return policyAPIDtos;
            }
            return apiDtoList;
        }
    }

    @Override
    public List<PolicyAPIDto> selectPolicyByHotLevel() {
        List<PolicyAPIDto> policyAPIDtos = findPolicyClassById(1);
        List<PolicyAPIDto> apiDtoList = new ArrayList<>();
        PolicyAPIDto policyAPIDto = new PolicyAPIDto();
        for (int i = 0; i < policyAPIDtos.size(); i++) {
            Integer max = i;
            Integer MaxHotLevelObj = policyAPIDtos.get(i).getHotLevel();
            for (int j = i + 1; j < policyAPIDtos.size(); j++) {
                Integer nowHotLevelObj = policyAPIDtos.get(j).getHotLevel();
                if (nowHotLevelObj > MaxHotLevelObj) {
                    max = j;
                    MaxHotLevelObj = nowHotLevelObj;
                }
            }
            if (max != i) {
                policyAPIDto = policyAPIDtos.get(i);
                policyAPIDtos.set(i, policyAPIDtos.get(max));
                policyAPIDtos.set(max, policyAPIDto);
                apiDtoList.add(policyAPIDtos.get(i));
            } else {
                apiDtoList.add(policyAPIDtos.get(max));
            }
        }
        return apiDtoList;
    }

    @Override
    public List<PolicyClass> getAllHobby(Integer companyId) {
        return policyClassMapper.getAllHobby(companyId);
    }

    @Override
    public List<PolicyClass> getAllCategoryData() {
        return policyClassMapper.getAllCategoryData();
    }

    @Override
    public Integer updateHobby(PolicyClass allHobby) {
        return policyClassMapper.updateHobby(allHobby);
    }

}
