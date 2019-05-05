package com.zhonghuilv.aitravel.common.api.config.error.decoder;

import com.alibaba.fastjson.JSONObject;

/**
 * 默认处理没有被处理过的异常
 * Create By zhengjing on 2018/4/7 09:36
 */
public class DefaultDecoderPolicy implements DecoderPolicy {

    @Override
    public Exception error(String body) {
        JSONObject error = JSONObject.parseObject(body);
//
//        String exc = error.getString("exception");
//        if (StringUtils.isNotBlank(exc)) {
//            long code = 500_001L;
//            if (error.containsKey("errcode")) {
//                code = error.getLongValue("errcode");
//            }
//            exc = exc.concat(":");
//            String msg = error.getString("message");
//            int idx = msg.indexOf(exc);
//            return new ServiceLogicException(code, idx > 0 ? msg.substring(msg.indexOf(exc) + exc.length()) : msg);
//        }
        throw new RuntimeException(body);
    }
}
