package br.com.carv.restful.service;

import br.com.carv.restful.model.dto.request.UserRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails loadUserByUsername(String username);

}
