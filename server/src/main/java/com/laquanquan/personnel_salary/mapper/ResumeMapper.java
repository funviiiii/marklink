package com.laquanquan.personnel_salary.mapper;

import com.laquanquan.personnel_salary.domain.Resume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author lqq
*/
@Mapper
public interface ResumeMapper {

    /**
     * 根据简历编号查询简历
     *
     * @param id 简历编号
     * @return 返回简历的主体
     */
    @Select("SELECT `content` FROM `t_resume` WHERE `rid`=#{id} AND `is_deleted`=0")
    Resume selectById(String id);
}




