package com.ruoyi.web.controller.api.service;

import com.ruoyi.web.controller.api.domain.PolicyClass;
import com.ruoyi.web.controller.api.domain.dto.PolicyAPIDto;
import com.ruoyi.web.controller.api.domain.dto.PolicyDtoResult;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public interface PolicyClassService {
    List<PolicyClass> getAll();
    List<PolicyAPIDto> findPolicyClassById(Integer id);

    List<PolicyAPIDto> selectPolicyByClass(List<PolicyAPIDto> policyAPIDtoList, List<PolicyDtoResult> policyDtoResultList);

    List<PolicyAPIDto> selectPolicyByTime(List<PolicyAPIDto> policyAPIDtoList,List<Date> timePeriod);

    List<PolicyAPIDto> selectPolicyById(Integer id);

    List<PolicyAPIDto> selectPolicyByName(List<PolicyAPIDto> policyAPIDtoList, String policyName);

    List<PolicyAPIDto> sortPolicyByQuery(List<PolicyAPIDto> policyAPIDtoList,String query);

    List<PolicyAPIDto> selectPolicyByIsNew(Integer isNew) throws ParseException;

    List<PolicyAPIDto> selectPolicyByHotLevel();

    List<PolicyClass> getAllHobby(Integer companyId);

    List<PolicyClass> getAllCategoryData();

    Integer updateHobby(PolicyClass allHobby);
}
