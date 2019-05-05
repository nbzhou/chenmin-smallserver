package com.zhonghuilv.aitravel.common.util;

import com.zhonghuilv.aitravel.common.pojo.po.ComonPo;
import com.zhonghuilv.aitravel.common.pojo.po.MainPO;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Create By chenmin on 2017/12/21 17:36
 */
public class ComonPOUtil {

    public static void initAdd(ComonPo po, Long userId) {

        if (Objects.isNull(po.getCreateTime()))
            po.setCreateTime(LocalDateTime.now());
        if (Objects.isNull(po.getCreator()))
            po.setCreator(userId);
        if (Objects.isNull(po.getEditTime()))
            po.setEditTime(LocalDateTime.now());
        if (Objects.isNull(po.getEditor()))
            po.setEditor(userId);
    }

    public static void initUpdate(ComonPo po, Long userId) {
        if (Objects.isNull(po.getEditTime()))
            po.setEditTime(LocalDateTime.now());
        if (Objects.isNull(po.getEditor()))
            po.setEditor(userId);
    }
}
