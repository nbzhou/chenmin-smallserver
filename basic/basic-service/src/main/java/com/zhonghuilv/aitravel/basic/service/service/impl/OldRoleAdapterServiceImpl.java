package com.zhonghuilv.aitravel.basic.service.service.impl;

import com.zhonghuilv.aitravel.basic.intf.pojo.SysUserRole;
import com.zhonghuilv.aitravel.basic.intf.pojo.User;
import com.zhonghuilv.aitravel.basic.intf.pojo.UserRole;
import com.zhonghuilv.aitravel.basic.service.mapper.SysUserRoleMapper;
import com.zhonghuilv.aitravel.basic.service.service.OldRoleAdapterService;
import com.zhonghuilv.aitravel.common.service.util.ExampleBuiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OldRoleAdapterServiceImpl implements OldRoleAdapterService{

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public void save(UserRole userRole) {
        List<SysUserRole> sysUserRoleList = new ArrayList<>();
        String[] temp = Optional.ofNullable(userRole.getRoleId()).orElse("").split(",");
        for (String i: temp ) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(Long.valueOf(i));
            sysUserRole.setUserId((userRole.getId()));
            sysUserRoleList.add(sysUserRole);
        }
        sysUserRoleMapper.insertList(sysUserRoleList);
    }

    @Override
    public void update(UserRole userRole) {
        Optional optional = Optional.ofNullable(userRole.getRoleId());
        optional.ifPresent((o)->{
            Example example = ExampleBuiler.cls(SysUserRole.class);
            example.createCriteria().andEqualTo("userId",userRole.getId());
            sysUserRoleMapper.deleteByExample(example);
        });
        save(userRole);
    }

    @Override
    public String select(User user) {
        String roleId = "";
        Example example = ExampleBuiler.cls(SysUserRole.class);
        example.createCriteria().andEqualTo("userId",user.getId());
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectByExample(example);
        if (sysUserRoleList.size()>0)
            for (SysUserRole temp : sysUserRoleList ) {
                roleId += temp.getRoleId().toString() + ",";
            }
        else
            return null;
        return roleId.substring(0,roleId.length()-1);
    }
}
