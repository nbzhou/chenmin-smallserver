package com.zhonghuilv.aitravel.basic.intf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create By zhengjing on 2018/5/19 16:58
 */
@AllArgsConstructor
@Getter
public enum EnumRankAwardCode {

    RUN_DATA("步数排名"),
    GAME_LINE("游戏排名");

    private String desc;
}
