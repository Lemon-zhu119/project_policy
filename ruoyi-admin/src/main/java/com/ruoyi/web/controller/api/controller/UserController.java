package com.ruoyi.web.controller.api.controller;

import com.ruoyi.web.controller.api.common.Result;
import com.ruoyi.web.controller.company.domain.Company;
import com.ruoyi.web.controller.company.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private ICompanyService companyService;

    @GetMapping("/getuserinfo/{id}")
    public Result<List<Company>> getUserInfo(@PathVariable("id") Integer id) {
        return Result.success(companyService.selectCompanyListById(id));
    }
}
