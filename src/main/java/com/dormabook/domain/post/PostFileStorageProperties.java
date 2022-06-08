package com.dormabook.domain.post;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class PostFileStorageProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }
    public String getUploadDir2() {
        return uploadDir;
    }
    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
