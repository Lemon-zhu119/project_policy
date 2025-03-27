package com.ruoyi.web.controller.api.service.serviceImpl;

import com.ruoyi.web.controller.api.domain.CompanySubmissionStatus;
import com.ruoyi.web.controller.api.mapper.CompanySubmissionStatusMapper;
import com.ruoyi.web.controller.api.service.CompanySubmissionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanySubmissionStatusServiceImpl implements CompanySubmissionStatusService {
    @Autowired
    private CompanySubmissionStatusMapper companySubmissionStatusMapper;
    @Override
    public List<CompanySubmissionStatus> selectSubmissionStatusByCompanyId(Integer companyId) {
        return companySubmissionStatusMapper.selectSubmissionStatusByCompanyId(companyId);
    }
}
