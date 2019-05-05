package com.chemin.smallserver.constant.intf.Enum;

import com.chemin.smallserver.common.excption.ParameterNotValidException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum EnumSmsTemplate {

    LOGIN(0, "AI旅行", "SMS_135801831", "验证码"),
    REGISTER(1, "AI旅行", "SMS_135801831", "验证码"),
    FIND(2, "AI旅行", "SMS_135801831", "验证码");

    private Integer type;
    private String sign;
    private String code;
    private String desc;

    public static EnumSmsTemplate valueOfType(Integer type) {

        return Stream.of(values())
                .filter(sms -> sms.getType().equals(type))
                .findAny().orElseThrow(() -> new ParameterNotValidException("短信类型不支持:" + type));
    }
}