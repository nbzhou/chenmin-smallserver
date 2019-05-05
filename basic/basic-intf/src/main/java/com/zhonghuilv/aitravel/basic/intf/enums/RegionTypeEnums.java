package com.zhonghuilv.aitravel.basic.intf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegionTypeEnums {
    STATE(1, "省级"), CITY(2, "市级"), COUNTY(3, "区县级");
    private Integer key;
    private String code;
}
