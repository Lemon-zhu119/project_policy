package com.ruoyi.web.controller.api.service.serviceImpl;

import com.ruoyi.web.controller.api.domain.Material;
import com.ruoyi.web.controller.api.mapper.MaterialMapper;
import com.ruoyi.web.controller.api.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public List<Material> getMaterial(Integer id, String modelName) {
        return materialMapper.getMaterial(id,modelName);
    }



}


