package br.com.carv.restful.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiFileNotFoundException extends RuntimeException {

    public ApiFileNotFoundException(String message) {
        super(message);
    }

    public ApiFileNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
