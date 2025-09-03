package com.projectexam.exam.Services.Filestorage;

import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    void store(MultipartFile file);

    String store(MultipartFile file, String filename);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    Path getRootLocation();

    String mimeTypeToExtension(String mimeType);

    Optional<String> getStorageHash(MultipartFile file);
}
