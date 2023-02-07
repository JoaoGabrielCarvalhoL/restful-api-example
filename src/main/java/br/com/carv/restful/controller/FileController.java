package br.com.carv.restful.controller;

import br.com.carv.restful.model.dto.response.FileUploadResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "File Endpoint")
public interface FileController {

    @Operation(summary = "Upload File", description = "Upload file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = { @Content
                    (schema = @Schema(implementation = FileUploadResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PostMapping(value = "/upload")
    @ResponseStatus(HttpStatus.CREATED)
    FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file);
}
