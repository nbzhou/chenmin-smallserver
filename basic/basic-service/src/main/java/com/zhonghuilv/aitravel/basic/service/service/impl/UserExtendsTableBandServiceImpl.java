package com.zhonghuilv.aitravel.basic.service.service.impl;

import com.zhonghuilv.aitravel.basic.intf.pojo.*;
import com.zhonghuilv.aitravel.basic.intf.pojo.vo.UserVO;
import com.zhonghuilv.aitravel.basic.service.mapper.*;
import com.zhonghuilv.aitravel.basic.service.service.OldAccessAdapterService;
import com.zhonghuilv.aitravel.basic.service.service.OldRoleAdapterService;
import com.zhonghuilv.aitravel.basic.service.service.UserExtendsService;
import com.zhonghuilv.aitravel.basic.service.service.UserExtendsTableBandService;
import com.zhonghuilv.aitravel.common.service.util.ExampleBuiler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserExtendsTableBandServiceImpl implements UserExtendsTableBandService {

    @Autowired
    OldRoleAdapterService oldRoleAdapterService;

    @Autowired
    OldAccessAdapterService oldAccessAdapterService;

    @Autowired
    UserExtendsService userExtendsService;

    @Autowired
    UserExtendsMapper userExtendsMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    SysAccessMapper sysAccessMapper;



    @Override
    public UserVO saveExtends(User user) {
        /**
         * @Auther:Alienware_PC
         * @Description:[user]
         * @Data:16:33 2018/4/7
         * @Return:com.zhonghuilv.aitravel.basic.intf.pojo.vo.UserVO
         */

        UserExtends userExtends = new UserExtends();
        UserRole userRole = new UserRole();
        userRole.setId(user.getId());
        userRole.setRoleId(Optional.ofNullable(user.getRoleId()).orElse(null));
        userExtends.setId(user.getId());
        userExtends.setScenicId(Optional.ofNullable(user.getScenicId()).orElse(null));
        oldRoleAdapterService.save(userRole);
        userExtendsMapper.insert(userExtends);
        return selectExtends(user);
    }

    @Override
    public UserVO updateExtends(User user) {
        UserRole userRole = new UserRole();
        userRole.setId(user.getId());
        userRole.setRoleId(user.getRoleId());
        UserExtends userExtends = new UserExtends();
        userExtends.setId(user.getId());
        userExtends.setScenicId(user.getScenicId());
        oldRoleAdapterService.update(userRole);
        Optional optional2 =Optional.ofNullable(user.getScenicId());
        optional2.ifPresent((o)->{userExtendsService.update(userExtends);});
        return selectExtends(user);
    }

    @Override
    public UserVO selectAllExtends(User user) {
        UserVO userVO = new UserVO();
        userVO.setSysAccessList(new ArrayList<>());
        userVO.setRoleList(new ArrayList<>());
        //userVO.setRoleCode("");
        BeanUtils.copyProperties(user,userVO);
        userVO.setScenicId(Optional.ofNullable(userExtendsMapper.selectByPrimaryKey(user.getId())).orElse(new UserExtends()).getScenicId());
        userVO.setRoleId(oldRoleAdapterService.select(user));
        String[] roleListString = Optional.ofNullable(userVO.getRoleId()).orElse("").split(",");
        for (String temp : roleListString) {
            if (temp != "") {
                userVO.getRoleList().add(Optional.ofNullable(roleMapper.selectByPrimaryKey(Long.valueOf(temp))).orElse(new Role()));
                String[] accessListString = Optional.ofNullable(oldAccessAdapterService.select(Long.valueOf(temp))).map(RoleAccess::getAccessId).orElse("").split(",");
                for (String accessTemp : accessListString) {
                    if (accessTemp != "")
                        userVO.getSysAccessList().add(Optional.ofNullable(sysAccessMapper.selectByPrimaryKey(Long.valueOf(accessTemp))).orElse(new SysAccess()));
                }
            }
        }
        return userVO;
    }

    @Override
    public UserVO selectExtends(User user) {

        UserVO userVO = new UserVO();
        userVO.setSysAccessList(new ArrayList<>());
        userVO.setRoleList(new ArrayList<>());
        BeanUtils.copyProperties(user, userVO);
        userVO.setScenicId(Optional.ofNullable(userExtendsMapper.selectByPrimaryKey(user.getId())).orElse(new UserExtends()).getScenicId());
        userVO.setRoleId(oldRoleAdapterService.select(user));
        String[] roleListString = Optional.ofNullable(userVO.getRoleId()).orElse("").split(",");
        for (String temp : roleListString) {
            if (temp != "") {
                userVO.getRoleList().add(Optional.ofNullable(roleMapper.selectByPrimaryKey(Long.valueOf(temp))).orElse(new Role()));
            }
        }
        return userVO;
    }
}
