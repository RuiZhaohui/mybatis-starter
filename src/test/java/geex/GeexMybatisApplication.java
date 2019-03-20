package geex;

import geex.boot.annotation.EnableGeexMybatis;
import geex.boot.database.annotation.EnableGeexDataSource;
import geex.boot.database.constant.GeexDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * //TODO description.
 *
 * @author JuChen
 * @date 2019/2/21
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableGeexMybatis(mapperInterface = {
        "gravity.geex.boot.dao.gravity.GravityDao"
})
@EnableGeexDataSource({GeexDataSource.XUSER, GeexDataSource.XCONFIG, GeexDataSource.GRAVITY})
public class GeexMybatisApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GeexMybatisApplication.class, args);
    }
}
