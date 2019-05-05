package com.zhonghuilv.aitravel.basic.api.controller;

import com.github.pagehelper.PageInfo;
import com.zhonghuilv.aitravel.authorization.common.util.UserUtil;
import com.zhonghuilv.aitravel.basic.api.controller.vo.NotificationVO;
import com.zhonghuilv.aitravel.basic.intf.clients.NotificationClient;
import com.zhonghuilv.aitravel.basic.intf.enums.EnumNotificationState;
import com.zhonghuilv.aitravel.basic.intf.enums.EnumNotificationType;
import com.zhonghuilv.aitravel.basic.intf.pojo.Notification;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.common.pojo.dto.PageQuery;
import com.zhonghuilv.aitravel.order.intf.clients.TbCouponClient;
import com.zhonghuilv.aitravel.order.intf.pojo.TbCoupon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by zhengjing  on 2018-04-29 17:12:50
 */
@RestController
@RequestMapping("/notification")
@Api(value = "NotificationController", description = "系统通知")
public class NotificationController {

    @Autowired
    private NotificationClient notificationClient;

    @Autowired
    private TbCouponClient tbCouponClient;

    @GetMapping("/_not_read_count")
    @ApiOperation("获取未读数量")
    public ApiResult<Integer> notReadCount() {

        Notification notification = new Notification();
        notification.setUserId(UserUtil.getUserId());
        notification.setState(EnumNotificationState.NOT_READ.getState());
        //TODO 是否有 其他类型
        return ApiResult.success(notificationClient.loadCount(notification));
    }


    @GetMapping("/_dialog")
    @ApiOperation("首页弹框通知")
    public ApiResult<List<NotificationVO>> getNotificationIndex() {

        Notification notification = new Notification();
        notification.setUserId(UserUtil.getUserId());
        notification.setState(EnumNotificationState.NOT_READ.getState());

        List<Notification> notifications = notificationClient.loadList(notification);
        List<NotificationVO> dialogMsg = new ArrayList<>();
        if (!CollectionUtils.isEmpty(notifications)) {
            //设置弹框信息 设置优惠券信息
            dialogMsg = notifications.stream().filter(model -> EnumNotificationType.GET_INTEGRAL
                    .name().equalsIgnoreCase(model.getType()) ||
                    EnumNotificationType.GET_COUPON.name().equalsIgnoreCase(model.getType()))
                    .map(model -> {
                        NotificationVO notificationVO = new NotificationVO();
                        BeanUtils.copyProperties(model, notificationVO);
                        if (EnumNotificationType.GET_COUPON.name().equalsIgnoreCase(model.getType())) {
                            if (Objects.nonNull(model.getTargetValue())) {
                                notificationVO.setTbCoupon(tbCouponClient.loadById(model.getTargetValue()));
                            }
                        }
                        return notificationVO;
                    })
                    .collect(Collectors.toList());

        }
        return ApiResult.success(dialogMsg);
    }

    @PatchMapping("/{id}/_read")
    @ApiOperation("读消息（点击查看时调用）")
    public ApiResult<Boolean> readNotificationPatch(@PathVariable("id") Long id) {

        return ApiResult.success(_readNotification(id));
    }

    @PostMapping("/{id}/_read")
    @ApiOperation("读消息（点击查看时调用）")
    public ApiResult<Boolean> readNotificationPost(@PathVariable("id") Long id) {

        return ApiResult.success(_readNotification(id));
    }


    private boolean _readNotification(Long id) {
        Notification notification = new Notification();
        notification.setId(id);
        notification.setState(EnumNotificationState.READED.getState());
        notificationClient.updateSelective(notification);
        return true;
    }

    @GetMapping("")
    @ApiOperation("查询不分页")
    public ApiResult<List<Notification>> getNotification(@RequestParam(value = "state", required = false)
                                                         @ApiParam("状态 1- 未读 2已读  不传 查所有")
                                                                 Integer state) {

        Notification notification = new Notification();
        notification.setUserId(UserUtil.getUserId());
        notification.setState(state);
        //TODO 是否有 其他类型
        return ApiResult.success(notificationClient.loadList(notification));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public ApiResult<PageInfo<Notification>> loadPageInfo(@RequestParam(value = "state", required = false)
                                                          @ApiParam("状态 1- 未读 2已读  不传 查所有")
                                                                  Integer state,
                                                          @RequestParam(value = "pageNum", required = false)
                                                          @ApiParam("第几页") Integer pageNum,
                                                          @RequestParam(value = "pageSize", required = false)
                                                          @ApiParam("每页多少行") Integer pageSize) {

        Long userId = UserUtil.getUserId();
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setState(state);
        PageQuery<Notification> notificationPageQuery = new PageQuery<>();
        notificationPageQuery.setPageNum(pageNum);
        notificationPageQuery.setPageSize(pageSize);
        notificationPageQuery.setQueryPo(notification);
        return ApiResult.success(notificationClient.loadPage(notificationPageQuery));
    }

//    @PostMapping("/_search")
//    @ApiOperation("列表查询")
//    public ApiResult<List<Notification>> loadList(@RequestBody Notification notification) {
//        return ApiResult.success(notificationClient.loadList(notification));
//    }

}

