package com.apilighthouse.apilighthouse.service;

import com.apilighthouse.apilighthouse.entity.TLighthouse;
import com.apilighthouse.apilighthouse.util.ReplaceFileContents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class FreshService {
    @Autowired
    TLighthouseService tLighthouseService;

    Boolean bIsInit = new Boolean(true);

    @Scheduled(cron="*/5 * * * * ?")
    public void doSomeWork(){

        try {
        if (bIsInit.booleanValue()){
            Runtime.getRuntime().exec("nginx");
            bIsInit = false ;
        }

        List<TLighthouse> list = tLighthouseService.list();

        list.forEach(tLighthouse -> {
            list.forEach(tLighthouse1 -> {
                if (tLighthouse.getUrl() .equals(tLighthouse1.getServerName())) {
                    tLighthouse.setUrl(tLighthouse1.getUrl());
                }
            });
        });




        Process proc = null;

            ReplaceFileContents.write(list);
            proc = Runtime.getRuntime().exec("nginx -s reload");
            proc.waitFor(); //阻塞，直到上述命令执行完
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println("do some work om time......");
    }

}
