package com.zhonghuilv.aitravel.basic.intf.clients;

import com.zhonghuilv.aitravel.basic.intf.BasicConstant;
import com.zhonghuilv.aitravel.basic.intf.pojo.UserExtends;
import com.zhonghuilv.aitravel.common.intf.BasicClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhengjing  on 2018-03-27 17:47:13
 */
@FeignClient(value = BasicConstant.SERVICE_NAME)
@RequestMapping("/user_extends")
public interface UserExtendsClient extends BasicClient<UserExtends>{

}

