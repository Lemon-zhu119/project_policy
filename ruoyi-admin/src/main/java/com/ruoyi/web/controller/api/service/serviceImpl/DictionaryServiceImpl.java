package com.ruoyi.web.controller.api.service.serviceImpl;

import com.ruoyi.web.controller.api.domain.Dictionary;
import com.ruoyi.web.controller.api.mapper.DictionaryMapper;
import com.ruoyi.web.controller.api.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements IDictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Override
    public List<Dictionary> getAll() {
        return dictionaryMapper.getAll();
    }


}
