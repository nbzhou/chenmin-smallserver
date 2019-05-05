package com.chemin.smallserver.starter.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 需要做数据权限的接口
 * Create By zhengjing on 2018/5/7 15:18
 */
@Documented
@Inherited
@Retention(RUNTIME)
@Target({TYPE})
public @interface DataAuthority {

    /**
     *
     * @return
     */
    String value() default "scenicId";
}
