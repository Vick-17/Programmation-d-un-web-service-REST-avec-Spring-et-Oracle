package com.projectexam.exam.Services.Filestorage;

import org.springframework.core.io.Resource;

import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

/**
 * Service de stockage de fichiers sur disque (upload/download). Fourni une API
 * simple pour stocker, lister et charger des ressources depuis un répertoire racine.
 */
public interface FileStorageService {
    /** Stocke un fichier sous son nom d'origine. */
    void store(MultipartFile file);

    /** Stocke un fichier sous un nom explicite et retourne le nom stocké. */
    String store(MultipartFile file, String filename);

    /** Liste les chemins relatifs de tous les fichiers au niveau racine. */
    Stream<Path> loadAll();

    /** Retourne le chemin (Path) pour un fichier. */
    Path load(String filename);

    /** Retourne une Resource lisible pour un fichier. */
    Resource loadAsResource(String filename);

    /** Supprime récursivement le contenu du répertoire racine. */
    void deleteAll();

    /** Retourne le répertoire racine de stockage. */
    Path getRootLocation();

    /** Convertit un type MIME en extension de fichier. */
    String mimeTypeToExtension(String mimeType);

    /** Calcule un hash (MD5) de métadonnées du fichier pour nommage stable. */
    Optional<String> getStorageHash(MultipartFile file);
}
