package com.zhonghuilv.aitravel.basic.service.util;

import com.zhonghuilv.aitravel.basic.intf.pojo.SysAccess;
import com.zhonghuilv.aitravel.basic.service.mapper.SysAccessMapper;
import com.zhonghuilv.aitravel.common.service.util.ExampleBuiler;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

public class FindChildOfAccess {

    private Long id;

    private SysAccessMapper sysAccessMapper;

    private List<SysAccess> sysAccessList;


    public FindChildOfAccess(Long id, SysAccessMapper sysAccessMapper){
        this.id = id;
        this.sysAccessMapper = sysAccessMapper;
        SysAccess sysAccess = new SysAccess();
        sysAccess.setId(id);
        sysAccessList= new ArrayList<SysAccess>();
        sysAccessList.add(sysAccess);
    }

    public Long deleteAllChild(){
        for(int i=0;i<sysAccessList.size();i++){
            Example example = ExampleBuiler.cls(SysAccess.class);
            example.createCriteria().andEqualTo("accessPid",sysAccessList.get(i).getId());
            if (sysAccessMapper.selectByExample(example)!=null){
                sysAccessList.addAll(sysAccessMapper.selectByExample(example));
            }
            sysAccessMapper.deleteByExample(example);
        }
        return 1L;
    }
}
