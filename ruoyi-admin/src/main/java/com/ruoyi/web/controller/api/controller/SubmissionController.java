package com.ruoyi.web.controller.api.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.web.controller.api.common.Result;
import com.ruoyi.web.controller.api.domain.Material;
import com.ruoyi.web.controller.api.domain.ShenBao;
import com.ruoyi.web.controller.api.domain.ShenHe;
import com.ruoyi.web.controller.api.domain.dto.MaterialResult;
import com.ruoyi.web.controller.api.service.MaterialService;
import com.ruoyi.web.controller.api.service.PolicyLibraryService;
import com.ruoyi.web.controller.company.domain.Company;
import com.ruoyi.web.controller.company.service.impl.CompanyServiceImpl;
import com.ruoyi.web.controller.policy.domain.CompanyPolicySubmissionDocument;
import com.ruoyi.web.controller.policy.domain.Policy;
import com.ruoyi.web.controller.policy.service.ICompanyPolicySubmissionDocumentService;
import com.ruoyi.web.controller.policy.service.IPolicyService;
import com.ruoyi.web.controller.submission.domain.CompanyPolicySubmission;
import com.ruoyi.web.controller.submission.service.ICompanyPolicySubmissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/policy")
public class SubmissionController {
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IPolicyService policyService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private ICompanyPolicySubmissionService companyPolicySubmissionService;
    @Autowired
    private ICompanyPolicySubmissionDocumentService companyPolicySubmissionDocumentService;

    @Autowired
    private PolicyLibraryService policyLibraryService;
    @Autowired
    private CompanyServiceImpl companyService;
    //本地测试用
//    private String filePath = System.getProperty("user.dir") + "/files/";
    //服务器内部路径
    private String filePath = "/root/ruoyi-server/files/";

    @GetMapping("/cailiao")
    public Result<List<Material>> getCL(@RequestParam Integer id,
                                        @RequestParam String modelName) {
        List<Material> caiLiaos = materialService.getMaterial(id, modelName);
        return Result.success(caiLiaos);
    }

    @GetMapping("/cailiaonumber")
    public Result<List<Map<String, Object>>> getDocNumber() {
        List<Map<String, Object>> docNumbers = new ArrayList<>();
        Integer year = LocalDate.now().getYear();
        List<Policy> policyModelList = policyService.selectCustomModelList(new Policy(1));
        Integer finalYear = year;
        policyModelList = policyModelList.stream()
                .filter(policy -> policy.getName().startsWith(String.valueOf(finalYear)))
                .collect(Collectors.toList());
        for (Policy policyModel : policyModelList) {
            if (policyModel.getDocNumber() == null) {
                policyModel.setDocNumber(0);
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("modelName", policyModel.getName());
                map.put("docNumber", policyModel.getDocNumber());
                docNumbers.add(map);
            }
        }
        return Result.success(docNumbers);
    }

    @GetMapping("/cailiaoqingdan")
    public Result<Map<String, Object>> getDocDetail(@RequestParam String modelName) {
        Map<String, Object> docDetail = new HashMap<>();
        Policy policy = new Policy();
        policy.setName(modelName);
        List<Policy> policyModelList = policyService.selectCustomModelList(policy);
        if (policyModelList.isEmpty()) {
            return Result.error("传入的模型名称有误");
        } else {
            policy = policyModelList.get(0);
            docDetail.put("docDetail", policy.getDocDetail());
        }
        return Result.success(docDetail);
    }

    @GetMapping("/getmodelmaterialinfo")
    public Result<List<MaterialResult>> getModelMaterialInfo(@RequestParam("id") Integer id,
                                                             @RequestParam("modelName") String modelName,
                                                             @RequestParam(required = false) String year) {
        List<MaterialResult> materialResults = new ArrayList<>();
        List<Policy> policies = policyService.selectPolicyByName(modelName);
        List<Company> companies = companyService.selectCompanyListById(id);
        if (policies.isEmpty() || companies.isEmpty()) {
            return Result.error("输入的模型名称或企业id有误");
        } else {
            if (policies.get(0).getDocDetail() == null || policies.get(0).getDocDetail().equals("")) {
                return Result.error("当前模型没有材料需要提交");
            }
            String caiLiao = policies.get(0).getDocDetail().trim(); // 去除行首行尾的空格
            if (caiLiao.endsWith(";")) { // 检查是否以分号结尾
                caiLiao = caiLiao.substring(0, caiLiao.length() - 1); // 去除分号
            }
            List<CompanyPolicySubmission> companyPolicySubmissionList = companyPolicySubmissionService.selectCompanyPolicySubmissionList(new CompanyPolicySubmission(id, policies.get(0).getId()));
            String[] policyDocDetailList = caiLiao.split(";");
            int clId = -1;
                for (String policyDocDetail : policyDocDetailList) {
                    clId++;
                    MaterialResult materialResult = new MaterialResult();
                    materialResult.setMaterialName(policyDocDetail);
                    materialResult.setModelName(modelName);
                    materialResult.setId(clId);
                    if(companyPolicySubmissionList.isEmpty()){
                        materialResult.setMaterialId(0);
                        materialResult.setMaterialStatus(0);
                        materialResults.add(materialResult);
                        continue;
                    }
                    List<CompanyPolicySubmissionDocument> companyPolicySubmissionDocumentList = companyPolicySubmissionDocumentService.selectCompanyPolicySubmissionDocumentByName(companyPolicySubmissionList.get(0).getId(), policyDocDetail);
                    if (companyPolicySubmissionDocumentList.isEmpty()) {
                        materialResult.setMaterialId(0);
                        materialResult.setMaterialStatus(0);
                    } else {
                        materialResult.setMaterialId(companyPolicySubmissionDocumentList.get(0).getId());
                        materialResult.setMaterialStatus(1);
                    }
                    materialResults.add(materialResult);
                }
        }
        return Result.success(materialResults);
    }

    @PostMapping("/materialupload")
    public Result upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("materialName") String materialName,
                         @RequestParam("id") Integer id,
                         @RequestParam("modelName") String modelName) {
        List<Company> companies = companyService.selectCompanyList(new Company(id));
        if (companies.isEmpty()) {
            return Result.error("该公司不存在");
        }
        String flag;
        synchronized (PolicyDtoController.class) {
            flag = System.currentTimeMillis() + "";
            System.out.print(flag);
            try {
                Thread.sleep(1L); // 确保flag的唯一性
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        String fileName = file.getOriginalFilename();
        try {
            if (!new File(filePath).isDirectory()) {
                new File(filePath).mkdirs(); // 创建目录
            }
            // 文件存储形式：时间戳-文件名
            String fullPath = filePath + flag + "-" + fileName;
            file.transferTo(new File(fullPath)); // 保存文件
                System.out.println(fileName + "--上传成功");
            // 构造文件访问URL
            //本地测试用
//            String http = "http://localhost:8080/files/" + flag + "-" + fileName;
            //服务器用
            String http = "http://10.10.49.254:5388/files/" + flag + "-" + fileName;
            List<Policy> policies = policyService.selectPolicyByName(modelName);
            List<String> record = new ArrayList<>();
            if (!policies.isEmpty()) {
                Policy policy = policies.get(0);
                CompanyPolicySubmission companyPolicySubmission = new CompanyPolicySubmission();
                // 获取当前的日期时间，精度到秒
                ZonedDateTime zonedDateTime = ZonedDateTime.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS);
                Date date = Date.from(zonedDateTime.toInstant());
                companyPolicySubmission.setUserId(id);
                companyPolicySubmission.setPolicyId(policy.getId());
                List<CompanyPolicySubmission> companyPolicySubmissions = companyPolicySubmissionService.selectCompanyPolicySubmissionList(companyPolicySubmission);
                if (companyPolicySubmissions.isEmpty()) {
                    companyPolicySubmission.setDate(date);
                    companyPolicySubmission.setMaterialStatus("0");
                    companyPolicySubmission.setSubmissionStatus("2");
                    int insertCompanyPolicySubmission = companyPolicySubmissionService.insertCompanyPolicySubmission(companyPolicySubmission);
                    if (insertCompanyPolicySubmission == 1) {
                        record.add("插入" + companyPolicySubmission.toString());
                    } else {
                        return Result.error("提交成功插入submission失败");
                    }
                }
                CompanyPolicySubmissionDocument companyPolicySubmissionDocumentDB = new CompanyPolicySubmissionDocument();
                CompanyPolicySubmissionDocument companyPolicySubmissionDocument = new CompanyPolicySubmissionDocument();
                List<CompanyPolicySubmission> companyPolicySubmissionList = companyPolicySubmissionService.selectCompanyPolicySubmissionList(companyPolicySubmission);
                if (!companyPolicySubmissionList.isEmpty()) {
                    companyPolicySubmissionDocument.setSubmissionId(companyPolicySubmissionList.get(0).getId());
                    companyPolicySubmissionDocument.setUserId(id);
                    BeanUtils.copyProperties(companyPolicySubmissionDocument, companyPolicySubmissionDocumentDB);
                    companyPolicySubmissionDocument.setName(materialName);
                    List<CompanyPolicySubmissionDocument> companyPolicySubmissionDocuments = companyPolicySubmissionDocumentService.selectCompanyPolicySubmissionDocumentList(companyPolicySubmissionDocument);
                    if (!companyPolicySubmissionDocuments.isEmpty()) {
                        companyPolicySubmissionDocument.setId(companyPolicySubmissionDocuments.get(0).getId());
                        companyPolicySubmissionDocument.setUploadDate(date);
                        companyPolicySubmissionDocument.setFilePath(http);
                        int updateCompanyPolicySubmissionDocument = companyPolicySubmissionDocumentService.updateCompanyPolicySubmissionDocument(companyPolicySubmissionDocument);
                        if (updateCompanyPolicySubmissionDocument == 1) {
                            record.add("更新" + companyPolicySubmissionDocument.toString());
                        } else {
                            return Result.error("上传失败");
                        }
                    } else {
                        companyPolicySubmissionDocument.setUploadDate(date);
                        companyPolicySubmissionDocument.setFilePath(http);
                        int insertCompanyPolicySubmissionDocument = companyPolicySubmissionDocumentService.insertCompanyPolicySubmissionDocument(companyPolicySubmissionDocument);
                        if (insertCompanyPolicySubmissionDocument == 1) {
                            record.add("插入" + companyPolicySubmissionDocument.toString());
                        } else {
                            return Result.error("上传失败");
                        }
                    }
                } else {
                    return Result.error("上传失败");
                }
                //此处为获取当前材料数量和模型材料数量是否相等，相等即可改变材料状态为1
                List<CompanyPolicySubmissionDocument> companyPolicySubmissionDocumentListNow = companyPolicySubmissionDocumentService.selectCompanyPolicySubmissionDocumentList(companyPolicySubmissionDocumentDB);
                if (companyPolicySubmissionDocumentListNow.size() >= policy.getDocNumber()) {
                    companyPolicySubmission.setId(companyPolicySubmissions.get(0).getId());
                    companyPolicySubmission.setDate(date);
                    companyPolicySubmission.setMaterialStatus("1");
                    companyPolicySubmission.setSubmissionStatus("2");
                    int updateCompanyPolicySubmission = companyPolicySubmissionService.updateCompanyPolicySubmission(companyPolicySubmission);
                    if (updateCompanyPolicySubmission == 1) {
                        record.add("修改" + companyPolicySubmission.toString());
                    } else {
                        return Result.error("提交成功插入submission失败");
                    }
                }
                System.out.println(record);
                return Result.success("上传成功");
            } else {
                return Result.error("上传失败");
            }
        } catch (Exception e) {
            System.err.println(fileName + "--文件上传失败");
            return Result.error("上传失败");
        }
    }

    @DeleteMapping("/deletematerial/{materialId}")
    public AjaxResult removeMaterial(@PathVariable Integer materialId) {
        CompanyPolicySubmissionDocument companyPolicySubmissionDocumentDB = companyPolicySubmissionDocumentService.selectCompanyPolicySubmissionDocumentById(materialId);
        if (companyPolicySubmissionDocumentDB == null) {
            return AjaxResult.error("并无此材料");
        }
        int del = companyPolicySubmissionDocumentService.deleteCompanyPolicySubmissionDocumentById(materialId);
        if (del == 1) {
            CompanyPolicySubmission companyPolicySubmission = companyPolicySubmissionService.selectCompanyPolicySubmissionById(companyPolicySubmissionDocumentDB.getSubmissionId());
            if (!(companyPolicySubmission == null)) {
                companyPolicySubmission.setMaterialStatus("0");
                companyPolicySubmissionService.updateCompanyPolicySubmission(companyPolicySubmission);
            }
        }
        return AjaxResult.success("删除成功");
    }

    @GetMapping("/shenbaoprogress")
    public Result<ShenBao> getShenBao(@RequestParam Integer id, @RequestParam String modelName) {
        List<Policy> policies = policyService.selectPolicyByName(modelName);
        if (policies.isEmpty()) {
            return Result.error("模型名称有误请查看");
        } else {
            ShenBao shenBao = policyLibraryService.getShenBao(id, modelName);
            if (shenBao == null) {
                return Result.error("未找到此条申报");
            } else {
                return Result.success(shenBao);
            }

        }
    }

    @GetMapping("/sheheinfo")
    public Result<ShenHe> getShenHe(@RequestParam Integer id, @RequestParam String modelName) {
        ShenHe shenHe = policyLibraryService.getShenHe(id, modelName);
        return Result.success(shenHe);
    }

    @DeleteMapping("/deletecailiao")
    public Result<String> deleteCaiLiao(@RequestParam Integer id, @RequestParam String modelName) {
        List<Policy> policies = policyService.selectPolicyByName(modelName);
        if (policies.isEmpty()) {
            return Result.error("模型名称输入错误");
        }
        List<CompanyPolicySubmission> companyPolicySubmissions = companyPolicySubmissionService.selectCompanyPolicySubmissionList(new CompanyPolicySubmission(id, policies.get(0).getId()));
        if (!companyPolicySubmissions.isEmpty()) {
            int del = companyPolicySubmissionDocumentService.deleteCompanyPolicySubmissionDocumentBySubmissionId(companyPolicySubmissions.get(0).getId());
            if (del == 1) {
                CompanyPolicySubmission companyPolicySubmission = companyPolicySubmissions.get(0);
                companyPolicySubmission.setProgressDescription("尚未补交材料");
                companyPolicySubmission.setMaterialStatus("0");
                companyPolicySubmission.setSubmissionStatus("2");
                int update = companyPolicySubmissionService.updateCompanyPolicySubmission(companyPolicySubmission);
                if (update == 1) {
                    return Result.success("删除成功");
                } else {
                    return Result.error("删除成功但更新材料状态失败");
                }

            } else {
                return Result.error("删除失败");
            }
        } else {
            return Result.error("你看看这个id和这个模型名称对吗你就删");
        }

    }
}
