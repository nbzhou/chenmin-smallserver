package com.zhonghuilv.aitravel;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Create By zhengjing on 2018/3/15 10:14
 */
public class TableCreate {

    public void table() {

        Class<HashMap> hashMapClass = HashMap.class;

        Table tableAnnotation = hashMapClass.getAnnotation(Table.class);

        if (tableAnnotation == null) {
            return;
        }

        Field[] fields = hashMapClass.getFields();
        StringBuffer sv = new StringBuffer("create table ")
                .append(tableAnnotation.name())
                .append("(");
        Stream.of(fields).forEach(
                field -> {
                    field.getAnnotation(ApiModelProperty.class);
                }
        );


        sv.append(");");

    }

}
