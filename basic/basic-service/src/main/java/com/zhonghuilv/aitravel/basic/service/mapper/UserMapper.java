package com.zhonghuilv.aitravel.basic.service.mapper;

import com.zhonghuilv.aitravel.basic.intf.pojo.User;
import com.zhonghuilv.aitravel.common.service.mapper.CommonMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by lenovo07 on 2016/12/14.
 */
@Mapper
public interface UserMapper extends CommonMapper<User> {
}