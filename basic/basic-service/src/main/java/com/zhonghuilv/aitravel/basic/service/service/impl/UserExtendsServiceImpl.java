package com.zhonghuilv.aitravel.basic.service.service.impl;

import com.zhonghuilv.aitravel.basic.intf.pojo.UserExtends;
import com.zhonghuilv.aitravel.basic.service.mapper.UserExtendsMapper;
import com.zhonghuilv.aitravel.basic.service.service.UserExtendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserExtendsServiceImpl implements UserExtendsService{

    @Autowired
    private UserExtendsMapper userExtendsMapper;

    @Override
    public Boolean update(UserExtends userExtends) {
        Optional.ofNullable(userExtendsMapper.selectByPrimaryKey(userExtends.getId())).map(u->userExtendsMapper.updateByPrimaryKey(userExtends)).orElseGet(()->userExtendsMapper.insert(userExtends));
        return true;
    }
}
