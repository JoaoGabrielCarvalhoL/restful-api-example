package br.com.carv.restful.service.impl;

import br.com.carv.restful.model.Permission;
import br.com.carv.restful.model.User;
import br.com.carv.restful.model.dto.request.AuthUserRequest;
import br.com.carv.restful.model.dto.request.UserRequest;
import br.com.carv.restful.repository.UserRepository;
import br.com.carv.restful.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final Logger logger = Logger.getLogger(UserServiceImpl.class.getSimpleName());
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Getting user by username: " + username);
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username not found! Username: " + username);
        }
    }


}
