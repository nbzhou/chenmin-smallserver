package com.zhonghuilv.aitravel.basic.service.service;

import com.zhonghuilv.aitravel.basic.intf.pojo.User;
import com.zhonghuilv.aitravel.basic.intf.pojo.vo.UserVO;

public interface UserExtendsTableBandService {

    UserVO saveExtends(User user);

    UserVO updateExtends(User user);

    UserVO selectExtends(User user);

    UserVO selectAllExtends(User user);
}
