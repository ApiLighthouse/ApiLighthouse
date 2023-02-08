package com.apilighthouse.apilighthouse.service;

import com.apilighthouse.apilighthouse.property.DockerProperty;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Network;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author yuleis
 * @date 2023/2/6.
 */
@Service
public class DockerService {


    private static final String runState = "running";


    @Autowired
    private DockerClient dockerClient;


    @Autowired
    private DockerProperty dockerProperty;


    public List<Container> getRunContainerList(){
        List<Container> containerList = dockerClient.listContainersCmd().exec();
        if(dockerProperty.getExcludeContainers() != null){
            String[] excudeList = dockerProperty.getExcludeContainers().trim().split(",");
            Map<String,String> excludeMap = new HashMap<>();
            for(String s : excudeList){
                excludeMap.put("/"+s,s);
                excludeMap.put(s,s);
            }
            return containerList.stream().filter(p -> p.getState().equals(runState) && excludeMap.containsKey(p.getNames()[0])).collect(Collectors.toList());
        }
        return containerList.stream().filter(p -> p.getState().equals(runState)).collect(Collectors.toList());

    }

    public InspectContainerResponse getInspectContainer(String containerName){
        InspectContainerResponse response = dockerClient.inspectContainerCmd(containerName).exec();
        return response;
    }




}
