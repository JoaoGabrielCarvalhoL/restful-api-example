package br.com.carv.restful.controller.impl;

import br.com.carv.restful.controller.FileController;
import br.com.carv.restful.model.dto.response.FileUploadResponse;
import br.com.carv.restful.service.FileStorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/file")
public class FileControllerImpl implements FileController {

    private final FileStorageService fileStorageService;

    public FileControllerImpl(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Override
    public FileUploadResponse uploadFile(MultipartFile file) {
        String filename = fileStorageService.storageFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/").path(filename).toUriString();
        return new FileUploadResponse(filename, fileDownloadUri, file.getContentType(), file.getSize());
    }
}
