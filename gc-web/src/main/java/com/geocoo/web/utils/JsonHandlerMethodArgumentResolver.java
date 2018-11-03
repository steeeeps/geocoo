package com.geocoo.web.utils;


import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * desc:
 *
 * @author taopy
 * @create 2018-10-28 7:51 PM
 */
public class JsonHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        JsonParam jsonParam = parameter.getParameterAnnotation(JsonParam.class);
        String name = jsonParam.value();
        String value = webRequest.getParameter(name);
        if (!StringUtils.isEmpty(value)) {
            return JSON.parseObject(value, parameter.getGenericParameterType());
        } else {
            return null;
        }
    }
}
