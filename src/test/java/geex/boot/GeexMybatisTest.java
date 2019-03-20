package geex.boot;

import geex.GeexMybatisApplication;
import geex.boot.domain.AppCommonModel;
import geex.boot.domain.User;
import geex.boot.service.GravityService;
import geex.boot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GeexMybatisApplication.class)
public class GeexMybatisTest {
    @Autowired
    private DataSource xuserDataSource;
    @Autowired
    private DataSource xconfigDataSource;
    @Autowired
    private SqlSessionFactory xuserSqlSessionFactory;
    @Autowired
    private SqlSessionFactory xconfigSqlSessionFactory;

    @Autowired
    private UserService userService;
    @Autowired
    private GravityService gravityService;

    @Test
    public void testConditional() {
        assertNotNull(xuserDataSource);
        assertNotNull(xconfigDataSource);
        assertNotEquals(xuserDataSource, xconfigDataSource);

        assertNotNull(xuserSqlSessionFactory);
        assertNotNull(xconfigSqlSessionFactory);
        assertNotEquals(xuserSqlSessionFactory, xconfigSqlSessionFactory);
    }

    @Test
    public void testUserList() {
        List<User> users = userService.testMapper();
        log.info("users: {}", users);
    }

    @Test
    public void testGetAppCommon() {
        AppCommonModel appCommon = gravityService.getAppCommon(1);
        log.info("appCommon: {}", appCommon);
    }

    @Test
    public void testSearchUser() {
        List<User> users = userService.findUsers();
        log.info("users: {}", users);
    }
}