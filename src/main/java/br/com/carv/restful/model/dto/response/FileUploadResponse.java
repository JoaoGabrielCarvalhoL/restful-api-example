package br.com.carv.restful.model.dto.response;

import java.io.Serializable;

public class FileUploadResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String filename;
    private String fileDownloadUri;
    private String fileType;
    private Long fileSize;

    public FileUploadResponse() {

    }

    public FileUploadResponse(String filename, String fileDownloadUri, String fileType, Long fileSize) {
        this.filename = filename;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
