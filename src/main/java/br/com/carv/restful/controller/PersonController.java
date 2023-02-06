package br.com.carv.restful.controller;

import br.com.carv.restful.model.Person;
import br.com.carv.restful.model.dto.request.PersonRequest;
import br.com.carv.restful.model.dto.request.PersonUpdateRequest;
import br.com.carv.restful.model.dto.response.PersonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Resource", description = "Endpoint for managing users")
public interface PersonController {

    @Operation(summary = "Create user", description = "Insert a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = { @Content
                    (schema = @Schema(implementation = PersonResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    @ResponseStatus(HttpStatus.CREATED)
    PersonResponse save(@RequestBody PersonRequest person);

    @Operation(summary = "Update user", description = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content
                    (schema = @Schema(implementation = PersonResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" },
            consumes =  { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    PersonResponse update(@RequestBody PersonUpdateRequest person);

    @Operation(summary = "Find by id", description = "Find User by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content
                    (schema = @Schema(implementation = PersonResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    @ResponseStatus(HttpStatus.OK)
    PersonResponse findById(@PathVariable("id") Long id);

    @Operation(summary = "Find all", description = "Find all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content (mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema (schema = @Schema(implementation = PersonResponse.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    @ResponseStatus(HttpStatus.OK)
    List<PersonResponse> findAll();

    @Operation(summary = "Paginated", description = "Find users paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @GetMapping(value = "/paginated", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    @ResponseStatus(HttpStatus.OK)
    Page<PersonResponse> findAllPaginated(Pageable pageable);

    @Operation(summary = "Delete", description = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(Long id);

    @Operation(summary = "Disable user", description = "Disable User by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content
                    (schema = @Schema(implementation = PersonResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PatchMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    @ResponseStatus(HttpStatus.OK)
    PersonResponse disablePerson(@PathVariable("id") Long id);


    @Operation(summary = "Paged Model", description = "Find users paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @GetMapping(value = "/another-paginated", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<EntityModel<PersonResponse>>> anotherFindAllPaginated(Pageable pageable);

}
