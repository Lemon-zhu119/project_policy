package com.ruoyi.web.controller.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class PolicyAPIDto extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 政策名称
     */
    @Excel(name = "政策名称")
    private String policyName;
    private String name;

    /**
     * 政策发布时间
     */
    @Excel(name = "政策内容")
    private String content;

    /**
     * 政策过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "政策条件", width = 30, dateFormat = "yyyy-MM-dd")
    private String formula;

    /**
     * 政策申报状态
     */
    @Excel(name = "政策发布时间")
    private Date publishDate;
    @Excel(name = "政策截止时间")
    private Date expireDate;

    /**
     * $column.columnComment
     */
    @Excel(name = "政策原文链接", readConverterExp = "$column.readConverterExp()")
    private String policyContent;
    private String contact;


    /**
     * $column.columnComment
     */
    @Excel(name = "电话号", readConverterExp = "$column.readConverterExp()")
    private String phone;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String deadTime;
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String description;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String isMoney;

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer recLevel;
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer hotLevel;
    private String policyObject;
    private String fileUrl;
    private String shengyuTime;
    private String money;
    private String source;
    private Integer docNumber;
    private String docDetail;
    private String policyDocDetail;
    //作废
    private String policyCondition;
    //
    private String policyConditions;
    private String policyFileUrl;

    @Override
    public String toString() {
        return "PolicyAPIDto{" +
                "id=" + id +
                ", policyName='" + policyName + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", formula='" + formula + '\'' +
                ", publishDate=" + publishDate +
                ", expireDate=" + expireDate +
                ", policyContent='" + policyContent + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", deadTime='" + deadTime + '\'' +
                ", description='" + description + '\'' +
                ", isMoney='" + isMoney + '\'' +
                ", recLevel=" + recLevel +
                ", hotLevel=" + hotLevel +
                ", policyObject='" + policyObject + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", shengyuTime='" + shengyuTime + '\'' +
                ", money='" + money + '\'' +
                ", source='" + source + '\'' +
                ", docNumber=" + docNumber +
                ", docDetail='" + docDetail + '\'' +
                ", policyDocDetail='" + policyDocDetail + '\'' +
                ", policyCondition='" + policyCondition + '\'' +
                ", policyConditions='" + policyConditions + '\'' +
                ", policyFileUrl='" + policyFileUrl + '\'' +
                ", leaderDepartment='" + leaderDepartment + '\'' +
                ", applicableArea='" + applicableArea + '\'' +
                ", policyClass='" + policyClass + '\'' +
                ", policyType='" + policyType + '\'' +
                ", declarationStatus='" + declarationStatus + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                '}';
    }

    public PolicyAPIDto(Integer id, String policyName, String name, String content, String formula, Date publishDate, Date expireDate, String policyContent, String contact, String phone, String deadTime, String description, String isMoney, Integer recLevel, Integer hotLevel, String policyObject, String fileUrl, String shengyuTime, String money, String source, Integer docNumber, String docDetail, String policyDocDetail, String policyCondition, String policyConditions, String policyFileUrl, String leaderDepartment, String applicableArea, String policyClass, String policyType, String declarationStatus, String releaseTime) {
        this.id = id;
        this.policyName = policyName;
        this.name = name;
        this.content = content;
        this.formula = formula;
        this.publishDate = publishDate;
        this.expireDate = expireDate;
        this.policyContent = policyContent;
        this.contact = contact;
        this.phone = phone;
        this.deadTime = deadTime;
        this.description = description;
        this.isMoney = isMoney;
        this.recLevel = recLevel;
        this.hotLevel = hotLevel;
        this.policyObject = policyObject;
        this.fileUrl = fileUrl;
        this.shengyuTime = shengyuTime;
        this.money = money;
        this.source = source;
        this.docNumber = docNumber;
        this.docDetail = docDetail;
        this.policyDocDetail = policyDocDetail;
        this.policyCondition = policyCondition;
        this.policyConditions = policyConditions;
        this.policyFileUrl = policyFileUrl;
        this.leaderDepartment = leaderDepartment;
        this.applicableArea = applicableArea;
        this.policyClass = policyClass;
        this.policyType = policyType;
        this.declarationStatus = declarationStatus;
        this.releaseTime = releaseTime;
    }

    public String getPolicyFileUrl() {
        return policyFileUrl;
    }

    public void setPolicyFileUrl(String policyFileUrl) {
        this.policyFileUrl = policyFileUrl;
    }

    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String leaderDepartment;
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String applicableArea;
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String policyClass;
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String policyType;
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String declarationStatus;
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String releaseTime;

    public PolicyAPIDto(Integer id, String policyName, String name, String content, String formula, Date publishDate, Date expireDate, String policyContent, String contact, String phone, String deadTime, String description, String isMoney, Integer recLevel, Integer hotLevel, String policyObject, String fileUrl, String shengyuTime, String money, String source, Integer docNumber, String docDetail, String policyDocDetail, String policyCondition, String policyConditions, String leaderDepartment, String applicableArea, String policyClass, String policyType, String declarationStatus, String releaseTime) {
        this.id = id;
        this.policyName = policyName;
        this.name = name;
        this.content = content;
        this.formula = formula;
        this.publishDate = publishDate;
        this.expireDate = expireDate;
        this.policyContent = policyContent;
        this.contact = contact;
        this.phone = phone;
        this.deadTime = deadTime;
        this.description = description;
        this.isMoney = isMoney;
        this.recLevel = recLevel;
        this.hotLevel = hotLevel;
        this.policyObject = policyObject;
        this.fileUrl = fileUrl;
        this.shengyuTime = shengyuTime;
        this.money = money;
        this.source = source;
        this.docNumber = docNumber;
        this.docDetail = docDetail;
        this.policyDocDetail = policyDocDetail;
        this.policyCondition = policyCondition;
        this.policyConditions = policyConditions;
        this.leaderDepartment = leaderDepartment;
        this.applicableArea = applicableArea;
        this.policyClass = policyClass;
        this.policyType = policyType;
        this.declarationStatus = declarationStatus;
        this.releaseTime = releaseTime;
    }

    public String getPolicyContent() {
        return policyContent;
    }

    public void setPolicyContent(String policyContent) {
        this.policyContent = policyContent;
    }

    public String getPolicyConditions() {
        return policyConditions;
    }

    public void setPolicyConditions(String policyConditions) {
        this.policyConditions = policyConditions;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public PolicyAPIDto(Integer id, String name, String content, String formula, Date publishDate, Date expireDate, String contact, String phone, String deadTime, String description, String isMoney, Integer recLevel, Integer hotLevel, String policyObject, String fileUrl, String shengyuTime, String money, String source, Integer docNumber, String docDetail, String policyDocDetail, String policyCondition, String leaderDepartment, String applicableArea, String policyClass, String policyType, String declarationStatus, String releaseTime) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.formula = formula;
        this.publishDate = publishDate;
        this.expireDate = expireDate;
        this.contact = contact;
        this.phone = phone;
        this.deadTime = deadTime;
        this.description = description;
        this.isMoney = isMoney;
        this.recLevel = recLevel;
        this.hotLevel = hotLevel;
        this.policyObject = policyObject;
        this.fileUrl = fileUrl;
        this.shengyuTime = shengyuTime;
        this.money = money;
        this.source = source;
        this.docNumber = docNumber;
        this.docDetail = docDetail;
        this.policyDocDetail = policyDocDetail;
        this.policyCondition = policyCondition;
        this.leaderDepartment = leaderDepartment;
        this.applicableArea = applicableArea;
        this.policyClass = policyClass;
        this.policyType = policyType;
        this.declarationStatus = declarationStatus;
        this.releaseTime = releaseTime;
    }

    public PolicyAPIDto(Integer id, String name, String content, String formula, Date publishDate, String contact, String phone, String deadTime, String isMoney, Integer recLevel, Integer hotLevel, String fileUrl, String shengyuTime, String money, String source, String leaderDepartment, String applicableArea, String policyClass, String policyType, String declarationStatus, String releaseTime) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.formula = formula;
        this.publishDate = publishDate;
        this.contact = contact;
        this.phone = phone;
        this.deadTime = deadTime;
        this.isMoney = isMoney;
        this.recLevel = recLevel;
        this.hotLevel = hotLevel;
        this.fileUrl = fileUrl;
        this.shengyuTime = shengyuTime;
        this.money = money;
        this.source = source;
        this.leaderDepartment = leaderDepartment;
        this.applicableArea = applicableArea;
        this.policyClass = policyClass;
        this.policyType = policyType;
        this.declarationStatus = declarationStatus;
        this.releaseTime = releaseTime;
    }

    public PolicyAPIDto(Integer id, String name, String content, String formula, Date publishDate, String contact, String phone, String deadTime, String isMoney, Integer recLevel, Integer hotLevel, String fileUrl, String shengyuTime, String leaderDepartment, String applicableArea, String policyClass, String policyType, String declarationStatus, String releaseTime) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.formula = formula;
        this.publishDate = publishDate;
        this.contact = contact;
        this.phone = phone;
        this.deadTime = deadTime;
        this.isMoney = isMoney;
        this.recLevel = recLevel;
        this.hotLevel = hotLevel;
        this.fileUrl = fileUrl;
        this.shengyuTime = shengyuTime;
        this.leaderDepartment = leaderDepartment;
        this.applicableArea = applicableArea;
        this.policyClass = policyClass;
        this.policyType = policyType;
        this.declarationStatus = declarationStatus;
        this.releaseTime = releaseTime;
    }

    public PolicyAPIDto(Integer id, String name, String content, String formula, Date publishDate, String contact, String phone, String deadTime, String isMoney, Integer recLevel, Integer hotLevel, String fileUrl, String leaderDepartment, String applicableArea, String policyClass, String policyType, String declarationStatus, String releaseTime) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.formula = formula;
        this.publishDate = publishDate;
        this.contact = contact;
        this.phone = phone;
        this.deadTime = deadTime;
        this.isMoney = isMoney;
        this.recLevel = recLevel;
        this.hotLevel = hotLevel;
        this.fileUrl = fileUrl;
        this.leaderDepartment = leaderDepartment;
        this.applicableArea = applicableArea;
        this.policyClass = policyClass;
        this.policyType = policyType;
        this.declarationStatus = declarationStatus;
        this.releaseTime = releaseTime;
    }

    public PolicyAPIDto(Integer id, String name, String content, String formula, Date publishDate, String contact, String phone, String deadTime, String isMoney, Integer recLevel, String leaderDepartment, String applicableArea, String policyClass, String policyType, String declarationStatus, String releaseTime) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.formula = formula;
        this.publishDate = publishDate;
        this.contact = contact;
        this.phone = phone;
        this.deadTime = deadTime;
        this.isMoney = isMoney;
        this.recLevel = recLevel;
        this.leaderDepartment = leaderDepartment;
        this.applicableArea = applicableArea;
        this.policyClass = policyClass;
        this.policyType = policyType;
        this.declarationStatus = declarationStatus;
        this.releaseTime = releaseTime;
    }

    public PolicyAPIDto() {
    }

    public PolicyAPIDto(Integer id, String name, String content, String formula, Date publishDate, String contact, String phone, String deadTime, String isMoney, Integer recLevel, Integer hotLevel) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.formula = formula;
        this.publishDate = publishDate;
        this.contact = contact;
        this.phone = phone;
        this.deadTime = deadTime;
        this.isMoney = isMoney;
        this.recLevel = recLevel;
        this.hotLevel = hotLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(String deadTime) {
        this.deadTime = deadTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsMoney() {
        return isMoney;
    }

    public void setIsMoney(String isMoney) {
        this.isMoney = isMoney;
    }

    public Integer getRecLevel() {
        return recLevel;
    }

    public void setRecLevel(Integer recLevel) {
        this.recLevel = recLevel;
    }

    public Integer getHotLevel() {
        return hotLevel;
    }

    public void setHotLevel(Integer hotLevel) {
        this.hotLevel = hotLevel;
    }

    public String getPolicyObject() {
        return policyObject;
    }

    public void setPolicyObject(String policyObject) {
        this.policyObject = policyObject;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getShengyuTime() {
        return shengyuTime;
    }

    public void setShengyuTime(String shengyuTime) {
        this.shengyuTime = shengyuTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(Integer docNumber) {
        this.docNumber = docNumber;
    }

    public String getDocDetail() {
        return docDetail;
    }

    public void setDocDetail(String docDetail) {
        this.docDetail = docDetail;
    }

    public String getPolicyDocDetail() {
        return policyDocDetail;
    }

    public void setPolicyDocDetail(String policyDocDetail) {
        this.policyDocDetail = policyDocDetail;
    }

    public String getPolicyCondition() {
        return policyCondition;
    }

    public void setPolicyCondition(String policyCondition) {
        this.policyCondition = policyCondition;
    }

    public String getLeaderDepartment() {
        return leaderDepartment;
    }

    public void setLeaderDepartment(String leaderDepartment) {
        this.leaderDepartment = leaderDepartment;
    }

    public String getApplicableArea() {
        return applicableArea;
    }

    public void setApplicableArea(String applicableArea) {
        this.applicableArea = applicableArea;
    }

    public String getPolicyClass() {
        return policyClass;
    }

    public void setPolicyClass(String policyClass) {
        this.policyClass = policyClass;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getDeclarationStatus() {
        return declarationStatus;
    }

    public void setDeclarationStatus(String declarationStatus) {
        this.declarationStatus = declarationStatus;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
}