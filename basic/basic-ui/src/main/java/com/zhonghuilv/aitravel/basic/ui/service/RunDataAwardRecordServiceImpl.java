package com.zhonghuilv.aitravel.basic.ui.service;

import com.github.pagehelper.PageInfo;
import com.zhonghuilv.aitravel.basic.intf.clients.RunDataAwardRecordClient;
import com.zhonghuilv.aitravel.basic.intf.clients.UserRunDataClient;
import com.zhonghuilv.aitravel.basic.intf.enums.EnumRunDataAwardRecord;
import com.zhonghuilv.aitravel.basic.intf.pojo.RunDataAwardConfig;
import com.zhonghuilv.aitravel.basic.intf.pojo.RunDataAwardRecord;
import com.zhonghuilv.aitravel.basic.intf.pojo.UserRunData;
import com.zhonghuilv.aitravel.basic.ui.dto.Rank;
import com.zhonghuilv.aitravel.basic.ui.task.grant.GrantAward;
import com.zhonghuilv.aitravel.common.enums.EnumState;
import com.zhonghuilv.aitravel.common.pojo.dto.PageQuery;
import com.zhonghuilv.aitravel.travel.intf.clients.ScenicClient;
import com.zhonghuilv.aitravel.travel.intf.pojo.Scenic;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Create By zhengjing on 2018/5/23 15:33
 */
@Slf4j
@Service
public class RunDataAwardRecordServiceImpl implements RunDataAwardRecordService {

    @Autowired
    UserRunDataClient userRunDataClient;

    @Autowired
    ScenicClient scenicClient;

    /**
     * 奖励记录
     */
    @Autowired
    RunDataAwardRecordClient recordClient;

    @Autowired
    ConfigService configService;

    @Autowired(required = false)
    List<GrantAward> grantAwards = new LinkedList<>();

    @Override
    public void grantAward() {

        Scenic query = new Scenic();
        query.setState(EnumState.NORMAL.getKey());

        List<Scenic> scenics = scenicClient.loadList(query);
        if (CollectionUtils.isEmpty(scenics)) {
            log.warn("景区数据为空");
            return;
        }
        log.trace("获取所有景区数据:{}", scenics);

        scenics.parallelStream().forEach(this::grantAward);
    }

    private void grantAward(Scenic scenic) {
        this.grantAward(scenic, LocalDate.now());
    }

    @Override
    public void grantAward(Scenic scenic, LocalDate grantDate) {

        LocalDate today = grantDate == null ? LocalDate.now() : grantDate;
        // 获取中奖人
        log.trace("发放{}的奖励", scenic.getScenicSpotName());
        Long id = scenic.getId();

        RunDataAwardConfig runDataAwardConfig = configService.getRunDataAwardConfig(scenic.getId());
        log.trace("加载步数排行的奖励配置：{}", runDataAwardConfig);
        if (Objects.isNull(runDataAwardConfig)) {
            log.warn("每日步数奖励配置为空 ");
            return;
        }

        RunDataAwardRecord recordQuery = new RunDataAwardRecord();
        recordQuery.setScenicId(scenic.getId());
        recordQuery.setAwardDate(today);

        RunDataAwardRecord todayRecord = null;

        List<RunDataAwardRecord> records = recordClient.loadList(recordQuery);

        if (!CollectionUtils.isEmpty(records)) {
            todayRecord = records.get(0);
        }
        /**
         * 如果有并发产生的多余数据
         */
        if (records.size() > 1)
            records.stream().skip(1).map(RunDataAwardRecord::getId).forEach(recordClient::delete);


        Integer luckyNumber = todayRecord == null ? RandomUtils.nextInt(1, 21) : todayRecord.getLuckyNumber();

        PageQuery<UserRunData> pageQuery = new PageQuery<>();
        pageQuery.setPageNum(1);
        pageQuery.setPageSize(luckyNumber);
        UserRunData runData = new UserRunData();
        runData.setRunDate(today);
        runData.setScenicId(id);
        pageQuery.setQueryPo(runData);
        PageInfo<UserRunData> userRunDataPageInfo = userRunDataClient.loadPage(pageQuery);
        List<UserRunData> list = userRunDataPageInfo.getList();

        // init ranks
        Rank luckyRanker = null;

        if (list.size() >= luckyNumber) {
            luckyRanker = new Rank(luckyNumber, "第" + (luckyNumber) + "名", list.get(luckyNumber - 1).getUserId());
        }
        // 获取中奖人 end

        GrantAward granter = grantAwards.stream().filter(grantAward ->
                grantAward.supports(runDataAwardConfig.getAwardType()))
                .findAny().orElse(null);
        if (granter == null) {
            log.warn("没有找到奖励发送者：{}", runDataAwardConfig);
            return;
        }
        log.info("幸运儿为：" + luckyRanker);

        if (todayRecord == null) {
            todayRecord = new RunDataAwardRecord();
            todayRecord.setLuckyNumber(luckyNumber);
            todayRecord.setScenicId(scenic.getId());
            todayRecord.setAwardDate(today);
        }
        todayRecord.setAwardType(runDataAwardConfig.getAwardType());
        todayRecord.setAwardValue(runDataAwardConfig.getAwardValue());
        if (luckyRanker != null) {
            todayRecord.setUserId(luckyRanker.getUserId());
            granter.grant(scenic, runDataAwardConfig, luckyRanker);
        }
        todayRecord.setState(EnumRunDataAwardRecord.GRANTED.getKey());
        if (todayRecord.getId() == null) {
            recordClient.save(todayRecord);
        } else {
            recordClient.updateSelective(todayRecord);
        }
    }

}
