package org.nayutablogserver.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nayutablogserver.config.CosConfigProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CosService {
    private final CosConfigProperties cosConfig;

    public String uploadImage(MultipartFile file) {
        // 1. 生成正确的文件路径
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filePath = "images/" + UUID.randomUUID() + fileExtension;  // 确保路径有效

        try (InputStream inputStream = file.getInputStream()) {
            // 初始化COS客户端
            COSCredentials cred = new BasicCOSCredentials(cosConfig.getSecretId(), cosConfig.getSecretKey());
            Region region = new Region(cosConfig.getRegion());
            ClientConfig clientConfig = new ClientConfig(region);
            clientConfig.setHttpProtocol(HttpProtocol.https);

            COSClient cosClient = new COSClient(cred, clientConfig);
            try {
                // 设置文件元信息
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(file.getSize());
                metadata.setContentType(file.getContentType());

                // 创建上传请求
                PutObjectRequest putObjectRequest = new PutObjectRequest(
                        cosConfig.getBucketName(),
                        filePath,
                        inputStream,
                        metadata
                );

                // 执行上传
                PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
                log.info("文件上传成功，ETag: {}", putObjectResult.getETag());

                // 返回完整访问URL
                return cosConfig.getBaseUrl() + "/" + filePath;
            } finally {
                if (cosClient != null) {
                    cosClient.shutdown(); // 关闭客户端
                }
            }
        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw new RuntimeException("文件上传失败", e);
        }
    }
}