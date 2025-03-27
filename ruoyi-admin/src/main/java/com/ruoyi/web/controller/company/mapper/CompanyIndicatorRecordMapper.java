package com.ruoyi.web.controller.company.mapper;

import com.ruoyi.web.controller.api.domain.dto.CompanyIndicatorRecordMap;
import com.ruoyi.web.controller.company.domain.CompanyIndicatorRecord;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CompanyIndicatorRecordMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public CompanyIndicatorRecord selectCompanyIndicatorRecordById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param companyIndicatorRecord 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CompanyIndicatorRecord> selectCompanyIndicatorRecordList(CompanyIndicatorRecord companyIndicatorRecord);

    /**
     * 新增【请填写功能名称】
     *
     * @param companyIndicatorRecord 【请填写功能名称】
     * @return 结果
     */
    public int insertCompanyIndicatorRecord(CompanyIndicatorRecord companyIndicatorRecord);

    /**
     * 修改【请填写功能名称】
     *
     * @param companyIndicatorRecord 【请填写功能名称】
     * @return 结果
     */
    public int updateCompanyIndicatorRecord(CompanyIndicatorRecord companyIndicatorRecord);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCompanyIndicatorRecordById(Integer id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompanyIndicatorRecordByIds(Integer[] ids);

    public List<CompanyIndicatorRecord> selectCompanyIndicatorRecordByDicId(Integer id);

    List<CompanyIndicatorRecordMap> selectCompanyIndicatorRecordListByCompanyId(Integer id);

    int insertManageLevel(@Param("companyId") Integer companyId, @Param("dictionaryId") Integer dictionaryId, @Param("threshold") String threshold);

    int updateManageLevel(@Param("id") Integer id , @Param("companyId") Integer companyId, @Param("dictionaryId") Integer dictionaryId, @Param("threshold") String threshold);

    List<CompanyIndicatorRecordMap> selectCompanyIndicatorRecordListByCompanyIdAndYear(@Param("id") Integer id, @Param("year") Integer year);

    List<CompanyIndicatorRecord> selectCompanyIndicatorRecordListByCompanyIdAndYearV1(@Param("id") Integer id,@Param("year") Integer year);
}