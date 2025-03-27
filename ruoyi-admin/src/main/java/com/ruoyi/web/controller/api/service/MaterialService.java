package com.ruoyi.web.controller.api.service;

import com.ruoyi.web.controller.api.domain.Material;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterialService {

    List<Material> getMaterial(Integer id, String modelName);
}