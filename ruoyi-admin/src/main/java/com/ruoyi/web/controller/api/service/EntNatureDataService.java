package com.ruoyi.web.controller.api.service;

import com.ruoyi.web.controller.api.domain.EntNatureDate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EntNatureDataService {
    List<EntNatureDate> getAll();
}
