package geex.boot.annotation;

import java.lang.annotation.*;

/**
 * 启用GeexMybatis
 *
 * @author JuChen
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableGeexMybatis {
    /**
     * 对应mybatis中的typeAliasesPackage
     */
    String[] typeAliases() default "";

    String[] mapperInterface() default "";
}
