package geex.boot.dao.xuser;

import geex.boot.dao.BaseDao;
import geex.boot.domain.User;

import java.util.List;

/**
 * //TODO description.
 *
 * @author JuChen
 * @date 2019/3/4
 */
public interface UserMapper extends BaseDao<User> {
    List<User> searchAllUser();
}
