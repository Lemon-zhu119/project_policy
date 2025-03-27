package com.ruoyi.web.controller.api.mapper;

import com.ruoyi.web.controller.api.domain.EntNatureDate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EntNatureDateMapper {
    List<EntNatureDate> getAll();
}
