package com.zhonghuilv.aitravel.basic.service.service;

import com.zhonghuilv.aitravel.basic.intf.pojo.User;
import com.zhonghuilv.aitravel.basic.intf.pojo.UserRole;

public interface OldRoleAdapterService {
    void save (UserRole userRole);

    void update(UserRole userRole);

    String select(User user);
}
