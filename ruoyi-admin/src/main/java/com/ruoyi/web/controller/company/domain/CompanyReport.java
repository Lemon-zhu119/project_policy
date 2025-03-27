package com.ruoyi.web.controller.company.domain;

public class CompanyReport {
    private Integer id;
    private String year;
    private Integer companyId;
    private String yanfaTotal;
    private String zichanTotal;
    private String xinzenguqTotal;
    private String xiaoshouTotal;
    private String zhuyingTotal;
    private String fuzhaiTotal;
    private String jinglirunTotal;
    private String yinyeshouruTotal;

    @Override
    public String toString() {
        return "CompanyReport{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", companyId=" + companyId +
                ", yanfaTotal='" + yanfaTotal + '\'' +
                ", zichanTotal='" + zichanTotal + '\'' +
                ", xinzenguqTotal='" + xinzenguqTotal + '\'' +
                ", xiaoshouTotal='" + xiaoshouTotal + '\'' +
                ", zhuyingTotal='" + zhuyingTotal + '\'' +
                ", fuzhaiTotal='" + fuzhaiTotal + '\'' +
                ", jinglirunTotal='" + jinglirunTotal + '\'' +
                ", yinyeshouruTotal='" + yinyeshouruTotal + '\'' +
                '}';
    }

    public String getYinyeshouruTotal() {
        return yinyeshouruTotal;
    }

    public void setYinyeshouruTotal(String yinyeshouruTotal) {
        this.yinyeshouruTotal = yinyeshouruTotal;
    }

    public CompanyReport(Integer id, String year, Integer companyId, String yanfaTotal, String zichanTotal, String xinzenguqTotal, String xiaoshouTotal, String zhuyingTotal, String fuzhaiTotal, String jinglirunTotal, String yinyeshouruTotal) {
        this.id = id;
        this.year = year;
        this.companyId = companyId;
        this.yanfaTotal = yanfaTotal;
        this.zichanTotal = zichanTotal;
        this.xinzenguqTotal = xinzenguqTotal;
        this.xiaoshouTotal = xiaoshouTotal;
        this.zhuyingTotal = zhuyingTotal;
        this.fuzhaiTotal = fuzhaiTotal;
        this.jinglirunTotal = jinglirunTotal;
        this.yinyeshouruTotal = yinyeshouruTotal;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public CompanyReport(Integer id, String year, Integer companyId, String yanfaTotal, String zichanTotal, String xinzenguqTotal, String xiaoshouTotal, String zhuyingTotal, String fuzhaiTotal, String jinglirunTotal) {
        this.id = id;
        this.year = year;
        this.companyId = companyId;
        this.yanfaTotal = yanfaTotal;
        this.zichanTotal = zichanTotal;
        this.xinzenguqTotal = xinzenguqTotal;
        this.xiaoshouTotal = xiaoshouTotal;
        this.zhuyingTotal = zhuyingTotal;
        this.fuzhaiTotal = fuzhaiTotal;
        this.jinglirunTotal = jinglirunTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYanfaTotal() {
        return yanfaTotal;
    }

    public void setYanfaTotal(String yanfaTotal) {
        this.yanfaTotal = yanfaTotal;
    }

    public String getZichanTotal() {
        return zichanTotal;
    }

    public void setZichanTotal(String zichanTotal) {
        this.zichanTotal = zichanTotal;
    }

    public String getXinzenguqTotal() {
        return xinzenguqTotal;
    }

    public void setXinzenguqTotal(String xinzenguqTotal) {
        this.xinzenguqTotal = xinzenguqTotal;
    }

    public String getXiaoshouTotal() {
        return xiaoshouTotal;
    }

    public void setXiaoshouTotal(String xiaoshouTotal) {
        this.xiaoshouTotal = xiaoshouTotal;
    }

    public String getZhuyingTotal() {
        return zhuyingTotal;
    }

    public void setZhuyingTotal(String zhuyingTotal) {
        this.zhuyingTotal = zhuyingTotal;
    }

    public String getFuzhaiTotal() {
        return fuzhaiTotal;
    }

    public void setFuzhaiTotal(String fuzhaiTotal) {
        this.fuzhaiTotal = fuzhaiTotal;
    }

    public String getJinglirunTotal() {
        return jinglirunTotal;
    }

    public void setJinglirunTotal(String jinglirunTotal) {
        this.jinglirunTotal = jinglirunTotal;
    }

    public CompanyReport() {
    }

    public CompanyReport(String year, Integer companyId, String yanfaTotal, String zichanTotal, String xinzenguqTotal, String xiaoshouTotal, String zhuyingTotal, String fuzhaiTotal, String jinglirunTotal) {
        this.year = year;
        this.companyId = companyId;
        this.yanfaTotal = yanfaTotal;
        this.zichanTotal = zichanTotal;
        this.xinzenguqTotal = xinzenguqTotal;
        this.xiaoshouTotal = xiaoshouTotal;
        this.zhuyingTotal = zhuyingTotal;
        this.fuzhaiTotal = fuzhaiTotal;
        this.jinglirunTotal = jinglirunTotal;
    }

    public CompanyReport(Integer id, String year, String yanfaTotal, String zichanTotal, String xinzenguqTotal, String xiaoshouTotal, String zhuyingTotal, String fuzhaiTotal, String jinglirunTotal) {
        this.id = id;
        this.year = year;
        this.yanfaTotal = yanfaTotal;
        this.zichanTotal = zichanTotal;
        this.xinzenguqTotal = xinzenguqTotal;
        this.xiaoshouTotal = xiaoshouTotal;
        this.zhuyingTotal = zhuyingTotal;
        this.fuzhaiTotal = fuzhaiTotal;
        this.jinglirunTotal = jinglirunTotal;
    }
}
