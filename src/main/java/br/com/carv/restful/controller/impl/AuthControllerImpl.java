package br.com.carv.restful.controller.impl;

import br.com.carv.restful.controller.AuthController;
import br.com.carv.restful.model.dto.request.AuthUserRequest;
import br.com.carv.restful.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    public ResponseEntity<?> signIn(@RequestBody AuthUserRequest userRequest) {
        if (userRequest != null && !userRequest.getUsername().isEmpty() && !userRequest.getPassword().isEmpty() ) {
            ResponseEntity<?> tokenResponse = authService.signIn(userRequest);

            if (tokenResponse == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
            } else {
                return tokenResponse;
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
    }

    public ResponseEntity refreshToken(@PathVariable("username") String username,
                                       @RequestHeader("Authorization") String refreshToken) {
        if (checkIfParamsIsNotNull(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authService.refreshToken(username, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }

    private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }


}
