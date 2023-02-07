package br.com.carv.restful.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.servlet.multipart.location")
public class FileStorageConfig {

    @Value("${spring.servlet.multipart.location}")
    private String uploadDirectory;

    public FileStorageConfig() {
    }

    public String getUploadDirectory() {
        return uploadDirectory;
    }

    public void setUploadDirectory(String uploadDirectory) {
        this.uploadDirectory = uploadDirectory;
    }
}
