package geex.boot;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableApolloConfig({"application","architecture-group.database"})
public class ApolloAppConfig {
    private final Logger logger = Logger.getLogger(ApolloAppConfig.class);

    public ApolloAppConfig() {
        // 修改日志级别
        Config logConfig = ConfigService.getAppConfig();
        logConfig.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                logger.info("changes for namespace " + changeEvent.getNamespace());
                for (String key : changeEvent.changedKeys()) {
                    if ("geex.log.level".equals(key)) {
                        ConfigChange change = changeEvent.getChange(key);
                        logger.info("key: " + key + ", oldVal: " + change.getOldValue() + ", newVal: " + change.getNewValue());
                        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
                        loggerContext.getLogger("root").setLevel(Level.valueOf(change.getNewValue()));
                    }
                }
            }
        });
    }
}