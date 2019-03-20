package geex.boot.constant;

/**
 * //TODO description.
 *
 * @author JuChen
 */
public enum GeexMybatisConstants {

    SQL_SESSION_FACTORY_NAME("SqlSessionFactory"),
    SQL_SESSION_FACTORY_ATTR("sqlSessionFactory"),
    MAPPER_SCAN_NAME("MapperScan"),
    TX_MANAGER_NAME("TransactionManager"),
    DATASOURCE_KEY("dataSource"),
    MAPPER_LOCATIONS_KEY("mapperLocations"),
    SQL_SESSION_FACTORY_BEAN_NAME_KEY("sqlSessionFactoryBeanName"),
    BASE_PACKAGE_KEY("basePackage"),
    MAPPER_INTERFACE("mapperInterface"),
    MARKER_INTERFACE("markerInterface"),
    PROPERTIES("properties");

    private String value;

    GeexMybatisConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }}
