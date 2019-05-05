package com.chemin.smallserver.constant.service.config;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.validation.constraints.NotNull;

/**
 * Create By zhengjing on 2017/12/15 14:01
 */
@Data
@ConfigurationProperties("aliyun.sts")
@Configuration
public class StsClientConfiguration {

    @NotNull
    private String accessKeyId;

    @NotNull
    private String accessKeySecret;

    @NotNull
    private String region;

    @NotNull
    private String version;

    @NotNull
    private String roleArn;

    @NotNull
    private String uploadEndpoint;

    private String uploadBucket = "ailx-static";

    @Bean("stsClient")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public DefaultAcsClient acsClient() {
        IClientProfile profile = DefaultProfile.getProfile(region, accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }

    @Bean
    public OSSClient ossClient() {
        return new OSSClient(uploadEndpoint, new DefaultCredentialProvider(accessKeyId, accessKeySecret), null);
    }
}
