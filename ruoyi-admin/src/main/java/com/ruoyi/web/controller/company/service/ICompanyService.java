package com.ruoyi.web.controller.company.service;


import com.ruoyi.web.controller.company.domain.Company;

import java.util.List;

public interface ICompanyService
{
    public List<Company> selectCompanyListById(Integer id);

    public List<Company> selectCompanyList(Company company);

    public int insertCompany(Company company);

    public int updateCompany(Company company);

    public int deleteCompanyByIds(Integer[] ids);

    public int deleteCompanyById(Integer id);

    public Company selectCompanyByOpenid(String openid);

    public Company selectCompanyByName(String companyName);
}