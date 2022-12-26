package com.laquanquan.personnel_salary.mapper;

import com.laquanquan.personnel_salary.domain.Resume;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author lqq
 */
@Mapper
public interface ResumeMapper {

    /**
     * 根据简历编号查询简历
     *
     * @param rid 简历编号
     * @return 返回简历的主体
     */
    @Select("SELECT `content` FROM `t_resume` WHERE `rid`=#{rid} AND `is_deleted`=0 LIMIT 1")
    Resume selectById(String rid);

    /**
     * 创建一条新的数据
     *
     * @param resume 新建的简历条目
     * @return 更新影响的数据库条数
     */
    int createOne(@Param("resume") Resume resume);

    /**
     * 更新一条数据
     *
     * @param resume 要更新的简历
     * @return 更新影响的数据库条数
     */
    int updateOne(@Param("resume") Resume resume);
}




