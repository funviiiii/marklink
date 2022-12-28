package com.laquanquan.personnel_salary.mapper;

import com.laquanquan.personnel_salary.vo.UserDataVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lqq
 */
@SpringBootTest
public class SalaryMapperTest {

    @Resource
    private SalaryMapper salaryMapper;

    @Test
    void selectOneByUid() {
        System.out.println(salaryMapper.selectOneByUid("uid_fRpnY3Oeab"));
    }

    @Test
    void selectListByDepartment(@Autowired UserMapper userMapper) {
        List<UserDataVO> userList = userMapper.selectByDepartment("did_default");
        System.out.println(salaryMapper.selectListByDepartment(userList));
    }
}
