package com.ruoyi.web.controller.api.service.serviceImpl;

import com.ruoyi.web.controller.api.domain.dto.PolicyAPIDto;
import com.ruoyi.web.controller.api.service.GovernMatchService;
import com.ruoyi.web.controller.api.service.PolicyClassService;
import com.ruoyi.web.controller.company.domain.Company;
import com.ruoyi.web.controller.company.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GovernMatchServiceImpl implements GovernMatchService {
    @Autowired
    private PolicyClassService policyClassService;
    @Autowired
    private ICompanyService companyService;

    @Override
    public List<PolicyAPIDto> GovernMatchData(Integer id) {
        List<PolicyAPIDto> policyAPIDtoList = new ArrayList<>();
        Company company = companyService.selectCompanyListById(id).get(0);
        //获取全部政策数据
        List<PolicyAPIDto> policyAPIDtos = policyClassService.findPolicyClassById(1);
        if (company.getAddress() == null) {
            return new ArrayList<PolicyAPIDto>();
        }
        for (PolicyAPIDto policyAPIDto : policyAPIDtos) {
            if (policyAPIDto.getApplicableArea() == null) {
                continue;
            }
            List<String> companyAddress = List.of(company.getAddress().split(";"));
            List<String> addressLevels = new ArrayList<>();

// 将用户地址的每一级都添加到 addressLevels 列表中
            for (int i = 0; i < companyAddress.size(); i++) {
                StringBuilder addressLevel = new StringBuilder();
                for (int j = 0; j <= i; j++) { // 从第一级到当前级
                    addressLevel.append(companyAddress.get(j));
                }
                addressLevels.add(addressLevel.toString());
            }
// 检查 ApplicableArea 是否包含用户地址的任意一级，或者是否以用户地址的某一级开始
            for (String addressLevel : addressLevels) {
                if (addressLevel.contains(policyAPIDto.getApplicableArea())){
                    policyAPIDtoList.add(policyAPIDto);
                    break; // 找到匹配项后退出循环
                }
            }
        }
        return policyAPIDtoList;
    }
}