package geex.boot.service;

import geex.boot.dao.gravity.GravityDao;
import geex.boot.domain.AppCommonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * //TODO description.
 *
 * @author JuChen
 * @date 2019/3/11
 */
@Service
public class GravityService {
    @Autowired
    private GravityDao gravityDao;

    public AppCommonModel getAppCommon(Integer appId) {
        return gravityDao.getAppCommon(appId);
    }
}
