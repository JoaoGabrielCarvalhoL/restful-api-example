package br.com.carv.restful.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String storageFile(MultipartFile file);

    Resource loadFileAsResource(String filename);
}
