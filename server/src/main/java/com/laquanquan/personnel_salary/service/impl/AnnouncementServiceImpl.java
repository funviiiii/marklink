package com.laquanquan.personnel_salary.service.impl;

import com.laquanquan.personnel_salary.domain.Account;
import com.laquanquan.personnel_salary.domain.Announcement;
import com.laquanquan.personnel_salary.mapper.AccountMapper;
import com.laquanquan.personnel_salary.mapper.AnnouncementMapper;
import com.laquanquan.personnel_salary.service.AnnouncementService;
import com.laquanquan.personnel_salary.utils.TokenBuilder;
import com.laquanquan.personnel_salary.utils.WebResponseBody;
import com.laquanquan.personnel_salary.vo.AnnouncementVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lqq
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Resource
    AnnouncementMapper announcementMapper;

    @Resource
    AccountMapper accountMapper;

    @Override
    public WebResponseBody<Announcement> create(AnnouncementVO announcementVO) throws SQLDataException {
        // 将token转换为实际用户
        Account account = new Account();
        account.setUid((String) TokenBuilder.parse(announcementVO.getCreatorToken()).get("uid"));
        announcementVO.setCreator(accountMapper.selectOne(account).getUsername());

        Announcement announcement = new Announcement(announcementVO);

        if (announcementMapper.save(announcement) != 1) {
            throw new SQLDataException("公告发布失败");
        }
        
        return new WebResponseBody<>("公告发布成功", announcement);
    }

    @Override
    public WebResponseBody<List<AnnouncementVO>> getList() throws SQLDataException {
        List<AnnouncementVO> announcementVOS = new ArrayList<>();
        List<Announcement> announcements = announcementMapper.selectAll();
        for (Announcement announcement : announcements) {
            announcementVOS.add(new AnnouncementVO(announcement));
        }
        return new WebResponseBody<>("公告发布成功", announcementVOS);
    }
}




