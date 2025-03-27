package com.ruoyi.web.controller.company.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

public class Company extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Excel(name = "公司名称")
    private String companyName;

    @Excel(name = "法人名称")
    private String legalPerson;

    @Excel(name = "地址")
    private String address;
    private List<String> preAddress;

    @Excel(name = "统一社会信用代码")
    private String USCC;

    @Excel(name = "企业类型")
    private String companyType;

    @Excel(name = "登记机关")
    private String reqAuthority;

    @Excel(name = "成立日期")
    private String establishDate;

    @Excel(name = "经营范围")
    private String businessScope;

    @Excel(name = "主管税务局名称")
    private String cTaxAuthority;

    @Excel(name = "法人电话")
    private String phone;

    @Excel(name = "营业执照")
    private String bLicenseUrl;

    @Excel(name = "详细地址")
    private String detailAdd;

    @Excel(name = "企业所属行业")
    private String entBelongs;

    @Excel(name = "企业性质")
    private String entNature;

    @Excel(name = "1类知识产权")
    private String oneClass;

    @Excel(name = "2类知识产权")
    private String twoClass;

    @Excel(name = "自主研发1类知识产权")
    private String oneSelfClass;

    @Excel(name = "1类高价值知识产权")
    private String oneValueClass;

    @Excel(name = "数字化水平")
    private String digitalCulLevel;

    @Excel(name = "企业所属待定细分市场")
    private String entDetailMarket;

    @Excel(name = "企业从事细分市场年数")
    private String entDetailYear;

    @Excel(name = "唯一标识")
    private String openid;

    @Excel(name = "关联sys_user表")
    private Long sysUserId;



    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", legalPerson='" + legalPerson + '\'' +
                ", address='" + address + '\'' +
                ", USCC='" + USCC + '\'' +
                ", companyType='" + companyType + '\'' +
                ", reqAuthority='" + reqAuthority + '\'' +
                ", establishDate='" + establishDate + '\'' +
                ", businessScope='" + businessScope + '\'' +
                ", cTaxAuthority='" + cTaxAuthority + '\'' +
                ", phone='" + phone + '\'' +
                ", bLicenseUrl='" + bLicenseUrl + '\'' +
                ", detailAdd='" + detailAdd + '\'' +
                ", entBelongs='" + entBelongs + '\'' +
                ", entNature='" + entNature + '\'' +
                ", oneClass='" + oneClass + '\'' +
                ", twoClass='" + twoClass + '\'' +
                ", oneSelfClass='" + oneSelfClass + '\'' +
                ", oneValueClass='" + oneValueClass + '\'' +
                ", digitalCulLevel='" + digitalCulLevel + '\'' +
                ", entDetailMarket='" + entDetailMarket + '\'' +
                ", entDetailYear='" + entDetailYear + '\'' +
                ", openid='" + openid + '\'' +
                ", sysUserId=" + sysUserId +
                '}';
    }

    public List<String> getPreAddress() {
        return preAddress;
    }

    public void setPreAddress(List<String> preAddress) {
        this.preAddress = preAddress;
    }

    public Company(Integer id, String companyName, String legalPerson, String address, List<String> preAddress, String USCC, String companyType, String reqAuthority, String establishDate, String businessScope, String cTaxAuthority, String phone, String bLicenseUrl, String detailAdd, String entBelongs, String entNature, String oneClass, String twoClass, String oneSelfClass, String oneValueClass, String digitalCulLevel, String entDetailMarket, String entDetailYear) {
        this.id = id;
        this.companyName = companyName;
        this.legalPerson = legalPerson;
        this.address = address;
        this.preAddress = preAddress;
        this.USCC = USCC;
        this.companyType = companyType;
        this.reqAuthority = reqAuthority;
        this.establishDate = establishDate;
        this.businessScope = businessScope;
        this.cTaxAuthority = cTaxAuthority;
        this.phone = phone;
        this.bLicenseUrl = bLicenseUrl;
        this.detailAdd = detailAdd;
        this.entBelongs = entBelongs;
        this.entNature = entNature;
        this.oneClass = oneClass;
        this.twoClass = twoClass;
        this.oneSelfClass = oneSelfClass;
        this.oneValueClass = oneValueClass;
        this.digitalCulLevel = digitalCulLevel;
        this.entDetailMarket = entDetailMarket;
        this.entDetailYear = entDetailYear;
    }

    public Company(Integer id, String companyName, String legalPerson, String address) {
        this.id = id;
        this.companyName = companyName;
        this.legalPerson = legalPerson;
        this.address = address;
    }

    public Company(Integer id, String companyName, String legalPerson, String address, String USCC, String companyType, String reqAuthority, String establishDate, String businessScope, String cTaxAuthority, String phone, String bLicenseUrl) {
        this.id = id;
        this.companyName = companyName;
        this.legalPerson = legalPerson;
        this.address = address;
        this.USCC = USCC;
        this.companyType = companyType;
        this.reqAuthority = reqAuthority;
        this.establishDate = establishDate;
        this.businessScope = businessScope;
        this.cTaxAuthority = cTaxAuthority;
        this.phone = phone;
        this.bLicenseUrl = bLicenseUrl;
    }

    public Company(Integer id, String companyName, String legalPerson, String address, String USCC, String companyType, String reqAuthority, String establishDate, String businessScope, String cTaxAuthority, String phone, String bLicenseUrl, String detailAdd, String entBelongs, String entNature, String oneClass, String twoClass, String oneSelfClass, String oneValueClass, String digitalCulLevel, String entDetailMarket, String entDetailYear) {
        this.id = id;
        this.companyName = companyName;
        this.legalPerson = legalPerson;
        this.address = address;
        this.USCC = USCC;
        this.companyType = companyType;
        this.reqAuthority = reqAuthority;
        this.establishDate = establishDate;
        this.businessScope = businessScope;
        this.cTaxAuthority = cTaxAuthority;
        this.phone = phone;
        this.bLicenseUrl = bLicenseUrl;
        this.detailAdd = detailAdd;
        this.entBelongs = entBelongs;
        this.entNature = entNature;
        this.oneClass = oneClass;
        this.twoClass = twoClass;
        this.oneSelfClass = oneSelfClass;
        this.oneValueClass = oneValueClass;
        this.digitalCulLevel = digitalCulLevel;
        this.entDetailMarket = entDetailMarket;
        this.entDetailYear = entDetailYear;
    }

    public Company(Integer id, String companyName, String legalPerson, String address, String USCC, String companyType, String reqAuthority, String establishDate, String businessScope, String cTaxAuthority, String phone, String bLicenseUrl, String detailAdd, String entBelongs, String entNature, String oneClass, String twoClass, String oneSelfClass, String oneValueClass, String digitalCulLevel, String entDetailMarket, String entDetailYear, String openid) {
        this.id = id;
        this.companyName = companyName;
        this.legalPerson = legalPerson;
        this.address = address;
        this.USCC = USCC;
        this.companyType = companyType;
        this.reqAuthority = reqAuthority;
        this.establishDate = establishDate;
        this.businessScope = businessScope;
        this.cTaxAuthority = cTaxAuthority;
        this.phone = phone;
        this.bLicenseUrl = bLicenseUrl;
        this.detailAdd = detailAdd;
        this.entBelongs = entBelongs;
        this.entNature = entNature;
        this.oneClass = oneClass;
        this.twoClass = twoClass;
        this.oneSelfClass = oneSelfClass;
        this.oneValueClass = oneValueClass;
        this.digitalCulLevel = digitalCulLevel;
        this.entDetailMarket = entDetailMarket;
        this.entDetailYear = entDetailYear;
        this.openid = openid;
    }

    public Company(Integer id, String companyName, String legalPerson, String address, String USCC, String companyType, String reqAuthority, String establishDate, String businessScope, String cTaxAuthority, String phone, String bLicenseUrl, String detailAdd, String entBelongs, String entNature, String oneClass, String twoClass, String oneSelfClass, String oneValueClass, String digitalCulLevel, String entDetailMarket, String entDetailYear, String openid, Long sysUserId) {
        this.id = id;
        this.companyName = companyName;
        this.legalPerson = legalPerson;
        this.address = address;
        this.USCC = USCC;
        this.companyType = companyType;
        this.reqAuthority = reqAuthority;
        this.establishDate = establishDate;
        this.businessScope = businessScope;
        this.cTaxAuthority = cTaxAuthority;
        this.phone = phone;
        this.bLicenseUrl = bLicenseUrl;
        this.detailAdd = detailAdd;
        this.entBelongs = entBelongs;
        this.entNature = entNature;
        this.oneClass = oneClass;
        this.twoClass = twoClass;
        this.oneSelfClass = oneSelfClass;
        this.oneValueClass = oneValueClass;
        this.digitalCulLevel = digitalCulLevel;
        this.entDetailMarket = entDetailMarket;
        this.entDetailYear = entDetailYear;
        this.openid = openid;
        this.sysUserId = sysUserId;
    }

    public Company(String companyName, String openid, Long sysUserId) {
        this.companyName = companyName;
        this.openid = openid;
        this.sysUserId = sysUserId;
    }

    public Company(Integer id, String openid, Long sysUserId) {
        this.id = id;
        this.openid = openid;
        this.sysUserId = sysUserId;
    }

    public Company() {
    }
    public Company(Integer id){
        this.id=id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUSCC() {
        return USCC;
    }

    public void setUSCC(String USCC) {
        this.USCC = USCC;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getReqAuthority() {
        return reqAuthority;
    }

    public void setReqAuthority(String reqAuthority) {
        this.reqAuthority = reqAuthority;
    }

    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getcTaxAuthority() {
        return cTaxAuthority;
    }

    public void setcTaxAuthority(String cTaxAuthority) {
        this.cTaxAuthority = cTaxAuthority;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getbLicenseUrl() {
        return bLicenseUrl;
    }

    public void setbLicenseUrl(String bLicenseUrl) {
        this.bLicenseUrl = bLicenseUrl;
    }

    public String getDetailAdd() {
        return detailAdd;
    }

    public void setDetailAdd(String detailAdd) {
        this.detailAdd = detailAdd;
    }

    public String getEntBelongs() {
        return entBelongs;
    }

    public void setEntBelongs(String entBelongs) {
        this.entBelongs = entBelongs;
    }

    public String getEntNature() {
        return entNature;
    }

    public void setEntNature(String entNature) {
        this.entNature = entNature;
    }

    public String getOneClass() {
        return oneClass;
    }

    public void setOneClass(String oneClass) {
        this.oneClass = oneClass;
    }

    public String getTwoClass() {
        return twoClass;
    }

    public void setTwoClass(String twoClass) {
        this.twoClass = twoClass;
    }

    public String getOneSelfClass() {
        return oneSelfClass;
    }

    public void setOneSelfClass(String oneSelfClass) {
        this.oneSelfClass = oneSelfClass;
    }

    public String getOneValueClass() {
        return oneValueClass;
    }

    public void setOneValueClass(String oneValueClass) {
        this.oneValueClass = oneValueClass;
    }

    public String getDigitalCulLevel() {
        return digitalCulLevel;
    }

    public void setDigitalCulLevel(String digitalCulLevel) {
        this.digitalCulLevel = digitalCulLevel;
    }

    public String getEntDetailMarket() {
        return entDetailMarket;
    }

    public void setEntDetailMarket(String entDetailMarket) {
        this.entDetailMarket = entDetailMarket;
    }

    public String getEntDetailYear() {
        return entDetailYear;
    }

    public void setEntDetailYear(String entDetailYear) {
        this.entDetailYear = entDetailYear;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
