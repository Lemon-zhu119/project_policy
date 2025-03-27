package com.ruoyi.web.controller.api.mapper;

import com.ruoyi.web.controller.api.domain.PolicyMatch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PolicyMatchMapper {
    List<PolicyMatch> getAll();
}
