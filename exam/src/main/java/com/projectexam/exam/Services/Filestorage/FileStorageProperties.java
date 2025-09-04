package com.projectexam.exam.Services.Filestorage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Propriétés de configuration du stockage de fichiers.
 * Récupérées depuis application.properties via le préfixe "filestorage".
 */
@Configuration
@ConfigurationProperties(prefix = "filestorage")
public class FileStorageProperties {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
