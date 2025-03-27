package com.ruoyi.web.controller.api.service;

import com.ruoyi.web.controller.api.domain.dto.PolicyAPIDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GovernMatchService {


    List<PolicyAPIDto> GovernMatchData(Integer id);
}
