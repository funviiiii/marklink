package com.laquanquan.personnel_salary.mapper;

import com.laquanquan.personnel_salary.domain.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lqq
 */
@Mapper
public interface AnnouncementMapper {

    /**
     * 保存一个公告对象到表中
     *
     * @param announcement 公告对象
     * @return 影响的行数
     */
    int save(@Param("announcement") Announcement announcement);

    List<Announcement> selectAll();
}




