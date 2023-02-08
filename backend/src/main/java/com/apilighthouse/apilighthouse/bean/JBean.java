package com.apilighthouse.apilighthouse.bean;

import com.apilighthouse.apilighthouse.property.DockerProperty;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author yuleis
 * @date 2023/2/8.
 */
@Component
public class JBean {


    @Autowired
    private DockerProperty dockerProperty;


    @Bean
    public DockerClient dockerClient(){

        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(dockerProperty.getDockerHost())
                .withDockerTlsVerify(dockerProperty.getDockerTlsVerify())
                .withDockerCertPath(dockerProperty.getDockerCertPath())
                .withRegistryUsername(dockerProperty.getRegistryUsername())
                .withRegistryPassword(dockerProperty.getRegistryPassword())
                .withRegistryEmail(dockerProperty.getRegistryEmail())
                .withRegistryUrl(dockerProperty.getRegistryUrl())
                .build();
        return DockerClientBuilder.getInstance(config).build();
    }

}
