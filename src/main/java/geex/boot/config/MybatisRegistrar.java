package geex.boot.config;

import com.google.common.collect.Lists;
import geex.boot.annotation.EnableGeexMybatis;
import geex.boot.constant.GeexMybatisConstants;
import geex.boot.database.DynamicDataSource;
import geex.boot.database.annotation.EnableGeexDataSource;
import geex.boot.database.constant.DataSourceConstant;
import geex.boot.database.constant.GeexDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * integration tk.mybatis
 *
 * @author JuChen
 */
@Slf4j
public class MybatisRegistrar implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware, Ordered {
    private GenericApplicationContext applicationContext;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        Reflections reflections = new Reflections();
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(EnableGeexDataSource.class);
        if (null != classes && classes.size() > 0) {
            Class<?> aClass = classes.iterator().next();
            EnableGeexDataSource annotation = aClass.getAnnotation(EnableGeexDataSource.class);
            GeexDataSource[] values = annotation.value();

            EnableGeexMybatis mybatisAnnotation = aClass.getAnnotation(EnableGeexMybatis.class);
            String[] typeAliases = mybatisAnnotation.typeAliases();
            String[] mapperInterface = mybatisAnnotation.mapperInterface();

            PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
            Resource[] mapperResources = new Resource[0];

            int index = 0;
            for (GeexDataSource value : values) {
                String prefix = value.getDbName();

                String dataSourceName = prefix + DataSourceConstant.DATA_SOURCE;
                String sessionFactoryName = prefix + GeexMybatisConstants.SQL_SESSION_FACTORY_NAME.getValue();
                String mapperScanName = prefix + GeexMybatisConstants.MAPPER_SCAN_NAME.getValue();
                String txManagerName = prefix + GeexMybatisConstants.TX_MANAGER_NAME.getValue();

                log.debug("index: {} --- dataSourceName: {} --- sessionFactoryName: {} --- txManagerName: {}",
                        index, dataSourceName, sessionFactoryName, txManagerName);

                try {
                    String locationPattern = "classpath*:" + prefix + "Mapper/*.xml";
                    mapperResources = pathResolver.getResources(locationPattern);
                    log.info("we catch mapper location: {}", locationPattern);
                } catch (IOException e) {
                    log.error("get mapper resources error: {}", e);
                }

                // get database
                DynamicDataSource dataSource = (DynamicDataSource) applicationContext.getBean(dataSourceName);

                // register SqlSessionFactoryBean
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(SqlSessionFactoryBean.class);
                beanDefinitionBuilder.addPropertyValue(GeexMybatisConstants.DATASOURCE_KEY.getValue(), dataSource);
                beanDefinitionBuilder.addPropertyValue(GeexMybatisConstants.MAPPER_LOCATIONS_KEY.getValue(), mapperResources);
                if (typeAliases.length > 0) {
                    for (String alias : typeAliases) {
                        String[] split = alias.split("\\.");
                        if (split.length > 0) {
                            String flag = split[0];
                            if (flag.equals(prefix)) {
                                String aliasRst = alias.substring(flag.length() + 1);
                                beanDefinitionBuilder.addPropertyValue("typeAliasesPackage", aliasRst);
                                log.debug("match type alias: {}", aliasRst);
                            }
                        }
                    }
                }
                registry.registerBeanDefinition(sessionFactoryName, beanDefinitionBuilder.getBeanDefinition());

                List<String> prefixes = Lists.newArrayList();
                if (mapperInterface.length > 0) {
                    for (String aInterface : mapperInterface) {
                        String[] split = aInterface.split("\\.");
                        if (split.length > 0) {
                            String flag = split[0];
                            prefixes.add(flag);
                            if (flag.equals(prefix)) {
                                beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(MapperFactoryBean.class);
                                String mi = aInterface.substring(flag.length() + 1);
                                log.debug("match mapper interface: {}", mi);
                                SqlSessionFactory sessionFactory = (SqlSessionFactory) applicationContext.getBean(sessionFactoryName);
                                log.info("we have registered SqlSessionFactory {} for DataSource {}", sessionFactoryName, prefix);
                                beanDefinitionBuilder.addPropertyValue(GeexMybatisConstants.MAPPER_INTERFACE.getValue(), mi);
                                beanDefinitionBuilder.addPropertyValue(GeexMybatisConstants.SQL_SESSION_FACTORY_ATTR.getValue(), sessionFactory);
                                registry.registerBeanDefinition(prefix + "Dao", beanDefinitionBuilder.getBeanDefinition());
                            }
                        }
                    }
                } else {
                    registerMapperConfigurer(registry, prefix, sessionFactoryName, mapperScanName);
                }

                if (mapperInterface.length > 0 && !prefixes.contains(prefix)) {
                    registerMapperConfigurer(registry, prefix, sessionFactoryName, mapperScanName);
                }

                // register DataSourceTransactionManager
                beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(DataSourceTransactionManager.class);
                beanDefinitionBuilder.addPropertyValue(GeexMybatisConstants.DATASOURCE_KEY.getValue(), dataSource);
                if (index == 0) {
                    beanDefinitionBuilder.getBeanDefinition().setPrimary(true);
                }
                registry.registerBeanDefinition(txManagerName, beanDefinitionBuilder.getBeanDefinition());
                index++;
            }
        }

    }

    private void registerMapperConfigurer(BeanDefinitionRegistry registry, String prefix, String sessionFactoryName, String mapperScanName) {
        BeanDefinitionBuilder beanDefinitionBuilder;
        beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(MapperScannerConfigurer.class);
        beanDefinitionBuilder.addPropertyValue(GeexMybatisConstants.SQL_SESSION_FACTORY_BEAN_NAME_KEY.getValue(), sessionFactoryName);
        beanDefinitionBuilder.addPropertyValue(GeexMybatisConstants.BASE_PACKAGE_KEY.getValue(), "geex.**.dao." + prefix);
        beanDefinitionBuilder.addPropertyValue(GeexMybatisConstants.MARKER_INTERFACE.getValue(), "geex.boot.dao.BaseDao");
        beanDefinitionBuilder.addPropertyValue(GeexMybatisConstants.PROPERTIES.getValue(), "mappers=geex.boot.dao.BaseDao");
        registry.registerBeanDefinition(mapperScanName, beanDefinitionBuilder.getBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (GenericApplicationContext) applicationContext;
    }

    @Override
    public int getOrder() {
        return 2147483640;
    }
}
