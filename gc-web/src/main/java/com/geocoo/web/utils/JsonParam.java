package com.geocoo.web.utils;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 描述:
 * 转换接口中 json 传参
 *
 * @author taopy
 * @create 2018-05-28 下午8:20
 */

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonParam {
    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";
}

