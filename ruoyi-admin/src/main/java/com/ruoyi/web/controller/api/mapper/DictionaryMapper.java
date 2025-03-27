package com.ruoyi.web.controller.api.mapper;

import com.ruoyi.web.controller.api.domain.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface DictionaryMapper {
    List<Dictionary> getAll();
}
