package com.ruoyi.web.controller.api.mapper;

import com.ruoyi.web.controller.api.domain.CompanySubmissionStatus;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
@Mapper
public interface CompanySubmissionStatusMapper {
    List<CompanySubmissionStatus> selectSubmissionStatusByCompanyId(Integer companyId);
}
