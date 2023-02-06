package br.com.carv.restful.service;

import br.com.carv.restful.model.dto.request.AuthUserRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> signIn(AuthUserRequest userRequest);

    ResponseEntity<?> refreshToken(String username, String refreshToken);
}
