package com.ruoyi.web.controller.api.service.serviceImpl;

import com.ruoyi.web.controller.api.domain.PolicyMatch;
import com.ruoyi.web.controller.api.mapper.PolicyMatchMapper;
import com.ruoyi.web.controller.api.service.PolicyMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PolicyMatchServiceImpl implements PolicyMatchService {
    @Autowired
    private PolicyMatchMapper policyMatchMapper;
    public List<PolicyMatch> getAll(){
        List<PolicyMatch> currentModel=policyMatchMapper.getAll();
        String year=String.valueOf(LocalDate.now().getYear());
        currentModel=currentModel.stream().filter(policyMatch -> policyMatch.getModelName().startsWith(year)).collect(Collectors.toList());
        return currentModel;
    }
}
