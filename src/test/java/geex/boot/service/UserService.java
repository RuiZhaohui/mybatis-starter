package geex.boot.service;

import geex.boot.domain.User;
import geex.boot.dao.xuser.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * //TODO description.
 *
 * @author JuChen
 * @date 2019/3/4
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> testMapper() {
        return userMapper.selectAll();
    }

    public List<User> findUsers(){
        return userMapper.searchAllUser();
    }
}
