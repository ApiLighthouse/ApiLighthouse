package com.apilighthouse.apilighthouse.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yuleis
 * @date 2023/2/6.
 */
@Component
@ConfigurationProperties(prefix = "custom.docker")
@Data
public class DockerProperty {

    /**
     * 是否启用
     */
    private Boolean enable = true;

    /**
     * 排队的容器，以，分隔
     */
    private String excludeContainers;

    /**
     * The Docker Host URL, e.g. tcp://localhost:2376 or unix:///var/run/docker.sock
     */
    private String dockerHost = "tcp://localhost:2376";

    /**
     * enable/disable TLS verification (switch between http and https protocol)
     */
     private Boolean dockerTlsVerify = false;

    /**
     * Path to the certificates needed for TLS verification
     */
    private String dockerCertPath;

    /**
     * Your registry username (required to push containers).
     */
     private String registryUsername;

    /**
     *  Your registry password.
     */
    private String registryPassword;

    /**
     * Your registry email.
     */
     private String registryEmail;

    /**
     * Your registry's address.
     */
    private String registryUrl;
}
