package br.com.carv.restful.service.impl;

import br.com.carv.restful.model.User;
import br.com.carv.restful.model.dto.request.AuthUserRequest;
import br.com.carv.restful.model.dto.response.UserResponse;
import br.com.carv.restful.repository.UserRepository;
import br.com.carv.restful.security.JwtTokenProvider;
import br.com.carv.restful.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final Logger logger = Logger.getLogger(AuthServiceImpl.class.getSimpleName());

    public AuthServiceImpl(JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> signIn(AuthUserRequest userRequest) {
        try {
            String username = userRequest.getUsername();
            String password = userRequest.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User user = userRepository.findByUsername(username);
            System.out.println(user.toString());

            UserResponse userResponse = new UserResponse();

            if (userResponse != null) {
                userResponse = jwtTokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username: " + username + " not found!");
            }

            return ResponseEntity.status(HttpStatus.OK).body(userResponse);

        } catch (Exception ex) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    public ResponseEntity<?> refreshToken(String username, String refreshToken) {
        User user = userRepository.findByUsername(username);

        UserResponse userResponse = new UserResponse();
        if (user != null) {
            userResponse = jwtTokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(userResponse);
    }


}
