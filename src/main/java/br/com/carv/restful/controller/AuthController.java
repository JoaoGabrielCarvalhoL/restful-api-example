package br.com.carv.restful.controller;

import br.com.carv.restful.model.dto.request.AuthUserRequest;
import br.com.carv.restful.model.dto.response.PersonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Authentication Endpoint")
public interface AuthController {

    @Operation(summary = "Authenticates User", description = "Authenticates a user and returns a token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = { @Content
                    (schema = @Schema(implementation = PersonResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PostMapping(name = "/signin",  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> signIn(@RequestBody AuthUserRequest userRequest);
}
