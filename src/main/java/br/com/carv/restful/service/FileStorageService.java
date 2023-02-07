package br.com.carv.restful.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String storageFile(MultipartFile file);
}
