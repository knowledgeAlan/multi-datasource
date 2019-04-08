package com.druid.controller;

import com.druid.entity.TUser;
import com.druid.service.TUseSerive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @Autowired
    private TUseSerive tUseSerive;

    @GetMapping("listMaster")
    public List<TUser> listMaster(){
        return  tUseSerive.listMaster();
    }

    @GetMapping("listSlave")
    public List<TUser> listSlave(){
        return  tUseSerive.listSlave();
    }

}
