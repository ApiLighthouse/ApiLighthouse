package com.apilighthouse.apilighthouse.controller;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.apilighthouse.apilighthouse.entity.TLighthouse;
import com.apilighthouse.apilighthouse.service.TLighthouseService;
import com.apilighthouse.apilighthouse.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dataMinder
 * @since 2023-01-06
 */
@RestController
@RequestMapping({"/","/apiLighthouse"})
public class TLighthouseController {
    @Autowired
    private TLighthouseService tLighthouseService;

    @RequestMapping("/list")
    private Result<TLighthouse> list(){
        return Result.succeed(tLighthouseService.list());
    }

    @RequestMapping({"/change","/add"})
    private Result<TLighthouse> add(@RequestParam String serviceName, @RequestParam String url, @RequestParam(defaultValue = "") String remarks){
        return Result.succeed(tLighthouseService.saveOrUpdate(new TLighthouse(serviceName,url,remarks)));
    }

    @RequestMapping({"/delete","remove"})
    private Result<TLighthouse> delete(@RequestParam String serviceName){
        LambdaUpdateWrapper<TLighthouse> wrapper = new LambdaUpdateWrapper<TLighthouse>();
        wrapper.eq(TLighthouse::getServerName,serviceName);
        return Result.succeed(tLighthouseService.remove(wrapper));
    }

}
