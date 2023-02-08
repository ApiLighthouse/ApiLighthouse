package com.apilighthouse.apilighthouse.service;

import com.apilighthouse.apilighthouse.entity.TLighthouse;
import com.apilighthouse.apilighthouse.util.ReplaceFileContents;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ContainerNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class FreshService {
    @Autowired
    TLighthouseService tLighthouseService;
    @Autowired
    private  DockerService dockerService;

    Boolean bIsInit = new Boolean(true);

//    @Scheduled(cron="*/5 * * * * ?")
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

    @Scheduled(cron="*/5 * * * * ?")
    public void doDockerList(){

        List<Container> allRunContainerList = dockerService.getRunContainerList();

        List<TLighthouse> tLighthouseList = allRunContainerList.stream().map(p -> convertTlighthouse(p)).collect(Collectors.toList());

        tLighthouseService.saveOrUpdateBatch(tLighthouseList);

    }

    private  TLighthouse convertTlighthouse(Container container){
        TLighthouse tLighthouse = new TLighthouse();
        String containName = container.getNames()[0].trim();
        if(containName.startsWith("/")){
            containName = containName.substring(1);
        }
        tLighthouse.setServerName(containName);
        if(container.getNetworkSettings().getNetworks().containsKey("bridge")){
            //"host": 都为空
           ContainerNetwork network = container.getNetworkSettings().getNetworks().get("bridge");
            Integer port = container.getPorts()[0].getPublicPort();
            tLighthouse.setUrl(network.getGateway()+":"+port);
        }else if(container.getNetworkSettings().getNetworks().containsKey("host")){
            InspectContainerResponse inspectContainerResponse =  dockerService.getInspectContainer(containName);
            Integer port = inspectContainerResponse.getConfig().getExposedPorts()[0].getPort();
            tLighthouse.setUrl("127.0.0.1:"+port);
        }

        tLighthouse.setRemarks(containName);


        return tLighthouse;

    }

}
