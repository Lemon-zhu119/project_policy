package com.ruoyi.web.controller.api.controller;

import com.ruoyi.web.controller.api.common.Result;
import com.ruoyi.web.controller.api.domain.dto.PolicyAPIDto;
import com.ruoyi.web.controller.policy.domain.Policy;
import com.ruoyi.web.controller.policy.domain.PolicyCategoryData;
import com.ruoyi.web.controller.policy.domain.PolicyPropertiesData;
import com.ruoyi.web.controller.policy.mapper.PolicyMapper;
import com.ruoyi.web.controller.policy.service.IPolicyCategoryDataService;
import com.ruoyi.web.controller.policy.service.IPolicyPropertiesDataService;
import com.ruoyi.web.controller.policy.service.IPolicyService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    @Autowired
    private PolicyMapper policyMapper;
    @Autowired
    private IPolicyService policyService;
    @Autowired
    private IPolicyCategoryDataService policyCategoryDataService;
    @Autowired
    private IPolicyPropertiesDataService policyPropertiesDataService;

    @PostMapping("/uploadExcel")
    public Result<String> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
        // 检查文件类型
        if (!isExcelFile(file)) {
            throw new IllegalArgumentException("Uploaded file is not an Excel file.");
        }

        List<Map<String, String>> data = new ArrayList<>();
        List<Policy> policies = new ArrayList<>(); // 用于存储 Policy 对象

        // 创建工作簿
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // 读取第一个工作表

        // 获取表头
        Row headerRow = sheet.getRow(0);
        List<String> headers = new ArrayList<>();
        for (Cell cell : headerRow) {
            headers.add(cell.getStringCellValue());
        }

        // 遍历每一行
        for (int i = 2; i <= sheet.getLastRowNum(); i++) { // 从1开始，跳过表头
            Row row = sheet.getRow(i);
            Map<String, String> rowMap = new HashMap<>();
            PolicyAPIDto policyAPIDto = new PolicyAPIDto(); // 创建 Policy 对象
            Policy policy = new Policy();
            for (int j = 0; j < headers.size(); j++) {
                Cell cell = row.getCell(j);
                String cellValue = (cell != null && !cell.toString().equals("/")) ? cell.toString() : ""; // 处理空单元格
                rowMap.put(headers.get(j), cellValue);
                // 根据表头设置 Policy 对象的属性
                switch (headers.get(j)) {
                    case "政策名称":
                        policyAPIDto.setName(cellValue);
                        break;
                    case "发布部门":
                        policyAPIDto.setLeaderDepartment(cellValue);
                        policyAPIDto.setSource(cellValue);
                        break;
                    case "发布时间":
                        policyAPIDto.setPublishDate(parseDate(cellValue));
                        break;
                    case "政策原文":
                        policyAPIDto.setContent(cellValue);
                        break;
                    case "适用区域":
                        policyAPIDto.setApplicableArea(cellValue); // 假设状态是整数
                        break;
                    case "政策内容":
                        policyAPIDto.setDescription(cellValue);
                        break;
                    case "政策对象":
                        policyAPIDto.setPolicyObject(cellValue);
                        break;
                    case "政策条件":
                        policyAPIDto.setPolicyCondition(cellValue.replace("\n", ""));
                        break;
                    case "申报材料":
                        policyAPIDto.setPolicyDocDetail(cellValue);
                        break;
                    case "单位联系人":
                        policyAPIDto.setContact(cellValue);
                        break;
                    case "联系电话":
                        if (!cellValue.equals("")) {
                            if (cellValue.contains("-")) {
                                policyAPIDto.setPhone(cellValue);
                            } else {
                                double number = Double.parseDouble(cellValue);
                                String normalString = String.format("%.0f", number);
                                policyAPIDto.setPhone(normalString);
                            }
                        }
                        break;
                    case "政策类别":
                        policyAPIDto.setPolicyClass(cellValue);
                        break;
                    case "政策类型":
                        policyAPIDto.setPolicyType(cellValue);
                        break;
                    case "申报状态":
                        policyAPIDto.setDeclarationStatus(cellValue);
                        break;
                    case "截止时间":
                        policyAPIDto.setExpireDate(parseDate(cellValue));
                        break;

                }
            }

            BeanUtils.copyProperties(policyAPIDto, policy);
            if (!policyService.selectPolicyByName(policyAPIDto.getName()).isEmpty()) {
                continue;
            }
            if (policy.getPublishDate() != null) {
                List<Policy> policydb=policyService.selectPolicyByName(policy.getName());
                if (policydb.isEmpty()) {
                    policyMapper.insertPolicy(policy);
                }else {
                    policy.setId(policydb.get(0).getId());
                    policyMapper.updatePolicy(policy);
                }
            }
            //现在companyId 和六大属性都获取到了 该怎么去接下来的插入？
            //一个个policyAPIDto.get六大属性？看看有没有值？
            //一级目录表结构不改的情况下应该不会报错
            Integer policyId = null;
            if (!policyService.selectPolicyByName(policy.getName()).isEmpty()) {
                policyId = policyService.selectPolicyByName(policy.getName()).get(0).getId();
            }
            if (!policyAPIDto.getApplicableArea().equals("")) {
                List<PolicyCategoryData> policyCategoryDataList = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, 2, policyAPIDto.getApplicableArea(), null));
                if (policyCategoryDataList.isEmpty()) {
                    //二级目录不存在
                    policyCategoryDataService.insertPolicyCategoryData(new PolicyCategoryData(null, 2, policyAPIDto.getApplicableArea(), null));
                    List<PolicyCategoryData> policyCategoryDataList1 = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, 2, policyAPIDto.getApplicableArea(), null));
                    //
                    policyPropertiesDataService.insertPolicyPropertiesData(new PolicyPropertiesData(null, policyId, policyCategoryDataList1.get(0).getId(), null));
                } else {
                    //二级目录存在
                    //判断该政策之前是否绑定过ApplicableArea,如果没有绑定就insert ppd，绑定过就换绑
                    policyPropertiesDataService.insertPolicyPropertiesData(new PolicyPropertiesData(null, policyId, policyCategoryDataList.get(0).getId(), null));
                }
            }
            if (!policyAPIDto.getLeaderDepartment().equals("")) {
                List<PolicyCategoryData> policyCategoryDataList = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, 1, policyAPIDto.getLeaderDepartment(), null));
                if (policyCategoryDataList.isEmpty()) {
                    policyCategoryDataService.insertPolicyCategoryData(new PolicyCategoryData(null, 1, policyAPIDto.getLeaderDepartment(), null));
                    List<PolicyCategoryData> policyCategoryDataList1 = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, 1, policyAPIDto.getLeaderDepartment(), null));
                    //
                    policyPropertiesDataService.insertPolicyPropertiesData(new PolicyPropertiesData(null, policyId, policyCategoryDataList1.get(0).getId(), null));
                } else {
                    policyPropertiesDataService.insertPolicyPropertiesData(new PolicyPropertiesData(null, policyId, policyCategoryDataList.get(0).getId(), null));
                }
            }
            if (!policyAPIDto.getPolicyClass().equals("")) {
                List<PolicyCategoryData> policyCategoryDataList = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, 3, policyAPIDto.getPolicyClass(), null));
                if (policyCategoryDataList.isEmpty()) {
                    policyCategoryDataService.insertPolicyCategoryData(new PolicyCategoryData(null, 3, policyAPIDto.getPolicyClass(), null));
                    List<PolicyCategoryData> policyCategoryDataList1 = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, 3, policyAPIDto.getPolicyClass(), null));
                    //
                    policyPropertiesDataService.insertPolicyPropertiesData(new PolicyPropertiesData(null, policyId, policyCategoryDataList1.get(0).getId(), null));
                } else {
                    policyPropertiesDataService.insertPolicyPropertiesData(new PolicyPropertiesData(null, policyId, policyCategoryDataList.get(0).getId(), null));
                }
            }
            if (!policyAPIDto.getPolicyType().equals("")) {
                List<PolicyCategoryData> policyCategoryDataList = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, 4, policyAPIDto.getPolicyType(), null));
                if (policyCategoryDataList.isEmpty()) {
                    policyCategoryDataService.insertPolicyCategoryData(new PolicyCategoryData(null, 4, policyAPIDto.getPolicyType(), null));
                    List<PolicyCategoryData> policyCategoryDataList1 = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, 4, policyAPIDto.getPolicyType(), null));
                    //
                    policyPropertiesDataService.insertPolicyPropertiesData(new PolicyPropertiesData(null, policyId, policyCategoryDataList1.get(0).getId(), null));
                } else {
                    policyPropertiesDataService.insertPolicyPropertiesData(new PolicyPropertiesData(null, policyId, policyCategoryDataList.get(0).getId(), null));
                }
            }
            if (!policyAPIDto.getDeclarationStatus().equals("")) {
                List<PolicyCategoryData> policyCategoryDataList = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, 5, policyAPIDto.getDeclarationStatus(), null));
                if (policyCategoryDataList.isEmpty()) {
                    policyCategoryDataService.insertPolicyCategoryData(new PolicyCategoryData(null, 5, policyAPIDto.getDeclarationStatus(), null));
                    List<PolicyCategoryData> policyCategoryDataList1 = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, 5, policyAPIDto.getDeclarationStatus(), null));
                    //
                    policyPropertiesDataService.insertPolicyPropertiesData(new PolicyPropertiesData(null, policyId, policyCategoryDataList1.get(0).getId(), null));
                } else {
                    policyPropertiesDataService.insertPolicyPropertiesData(new PolicyPropertiesData(null, policyId, policyCategoryDataList.get(0).getId(), null));
                }
            }
            if (policy.getPublishDate() == null) {
                continue;
            }
            //六大属性的发布时间感觉最好写
            LocalDate localDate = policyAPIDto.getPublishDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 定义输出格式为仅年份
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy");
            // 将LocalDate对象格式化为年份字符串
            String year = localDate.format(outputFormatter);
            List<PolicyCategoryData> policyCategoryDataList = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, 6, year, null));
            if (policyCategoryDataList.isEmpty()) {
                policyCategoryDataService.insertPolicyCategoryData(new PolicyCategoryData(null, 6, year, null));
                List<PolicyCategoryData> policyCategoryDataList1 = policyCategoryDataService.selectPolicyCategoryDataList(new PolicyCategoryData(null, 6, year, null));
                //
                policyPropertiesDataService.insertPolicyPropertiesData(new PolicyPropertiesData(null, policyId, policyCategoryDataList1.get(0).getId(), null));
            } else {
                policyPropertiesDataService.insertPolicyPropertiesData(new PolicyPropertiesData(null, policyId, policyCategoryDataList.get(0).getId(), null));
            }
            policies.add(policy); // 将 Policy 对象添加到列表中
            data.add(rowMap); // 将每行数据添加到列表中
        }

        workbook.close(); // 关闭工作簿
//        System.out.println(data);
        return Result.success("成功");
        // 调用服务类插入数据

    }

    // 检查文件是否为 Excel 格式
    private boolean isExcelFile(MultipartFile file) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        return (contentType != null && (contentType.equals("application/vnd.ms-excel") ||
                contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))) ||
                (fileName != null && (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")));
    }

    // 解析日期字符串
    private Date parseDate(String dateStr) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        try {
            LocalDate localDate = LocalDate.parse(dateStr, inputFormatter);
            // 转换为 java.util.Date
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            return null; // 或者抛出异常
        }
    }
}