package com.zhonghuilv.aitravel.basic.api.controller;

import com.github.pagehelper.PageInfo;
import com.zhonghuilv.aitravel.authorization.common.util.UserUtil;
import com.zhonghuilv.aitravel.basic.api.controller.vo.NumberAndDog;
import com.zhonghuilv.aitravel.basic.intf.clients.RunDataAwardRecordClient;
import com.zhonghuilv.aitravel.basic.intf.clients.UserClient;
import com.zhonghuilv.aitravel.basic.intf.clients.UserRunDataClient;
import com.zhonghuilv.aitravel.basic.intf.enums.EnumRunDataAwardRecord;
import com.zhonghuilv.aitravel.basic.intf.pojo.RunDataAwardRecord;
import com.zhonghuilv.aitravel.basic.intf.pojo.User;
import com.zhonghuilv.aitravel.basic.intf.pojo.UserRunData;
import com.zhonghuilv.aitravel.basic.intf.pojo.dto.RunDataAwardRecordQueryVO;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.common.constants.CommonConst;
import com.zhonghuilv.aitravel.common.pojo.dto.PageQuery;
import com.zhonghuilv.aitravel.common.pojo.dto.QueryExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by zhengjing  on 2018-04-03 16:14:22
 */
@RestController
@RequestMapping("/user_run_data")
@Api(value = "UserRunDataController", description = "用户运动")
@Slf4j
public class UserRunDataController {


    private UserRunDataClient userRunDataClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private RunDataAwardRecordClient awardRecordClient;

    @Autowired
    public UserRunDataController(UserRunDataClient userRunDataClient) {
        this.userRunDataClient = userRunDataClient;
    }

    @ApiOperation("上传步数")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ApiResult<Boolean> save(@RequestParam("step") Integer step,
                                   @RequestHeader(value = CommonConst.DATA_AUTHORITY_HEADER_NAME,
                                           required = false, defaultValue = "1")
                                           Long senicId) {


        Long userId = UserUtil.getUserId();
        UserRunData query = new UserRunData();
        query.setRunDate(LocalDate.now());
        query.setUserId(userId);
        query.setScenicId(senicId);
        List<UserRunData> runData = userRunDataClient.loadList(query);
        if (CollectionUtils.isEmpty(runData)) {
            query.setStep(step);
            userRunDataClient.save(query);
        } else {
            if (runData.size() > 1) {
                log.warn("用户步数发现并发数据:" + runData);
                runData.stream().skip(1).map(UserRunData::getId).forEach(userRunDataClient::delete);
            }
            UserRunData update = runData.get(0);
            update.setStep(step);
            userRunDataClient.updateSelective(update);
        }
        return ApiResult.success(true);
    }


    @GetMapping("/number_and_dog")
    @ApiOperation("获取今日幸运数字和昨日幸运用户")
    public ApiResult<NumberAndDog> numberAndDog(@RequestParam("senicId") @ApiParam("景区id") Long senicId) {

        Integer luckyNumber = getLuckyNumber(senicId);
        RunDataAwardRecord yesterdayLuckyDog = getYesterdayLuckyDog(senicId);
        NumberAndDog dog = new NumberAndDog();
        dog.setLuckyNumber(luckyNumber);
        dog.setYesterdayLuckyDog(yesterdayLuckyDog);
        return ApiResult.success(dog);
    }


    private Integer getLuckyNumber(Long senicId) {
        LocalDate today = LocalDate.now();
        RunDataAwardRecord recordQuery = new RunDataAwardRecord();
        recordQuery.setScenicId(senicId);
        recordQuery.setAwardDate(today);

        List<RunDataAwardRecord> records = awardRecordClient.loadList(recordQuery);
        if (CollectionUtils.isEmpty(records)) {
            RunDataAwardRecord todayRecord = new RunDataAwardRecord();
            todayRecord.setLuckyNumber(RandomUtils.nextInt(1, 21));
            todayRecord.setScenicId(senicId);
            todayRecord.setAwardDate(today);
            todayRecord.setState(EnumRunDataAwardRecord.NOT_GRANT.getKey());
            awardRecordClient.save(todayRecord);
            return todayRecord.getLuckyNumber();
        }

        /**
         * 如果有并发产生的多余数据
         */
        if (records.size() > 1) {
            log.warn("步数奖励发现并发数据:" + records);
            records.stream().skip(1).map(RunDataAwardRecord::getId).forEach(awardRecordClient::delete);
        }
        return records.get(0).getLuckyNumber();
    }

    private RunDataAwardRecord getYesterdayLuckyDog(Long senicId) {
        LocalDate yesterday = LocalDate.now().minus(1L, ChronoUnit.DAYS);
        RunDataAwardRecord recordQuery = new RunDataAwardRecord();
        recordQuery.setScenicId(senicId);
        recordQuery.setAwardDate(yesterday);

        RunDataAwardRecord yesterdayRecord = awardRecordClient.loadOne(recordQuery);
        if (yesterdayRecord != null && Objects.nonNull(yesterdayRecord.getUserId())) {
            yesterdayRecord.setUser(userClient.loadById(yesterdayRecord.getUserId()));
        }
        return yesterdayRecord;
    }

    @GetMapping("/lucky")
    @ApiOperation("获取今日幸运数字")
    public ApiResult<Integer> luckyNumber(@RequestParam("senicId") @ApiParam("景区id") Long senicId) {

        return ApiResult.success(getLuckyNumber(senicId));

    }

    @GetMapping("/yesterday_lucky_dog")
    @ApiOperation("获取昨日幸运儿")
    public ApiResult<RunDataAwardRecord> yesterdayLuckyDog(@RequestParam("senicId") @ApiParam("景区id") Long senicId) {

        LocalDate yesterday = LocalDate.now().minus(1L, ChronoUnit.DAYS);
        RunDataAwardRecord recordQuery = new RunDataAwardRecord();
        recordQuery.setScenicId(senicId);
        recordQuery.setAwardDate(yesterday);

        RunDataAwardRecord yesterdayRecord = awardRecordClient.loadOne(recordQuery);
        if (yesterdayRecord != null && Objects.nonNull(yesterdayRecord.getUserId())) {
            yesterdayRecord.setUser(userClient.loadById(yesterdayRecord.getUserId()));
        }
        return ApiResult.success(yesterdayRecord);
    }


    @GetMapping("/lucky_dog_30_days")
    @ApiOperation("获取最近30天的幸运儿")
    public ApiResult<List<RunDataAwardRecord>> luckydog(@RequestParam("senicId") @ApiParam("景区id") Long senicId) {

        //query vo
        LocalDate yesterday = LocalDate.now().minus(1L, ChronoUnit.DAYS);
        LocalDate yesterdayMinus30 = yesterday.minus(30L, ChronoUnit.DAYS);
        RunDataAwardRecordQueryVO queryVO = new RunDataAwardRecordQueryVO();
        queryVO.setScenicId(senicId);
        queryVO.setStartDate(yesterdayMinus30);
        queryVO.setEndDate(yesterday);

        List<RunDataAwardRecord> records = awardRecordClient.loadByQuery(queryVO);

        if (!CollectionUtils.isEmpty(records)) {

            //map to user id
            List<Long> userIds = records.stream().map(RunDataAwardRecord::getUserId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (!CollectionUtils.isEmpty(userIds)) {
                List<User> users = userClient.loadByIds(userIds);
                if (!CollectionUtils.isEmpty(users)) {
                    Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, Function.identity
                            ()));

                    records.stream().filter(record -> Objects.nonNull(record.getUserId()))
                            .forEach(record -> record.setUser(userMap.get(record.getUserId())));
                }
            }
        }

        return ApiResult.success(records);
    }


    @ApiOperation("获取排行榜")
    @RequestMapping(value = "/rank", method = RequestMethod.GET)
    public ApiResult<PageInfo<UserRunData>> rank(@RequestParam(value = "step", required = false)
                                                 @ApiParam("日期(不传 查询当天)")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                         LocalDate rankDate,
                                                 @RequestHeader(value = CommonConst.DATA_AUTHORITY_HEADER_NAME,
                                                         required = false, defaultValue = "1")
                                                         Long senicId) {

        if (Objects.isNull(rankDate)) {
            rankDate = LocalDate.now();
        }
        PageQuery<UserRunData> query = new PageQuery<>();
        query.setPageNum(1);
        query.setPageSize(20);
        UserRunData runData = new UserRunData();
        runData.setRunDate(rankDate);
        runData.setScenicId(senicId);
        query.setQueryPo(runData);
        PageInfo<UserRunData> userRunDataPageInfo = userRunDataClient.loadPage(query);

        //设置头像和昵称
        List<Long> ids = userRunDataPageInfo.getList().stream().map(UserRunData::getUserId).collect(Collectors.toList
                ());
        if (!CollectionUtils.isEmpty(ids)) {
            Map<Long, User> collect = userClient.loadByIds(ids).stream().collect(Collectors.toMap(User::getId, Function
                    .identity()));
            userRunDataPageInfo.getList().forEach(userRunData -> {
                if (collect.containsKey(userRunData.getUserId())) {
                    User user = collect.get(userRunData.getUserId());
                    userRunData.setNickname(user.getNickname());
                    userRunData.setAvatar(user.getAvatar());
                }

            });
        }
        return ApiResult.success(userRunDataPageInfo);
    }
}

