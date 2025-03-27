package com.ruoyi.web.controller.company.mapper;

import com.ruoyi.web.controller.company.domain.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyMapper
{

    public List<Company> selectCompanyListById(Integer id);


    public List<Company> selectCompanyList(Company company);


    public int insertCompany(Company company);


    public int updateCompany(Company company);


    public int deleteCompanyById(Integer id);


    public int deleteCompanyByIds(Integer[] ids);

    public Company selectCompanyByOpenid(@Param("openid") String openid);

    public Company selectCompanyByName(@Param("companyName") String companyName);


}