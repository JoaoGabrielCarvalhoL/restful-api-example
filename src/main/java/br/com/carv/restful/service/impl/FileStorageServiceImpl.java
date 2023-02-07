package br.com.carv.restful.service.impl;

import br.com.carv.restful.config.FileStorageConfig;
import br.com.carv.restful.exception.FileStorageException;
import br.com.carv.restful.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Logger logger = Logger.getLogger(FileStorageServiceImpl.class.getSimpleName());

    private final Path fileStorageLocation;
    private final FileStorageConfig fileStorageConfig;

    public FileStorageServiceImpl(FileStorageConfig fileStorageConfig) {
        Path path = Paths.get(fileStorageConfig.getUploadDirectory()).toAbsolutePath().normalize();
        this.fileStorageLocation = path;
        this.fileStorageConfig = fileStorageConfig;

        try {

            Files.createDirectories(this.fileStorageLocation);

        } catch (Exception exception) {
            throw new FileStorageException("Could not create directory where the uploaded files will be storage!", exception);
        }
    }

    public String storageFile(MultipartFile file) {
        logger.info("Storing file to disk");
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (filename.contains("..")) {
                throw new FileStorageException("Filename contains invalid path sequence. Filename: " + filename);
            }

            Path targetLocation = this.fileStorageLocation.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return filename;
        } catch (Exception exception) {
            throw new FileStorageException("Could not store file " + filename + ". Please, try again!", exception);
        }

    }

}
