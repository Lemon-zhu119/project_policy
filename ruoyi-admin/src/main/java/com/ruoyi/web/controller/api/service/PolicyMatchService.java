package com.ruoyi.web.controller.api.service;

import com.ruoyi.web.controller.api.domain.PolicyMatch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PolicyMatchService {
    List<PolicyMatch> getAll();
}
