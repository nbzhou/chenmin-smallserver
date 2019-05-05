package com.chemin.smallserver.starter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.chemin.smallserver.common.constants.ApplicationConfig;
import com.chemin.smallserver.common.excption.ServiceLogicException;
import com.chemin.smallserver.common.service.mapper.CommonMapper;
import com.chemin.smallserver.starter.annotation.DataAuthority;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 在新增修改时 自动添加修改 新增信息
 * Create By zhengjing on 2018/1/30 13:29
 */
@Aspect
@Slf4j
@Component
public class DataAuthorityAspect {

    enum ResultType {
        LIST,
        COUNT,
        SIMPLE
    }

    @Autowired
    ObjectMapper objectMapper;

    @Pointcut("execution(* com.chemin.smallserver..service.mapper.*.select*(..))")
    public void query() {
    }

    private static final String METHOD_NAME_SELECT = "select";

    private static final String METHOD_NAME_SELECT_COUNT = "selectCount";
    private static final String METHOD_NAME_EXAMPLE = "selectByExample";
    private static final String METHOD_NAME_EXAMPLE_COUNT = "selectCountByExample";

    private Map<String, ResultType> methodMap = new ConcurrentHashMap<>();

    {
        methodMap.put(METHOD_NAME_SELECT_COUNT, ResultType.COUNT);
        methodMap.put(METHOD_NAME_EXAMPLE_COUNT, ResultType.COUNT);
        methodMap.put(METHOD_NAME_SELECT, ResultType.LIST);
        methodMap.put(METHOD_NAME_EXAMPLE, ResultType.LIST);
        methodMap.put("selectByExampleAndRowBounds", ResultType.LIST);
        methodMap.put("selectByRowBounds", ResultType.LIST);
        methodMap.put("selectOne", ResultType.SIMPLE);
    }

    /**
     * 没有权限的list
     */
    private static final List<Long> NO_AUTHORY = Arrays.asList(0L);

    @Around("query()")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        String methodName = joinPoint.getSignature().getName();

        //如果不在拦截列表
        if (!methodMap.containsKey(methodName)) {
            return joinPoint.proceed();
        }

        Object target = joinPoint.getTarget();

        if (ArrayUtils.isNotEmpty(args)) {

            Object arg = args[0];

            //queryPo class
            Class<?> clazz;
            if (arg instanceof Example) {
                clazz = ((Example) arg).getEntityClass();
            } else {
                clazz = arg.getClass();

            }
            DataAuthority annotation = clazz.getAnnotation(DataAuthority.class);

            if (annotation != null) {
                List<Long> dataAuthorities = getDataAuthorities();

                //如果 有所有权限
                if (dataAuthorities.contains(-1L)) {
                    return joinPoint.proceed();
                }
                //如果没有权限
                if (dataAuthorities.contains(0L)) {
                    return getEmptyResult(methodName);
                }

                //单个景区的权限
                if (dataAuthorities.size() == 1) {
                    if (arg instanceof Example) {
                        ((Example) arg).and()
                                .andEqualTo(annotation.value(), dataAuthorities.get(0));
                    } else {
                        Method setterMethodName = getSetterMethodName(annotation.value(), clazz);
                        setterMethodName.invoke(arg, dataAuthorities.get(0));
                    }
                    return joinPoint.proceed();
                }

                //没有传这个字段 以前的方法 不做处理
                if(CollectionUtils.isEmpty(dataAuthorities)){
                    return joinPoint.proceed();
                }

                CommonMapper cast = null;
                if (target instanceof CommonMapper) {
                    cast = (CommonMapper) target;
                } else {
                    throw new ServiceLogicException(
                            String.format("target %s not CommonMapper",
                                    target.getClass().getName()));
                }
                //TODO policy pattern？
                if (METHOD_NAME_SELECT.equals(methodName) || METHOD_NAME_SELECT_COUNT.equals(methodName)) {
                    Example example = buildSelectExample(arg, annotation.value(), dataAuthorities, clazz);

                    if (METHOD_NAME_SELECT.equals(methodName))
                        return cast.selectByExample(example);
                    if (METHOD_NAME_SELECT_COUNT.equals(methodName))
                        return cast.selectCountByExample(example);
                }

                if (METHOD_NAME_EXAMPLE.equals(methodName) || METHOD_NAME_EXAMPLE_COUNT.equals(methodName)) {
                    if (arg instanceof Example) {
                        Example example = (Example) arg;
                        example.and().andIn(annotation.value(), dataAuthorities);
                        return joinPoint.proceed();
                    } else {
                        throw new ServiceLogicException("Example 查询但是参数不是Example 实例");
                    }
                }
            }

        }
        return joinPoint.proceed();
    }

    /**
     * 构造select 方法的example
     *
     * @param arg             原参数（queryPo）
     * @param field           数据权限字段（景区id）
     * @param dataAuthorities 数据权限
     * @param clazz           参数class
     * @return
     */
    private Example buildSelectExample(Object arg, String field, List<Long> dataAuthorities, Class<?> clazz) {

        Map<String, Object> map = objectMapper.convertValue(arg, Map.class);
        Example example = new Example(clazz);
        if (!map.isEmpty()) {
            Example.Criteria criteria = example.createCriteria();

            map.forEach((property, value) -> {
                if (Objects.isNull(value))
                    return;
                criteria.andEqualTo(property, value);
            });
            if (dataAuthorities.size() == 1) {
                criteria.andEqualTo(field, dataAuthorities.get(0));
            } else if (dataAuthorities.size() > 1) {
                criteria.andIn(field, dataAuthorities);
            }

        }

        //TODO read OrderBy
        if (map.containsKey("id")) {
            example.orderBy("id").desc();
        }
        return example;
    }

    /**
     * 根据属性名称获取对应的setter方法名称
     *
     * @param property
     * @return
     */
    public static Method getSetterMethodName(String property, Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }
        sb.insert(0, "set");

        try {
            return clazz.getMethod(sb.toString(), Long.class);
        } catch (NoSuchMethodException e) {
            throw new ServiceLogicException("权限拦截出错：没有获取到" + property + "的setter");
        }
    }

    private Object getEmptyResult(String methodName) {

        switch (methodMap.get(methodName)) {
            case LIST:
                return Collections.EMPTY_LIST;
            case COUNT:
                return 0;
            case SIMPLE:
                return null;
        }
        return null;
    }

    private List<Long> getDataAuthorities() {

        String headerValue = getHeaderValue(ApplicationConfig.FEIGN_HTTP_HEADER_DATA_AUTHORITIES);
        if (StringUtils.isNotBlank(headerValue)) {

            return Stream.of(headerValue.split(",")).map(Long::valueOf).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public String getHeaderValue(String headerName) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest().getHeader(headerName);
        }
        return null;
    }


}
