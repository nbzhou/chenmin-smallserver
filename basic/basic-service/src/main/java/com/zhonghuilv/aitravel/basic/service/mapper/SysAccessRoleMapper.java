package com.zhonghuilv.aitravel.basic.service.mapper;

import com.zhonghuilv.aitravel.basic.intf.pojo.SysAccess;
import com.zhonghuilv.aitravel.basic.intf.pojo.SysAccessRole;
import com.zhonghuilv.aitravel.common.service.mapper.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SysAccessRoleMapper extends CommonMapper<SysAccessRole> {
    List<SysAccess> selectAccessByRoleId (@Param("roleId") Long roleId);
}
