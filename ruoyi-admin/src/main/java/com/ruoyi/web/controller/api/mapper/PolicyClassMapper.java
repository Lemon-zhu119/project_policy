package com.ruoyi.web.controller.api.mapper;
import com.ruoyi.web.controller.api.domain.PolicyClass;
import com.ruoyi.web.controller.api.domain.PolicyClassAPI;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PolicyClassMapper{
    List<PolicyClass> getAllHobby(@Param("companyId") Integer companyId);

    List<PolicyClass> getAll();
    List<PolicyClassAPI> findPolicyClassById(Integer id);

    List<PolicyClass> getAllCategoryData();

    Integer updateHobby(PolicyClass allHobby);
}
