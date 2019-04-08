package com.druid.service.impl;

import com.druid.annotation.Master;
import com.druid.annotation.Slave;
import com.druid.dao.TUserMapper;
import com.druid.entity.TUser;
import com.druid.service.TUseSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhongzuoming <zhongzuoming, 1299076979@qq.com>
 * @version v1.0
 * @Description baipao
 * @encoding UTF-8
 * @date 2019-04-08
 * @time 18:03
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Service
public class TUseSeriveImpl implements TUseSerive {


    @Autowired
    private TUserMapper tUserMapper;

    @Master
    @Override
    public List<TUser> listMaster() {
        return tUserMapper.selectAll();
    }

    @Slave
    @Override
    public List<TUser> listSlave() {
        return tUserMapper.selectAll();
    }
}
