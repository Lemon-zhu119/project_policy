package com.ruoyi.web.controller.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class PolicyClassAPI extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;

    /**
     * 政策名称
     private Integer id;

     /**
     * 政策名称
     */
    @Excel(name = "政策名称")
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
    private String policyCondition;
    private String categoryName;
    private String categoryContent;

    @Override
    public String toString() {
        return "PolicyClassAPI{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", formula='" + formula + '\'' +
                ", publishDate=" + publishDate +
                ", expireDate=" + expireDate +
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
                ", categoryName='" + categoryName + '\'' +
                ", categoryContent='" + categoryContent + '\'' +
                '}';
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

    public PolicyClassAPI(Integer id, String name, String content, String formula, Date publishDate, Date expireDate, String contact, String phone, String deadTime, String description, String isMoney, Integer recLevel, Integer hotLevel, String policyObject, String fileUrl, String shengyuTime, String money, String source, Integer docNumber, String docDetail, String policyDocDetail, String policyCondition, String categoryName, String categoryContent) {
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
        this.categoryName = categoryName;
        this.categoryContent = categoryContent;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryContent() {
        return categoryContent;
    }

    public void setCategoryContent(String categoryContent) {
        this.categoryContent = categoryContent;
    }

    public PolicyClassAPI() {
    }

    public PolicyClassAPI(Integer id, String name, String content, String formula, Date publishDate, Date expireDate, String contact, String phone, String deadTime, String description, String isMoney, Integer recLevel, Integer hotLevel, String policyObject, String fileUrl, String shengyuTime, String money, String source, String categoryName, String categoryContent) {
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
        this.categoryName = categoryName;
        this.categoryContent = categoryContent;
    }
}
