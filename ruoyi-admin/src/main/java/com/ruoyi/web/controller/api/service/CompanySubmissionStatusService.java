package com.ruoyi.web.controller.api.service;

import com.ruoyi.web.controller.api.domain.CompanySubmissionStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanySubmissionStatusService {
    List<CompanySubmissionStatus> selectSubmissionStatusByCompanyId(Integer companyId);
}
