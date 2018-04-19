package com.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Belkin Sergei.
 */
@ResponseStatus( HttpStatus.NOT_FOUND )
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException( Integer id ) {
        super( "User with id " + id + " not found." );
    }
    
}
