package com.chemin.smallserver.constant.service.service;

import com.chemin.smallserver.constant.intf.pojo.StsCredentialsVO;
import org.apache.commons.lang3.StringUtils;

/**
 * Create By zhengjing on 2017/12/15 20:53
 */
public interface StsService {

    /**
     * 授权默认的bucket
     *
     * @param path
     * @return
     */
    StsCredentialsVO policyDefaultBucket(String path, String roleSessionName);

    /**
     * 授权指定的bucket
     *
     * @param bucketName
     * @param path
     * @return
     */
    StsCredentialsVO policy(String bucketName, String path, String roleSessionName);

    /**
     * 读写权限 的Policy
     *
     * @param bucketName
     * @param path
     * @return
     */
    default String wrPolicy(String bucketName, String path) {
        String resource = "acs:oss:*:*:" + bucketName + (StringUtils.startsWith(path, "/") ? path : "/" + path) + "/*";
        String policy = "{\n" +
                "    \"Version\": \"1\", \n" +
                "    \"Statement\": [\n" +
                "{\n" +
                "            \"Effect\": \"Allow\",\n" +
                "            \"Action\": [\n" +
                "                \"oss:ListBuckets\",\n" +
                "                \"oss:GetBucketAcl\"\n" +
                "            ],\n" +
                "            \"Resource\": [\n" +
                "                \"acs:oss:*:*:*\"\n" +
                "            ]\n" +
                "        },{\n" +
                "      \"Effect\": \"Allow\",\n" +
                "      \"Action\": [\n" +
                "        \"oss:ListObjects\",\n" +
                "        \"oss:GetBucketAcl\"\n" +
                "      ],\n" +
                "      \"Resource\": \"acs:oss:*:*:" + bucketName + "\"\n" +
                "    },        {\n" +
                "            \"Action\": [\n" +
                "                \"oss:*\"\n" +
                "            ], \n" +
                "            \"Resource\": [\n" +
                "                \"" + resource + "\"\n" +
                "            ], \n" +
                "            \"Effect\": \"Allow\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        return policy;
    }
}
