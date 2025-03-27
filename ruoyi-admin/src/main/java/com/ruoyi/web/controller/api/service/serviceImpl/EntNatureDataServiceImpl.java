package com.ruoyi.web.controller.api.service.serviceImpl;

import com.ruoyi.web.controller.api.domain.EntNatureDate;
import com.ruoyi.web.controller.api.mapper.EntNatureDateMapper;
import com.ruoyi.web.controller.api.service.EntNatureDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EntNatureDataServiceImpl implements EntNatureDataService {
    @Autowired
    private EntNatureDateMapper entNatureDateMapper;

    @Override
    public List<EntNatureDate> getAll() {
        return entNatureDateMapper.getAll();
    }
}
