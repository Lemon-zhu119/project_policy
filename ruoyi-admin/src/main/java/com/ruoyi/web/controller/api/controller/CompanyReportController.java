package com.ruoyi.web.controller.api.controller;

import com.ruoyi.web.controller.api.common.Result;
import com.ruoyi.web.controller.company.domain.CompanyReport;
import com.ruoyi.web.controller.company.service.ICompanyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/policy")
public class CompanyReportController {
    @Autowired
    private ICompanyReportService companyReportService;
    @GetMapping("/enterprisereport")
    public Result<CompanyReport> selectCompanyReportByYear(@RequestParam String year, @RequestParam Integer companyId){
        Integer currentYear= LocalDate.now().getYear();
        CompanyReport selectCompanyReport=companyReportService.selectCompanyReportByYear(year,companyId);
        if (selectCompanyReport!=null){
            return Result.success(selectCompanyReport);
        }else {
            if(Integer.parseInt(year)>currentYear+5||Integer.parseInt(year)<currentYear-5){
                return Result.error("目前尚不支持该年份年报查询和修改");
            }
            Integer loopYear=Integer.parseInt(year);
            if(Integer.parseInt(year)>currentYear){
                for(;loopYear>=currentYear-5;loopYear--){
                    CompanyReport selectLoopCompanyReport=companyReportService.selectCompanyReportByYear(String.valueOf(loopYear),companyId);
                    if(selectLoopCompanyReport!=null){
                        break;
                    }
                }

                if(loopYear>=currentYear-5){
                    CompanyReport selectLoopCompanyReport=companyReportService.selectCompanyReportByYear(String.valueOf(loopYear),companyId);
                    loopYear++;
                    for(;loopYear<=Integer.parseInt(year);loopYear++){
                        selectLoopCompanyReport.setYear(String.valueOf(loopYear));
                        companyReportService.insertCompanyReport(selectLoopCompanyReport);
                    }
                }

            }else if (Integer.parseInt(year)<currentYear){
                for(;loopYear<=currentYear+5;loopYear++){
                    CompanyReport selectLoopCompanyReport=companyReportService.selectCompanyReportByYear(String.valueOf(loopYear),companyId);
                    if(selectLoopCompanyReport!=null){
                        break;
                    }
                }

                if(loopYear<=currentYear+5){
                    CompanyReport selectLoopCompanyReport=companyReportService.selectCompanyReportByYear(String.valueOf(loopYear),companyId);
                    loopYear--;
                    for(;loopYear>=Integer.parseInt(year);loopYear--){
                        selectLoopCompanyReport.setYear(String.valueOf(loopYear));
                        companyReportService.insertCompanyReport(selectLoopCompanyReport);
                    }
                }
            }else {
                CompanyReport companyReport=new CompanyReport(null,String.valueOf(currentYear),0,"0","0","0","0","0","0","0");
                companyReportService.insertCompanyReport(companyReport);
            }
        }
        selectCompanyReport=companyReportService.selectCompanyReportByYear(year,companyId);
        return Result.success(selectCompanyReport);
    }
    @PostMapping("/putenterprisereport")
    @ResponseBody
    public Result<String> updateCompanyReport(@RequestParam(value = "year", required = false) String year, @RequestParam(value = "companyId", required = false) Integer companyId
            ,@RequestParam(value = "yinyeshouruTotal", required = false) String yinyeshouruTotal,@RequestParam(value = "yanfaTotal", required = false) String yanfaTotal,
                                              @RequestParam(value = "zichanTotal", required = false) String zichanTotal,@RequestParam(value = "xinzenguqTotal", required = false) String xinzenguqTotal,
                                              @RequestParam(value = "xiaoshouTotal", required = false) String xiaoshouTotal,@RequestParam(value = "zhuyingTotal", required = false) String zhuyingTotal,
                                              @RequestParam(value = "fuzhaiTotal", required = false) String fuzhaiTotal,@RequestParam(value = "jinglirunTotal", required = false) String jinglirunTotal){
        CompanyReport selectCompanyReport=companyReportService.selectCompanyReportByYear(year,companyId);
        if (selectCompanyReport!=null){
            CompanyReport companyReport=new CompanyReport(selectCompanyReport.getId(),year,companyId,yanfaTotal,zichanTotal,xinzenguqTotal,xiaoshouTotal,zhuyingTotal,fuzhaiTotal,jinglirunTotal,yinyeshouruTotal);
            int update=companyReportService.updateCompanyReport(companyReport);
            if (update==1){
                return Result.success("提交并且更新成功");
            }else {
                return Result.error("提交失败");
            }
        }else if (selectCompanyReport==null){
            CompanyReport companyReport=new CompanyReport(null,year,companyId,yanfaTotal,zichanTotal,xinzenguqTotal,xiaoshouTotal,zhuyingTotal,fuzhaiTotal,jinglirunTotal,yinyeshouruTotal);
            int insert=companyReportService.insertCompanyReport(companyReport);
            if(insert==1){
                return Result.success("提交并且插入成功");
            }else {
                return Result.error("提交失败");
            }
        }else {
            return Result.error("提交失败");
        }
    }
}
