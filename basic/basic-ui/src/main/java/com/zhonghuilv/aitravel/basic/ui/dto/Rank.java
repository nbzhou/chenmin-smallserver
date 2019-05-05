package com.zhonghuilv.aitravel.basic.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create By zhengjing on 2018/5/23 15:47
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rank {

    private Integer rank;

    private String rankName;

    private Long userId;
}
