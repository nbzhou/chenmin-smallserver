package com.chemin.smallserver.constant.service.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.chemin.smallserver.constant.intf.pojo.StsCredentialsVO;
import com.chemin.smallserver.constant.service.config.StsClientConfiguration;
import com.chemin.smallserver.constant.service.service.StsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Create By zhengjing on 2017/12/15 20:55
 */
@Service
@Slf4j
public class StsServiceImpl implements StsService {

    @Autowired
    @Qualifier("stsClient")
    DefaultAcsClient acsClient;

    @Autowired
    StsClientConfiguration clientConfiguration;

    @Override
    public StsCredentialsVO policyDefaultBucket(String path, String roleSessionName) {

        return policy(clientConfiguration.getUploadBucket(), path, roleSessionName);
    }

    @Override
    public StsCredentialsVO policy(String bucketName, String path, String roleSessionName) {
        String policy = this.wrPolicy(bucketName, path);

        final AssumeRoleRequest request = new AssumeRoleRequest();
        request.setVersion(clientConfiguration.getVersion());
        request.setMethod(MethodType.POST);
        request.setProtocol(ProtocolType.HTTPS);
        request.setRoleArn(clientConfiguration.getRoleArn());
        request.setRoleSessionName(roleSessionName);
        request.setPolicy(policy);
        // 发起请求，并得到response
        try {
            final AssumeRoleResponse response = acsClient.getAcsResponse(request);
            AssumeRoleResponse.Credentials credentials = response.getCredentials();
            StsCredentialsVO credentialsVO = new StsCredentialsVO();

            credentialsVO.setAccessKeyId(credentials.getAccessKeyId());
            credentialsVO.setAccessKeySecret(credentials.getAccessKeySecret());
            credentialsVO.setExpiration(credentials.getExpiration());
            credentialsVO.setSecurityToken(credentials.getSecurityToken());
            credentialsVO.setEndpoint(clientConfiguration.getUploadEndpoint());
            credentialsVO.setBucketName(bucketName);
            credentialsVO.setPath(path);
            return credentialsVO;
        } catch (ClientException e) {
            log.error("阿里sts授权失败", e);
            return null;
        }
    }
}
