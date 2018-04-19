package com.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Belkin Sergei.
 */
@ResponseStatus( HttpStatus.NOT_FOUND )
public class RoleNotFoundexception extends RuntimeException {

    public RoleNotFoundexception( Long id ) {
        super( "Role with id " + id + " not found." );
    }
    
}
