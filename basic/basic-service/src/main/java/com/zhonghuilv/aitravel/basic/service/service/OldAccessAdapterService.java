package com.zhonghuilv.aitravel.basic.service.service;

import com.zhonghuilv.aitravel.basic.intf.pojo.RoleAccess;
import com.zhonghuilv.aitravel.basic.intf.pojo.vo.RoleAccessVO;

public interface OldAccessAdapterService {

    RoleAccessVO select(Long roleId);

    Boolean insert(RoleAccess roleAccess);

    Boolean delete(Long roleId);
}
