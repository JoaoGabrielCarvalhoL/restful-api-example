package br.com.carv.restful.controller.impl;

import br.com.carv.restful.controller.FileController;
import br.com.carv.restful.model.dto.response.FileUploadResponse;
import br.com.carv.restful.service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
public class FileControllerImpl implements FileController {

    private final FileStorageService fileStorageService;

    private final Logger logger = Logger.getLogger(FileControllerImpl.class.getSimpleName());

    public FileControllerImpl(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Override
    public FileUploadResponse uploadFile(MultipartFile file) {
        String filename = fileStorageService.storageFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/").path(filename).toUriString();
        return new FileUploadResponse(filename, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @Override
    public List<FileUploadResponse>  uploadMultipleFile(MultipartFile[] files) {
        return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Resource> downloadFile(String filename, HttpServletRequest httpServletRequest) {
        Resource resource = fileStorageService.loadFileAsResource(filename);
        String contentType = "";
        try {
            contentType = httpServletRequest.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception ex) {
            logger.info("Could not determine file type!");
        }
        if (contentType.isBlank()) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }
}
