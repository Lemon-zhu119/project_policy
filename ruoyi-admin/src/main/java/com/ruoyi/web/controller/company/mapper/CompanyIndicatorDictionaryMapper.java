package com.ruoyi.web.controller.company.mapper;


import com.ruoyi.web.controller.company.domain.CompanyIndicatorDictionary;
import com.ruoyi.web.controller.company.domain.dto.CompanyIndicator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2024-10-29
 */
@Mapper
public interface CompanyIndicatorDictionaryMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public CompanyIndicatorDictionary selectCompanyIndicatorDictionaryById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param companyIndicatorDictionary 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CompanyIndicatorDictionary> selectCompanyIndicatorDictionaryList(CompanyIndicatorDictionary companyIndicatorDictionary);

    /**
     * 新增【请填写功能名称】
     *
     * @param companyIndicatorDictionary 【请填写功能名称】
     * @return 结果
     */
    public int insertCompanyIndicatorDictionary(CompanyIndicatorDictionary companyIndicatorDictionary);

    /**
     * 修改【请填写功能名称】
     *
     * @param companyIndicatorDictionary 【请填写功能名称】
     * @return 结果
     */
    public int updateCompanyIndicatorDictionary(CompanyIndicatorDictionary companyIndicatorDictionary);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCompanyIndicatorDictionaryById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompanyIndicatorDictionaryByIds(Integer[] ids);

    public CompanyIndicatorDictionary selectCompanyIndicatorDictionaryByName(String name);

    public List<CompanyIndicatorDictionary> selectCompanyIndicatorDictionaryListDistinct(Integer id);

    public List<CompanyIndicatorDictionary> selectCompanyIndicatorDictionaryByCompanyId(Integer companyId);

    Object selectCompanyIndicatorDictionaryByKey(@Param("key") String key);
}