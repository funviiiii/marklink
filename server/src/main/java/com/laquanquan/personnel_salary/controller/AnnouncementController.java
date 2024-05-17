package com.laquanquan.personnel_salary.controller;

import com.laquanquan.personnel_salary.domain.Announcement;
import com.laquanquan.personnel_salary.service.AnnouncementService;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import com.laquanquan.personnel_salary.vo.AnnouncementVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLDataException;
import java.util.List;

/**
 * @author lqq
 */
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    @Resource
    AnnouncementService announcementService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public WebResponseBody<Announcement> post(@RequestBody AnnouncementVO announcement) throws SQLDataException {
        return announcementService.create(announcement);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public WebResponseBody<List<AnnouncementVO>> getAll() throws SQLDataException {
        return announcementService.getList();
    }
}
