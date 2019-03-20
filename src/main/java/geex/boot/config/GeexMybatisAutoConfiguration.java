package geex.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * //TODO description.
 *
 * @author JuChen
 */
@Configuration
@Import({
        MybatisRegistrar.class
})
public class GeexMybatisAutoConfiguration {
}
