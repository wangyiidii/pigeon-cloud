package cn.yiidii.pigeon.file.strategy.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.yiidii.pigeon.file.api.entity.Attachment;
import cn.yiidii.pigeon.file.properties.OssProperties;
import cn.yiidii.pigeon.file.strategy.service.AbstractFileStrategy;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

/**
 * S3
 *
 * @author: YiiDii Wang
 * @create: 2021-03-09 22:47
 */
@Slf4j
public class S3FileStrategyImpl extends AbstractFileStrategy {

    public S3FileStrategyImpl(OssProperties ossProperties) {
        super(ossProperties);
    }

    @Override
    protected void practicalUploadFile(Attachment file, MultipartFile multipartFile) throws Exception {
        AmazonS3 client = getClient();
        String bucketName = fileProperties.getBucketName();
        if (!client.doesBucketExistV2(bucketName)) {
            client.createBucket(bucketName);
        }

        byte[] bytes = IOUtils.toByteArray(multipartFile.getInputStream());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        client.putObject(bucketName, file.getUrl(), byteArrayInputStream, objectMetadata);
        client.shutdown();
    }

    @Override
    public boolean delete(String bucketName, String objectName) {
        log.warn(StrUtil.format("模拟删除[{}}]的[{}]成功", bucketName, objectName));
        return true;
    }

    private AmazonS3 getClient() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
                fileProperties.getEndpoint(), fileProperties.getRegion());
        AWSCredentials awsCredentials = new BasicAWSCredentials(fileProperties.getAccessKey(),
                fileProperties.getSecretKey());
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        return AmazonS3Client.builder().withEndpointConfiguration(endpointConfiguration)
                .withClientConfiguration(clientConfiguration).withCredentials(awsCredentialsProvider)
                .disableChunkedEncoding().withPathStyleAccessEnabled(fileProperties.getPathStyleAccess()).build();
    }
}
