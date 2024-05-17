package com.laquanquan.personnel_salary.service;

import com.laquanquan.personnel_salary.domain.Announcement;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import com.laquanquan.personnel_salary.vo.AnnouncementVO;

import java.sql.SQLDataException;
import java.util.List;

/**
 * @author lqq
 */
public interface AnnouncementService {

    WebResponseBody<Announcement> create(AnnouncementVO announcement) throws SQLDataException;

    WebResponseBody<List<AnnouncementVO>> getList() throws SQLDataException;
}
