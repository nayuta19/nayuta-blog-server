package org.nayutablogserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "tencent.cos")
public class CosConfigProperties {
    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;
    private String baseUrl;
    private String folderPrefix; // 确保有这个字段

    // Lombok的@Data注解会自动生成getter/setter
    // 如果没有使用Lombok，需要手动添加：
    // public String getFolderPrefix() {
    //     return this.folderPrefix;
    // }
}