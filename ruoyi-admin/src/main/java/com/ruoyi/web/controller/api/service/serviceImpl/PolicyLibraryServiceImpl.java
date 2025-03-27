package com.ruoyi.web.controller.api.service.serviceImpl;

import com.ruoyi.web.controller.api.domain.*;
import com.ruoyi.web.controller.api.mapper.PolicyLibraryMapper;
import com.ruoyi.web.controller.api.service.PolicyLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyLibraryServiceImpl implements PolicyLibraryService {
    @Autowired
    private PolicyLibraryMapper policyLibraryMapper;

    public PolicyLibrary getAll() {
        return policyLibraryMapper.getAll();
    }

    public MatchN getMatchN() {
        return policyLibraryMapper.getMatchN();
    }

    @Override
    public UserVisitN getUserVisitN() {
        return policyLibraryMapper.getUserVisitN();
    }

    @Override
    public ShenBao getShenBao(Integer id, String modelName) {
        List<ShenBao> shenBao = policyLibraryMapper.selectShenBao(id, modelName);
        if (shenBao.isEmpty()) {
            return null;
        } else {
            return policyLibraryMapper.selectShenBao(id, modelName).get(0);
        }
    }

    @Override
    public ShenHe getShenHe(Integer id, String modelName) {
        List<ShenHe> shenHes = policyLibraryMapper.selectShenHe(id, modelName);
        if (shenHes.isEmpty()) {
            return null;
        }
        return policyLibraryMapper.selectShenHe(id, modelName).get(0);
    }

    @Override
    public Integer updateMatchN(MatchN matchN) {
        return policyLibraryMapper.updateMatchN(matchN);
    }

    @Override
    public Integer updateUserVisitN(UserVisitN userVisitN) {
        return policyLibraryMapper.updateUservisitN(userVisitN);
    }

}
