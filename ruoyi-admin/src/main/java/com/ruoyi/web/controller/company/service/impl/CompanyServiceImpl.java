package com.ruoyi.web.controller.company.service.impl;

import com.ruoyi.web.controller.company.domain.Company;
import com.ruoyi.web.controller.company.mapper.CompanyMapper;
import com.ruoyi.web.controller.company.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService
{
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Company> selectCompanyListById(Integer id) {
        return companyMapper.selectCompanyListById(id);
    }
    /**
     * 查询指标列表
     *
     * @param company 指标
     * @return 指标
     */
    @Override
    public List<Company> selectCompanyList(Company company)
    {
        return companyMapper.selectCompanyList(company);
    }

    /**
     * 新增指标
     *
     * @param company 指标
     * @return 结果
     */
    @Override
    public int insertCompany(Company company)
    {
        return companyMapper.insertCompany(company);
    }

    /**
     * 修改指标
     *
     * @param company 指标
     * @return 结果
     */
    @Override
    public int updateCompany(Company company)
    {
        return companyMapper.updateCompany(company);
    }

    /**
     * 批量删除指标
     *
     * @param ids 需要删除的指标主键
     * @return 结果
     */
    @Override
    public int deleteCompanyByIds(Integer[] ids)
    {
        return companyMapper.deleteCompanyByIds(ids);
    }

    /**
     * 删除指标信息
     *
     * @param id 指标主键
     * @return 结果
     */
    @Override
    public int deleteCompanyById(Integer id)
    {
        return companyMapper.deleteCompanyById(id);
    }


    @Override
    public Company selectCompanyByOpenid(String openid) {
        return companyMapper.selectCompanyByOpenid(openid);
    }

    @Override
    public Company selectCompanyByName(String companyName) {
        return companyMapper.selectCompanyByName(companyName);
    }

}
