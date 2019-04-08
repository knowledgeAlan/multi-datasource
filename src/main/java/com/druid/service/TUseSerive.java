package com.druid.service;

import com.druid.entity.TUser;

import java.util.List;

/**
 * @author zhongzuoming <zhongzuoming, 1299076979@qq.com>
 * @version v1.0
 * @Description baipao
 * @encoding UTF-8
 * @date 2019-04-08
 * @time 18:04
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public interface TUseSerive {

    /**
     *
     * 查询主库
     */
    List<TUser> listMaster();

    /**
     *
     * 查询从库
     */
    List<TUser> listSlave();
}
