package com.ruoyi.web.controller.api.mapper;

import com.ruoyi.web.controller.api.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PolicyLibraryMapper {
    PolicyLibrary getAll();
    MatchN getMatchN();
    UserVisitN getUserVisitN();

    List<ShenBao> selectShenBao(@Param("id") Integer id, @Param("modelName") String modelName);

    List<ShenHe> selectShenHe(@Param("id") Integer id, @Param("modelName") String modelName);

    Integer updateMatchN(MatchN matchN);

    Integer updateUservisitN(UserVisitN userVisitN);
}
