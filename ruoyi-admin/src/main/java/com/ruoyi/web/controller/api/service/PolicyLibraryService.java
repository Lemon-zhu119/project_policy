package com.ruoyi.web.controller.api.service;

import com.ruoyi.web.controller.api.domain.*;
import org.springframework.stereotype.Service;

@Service
public interface PolicyLibraryService{
    PolicyLibrary getAll();
    MatchN getMatchN();
    UserVisitN getUserVisitN();

    ShenBao getShenBao(Integer id, String modelName);

    ShenHe getShenHe(Integer id, String modelName);

    Integer updateMatchN(MatchN matchN);

    Integer updateUserVisitN(UserVisitN userVisitN);
}
