package com.cpap.rest.webservice.restfulwebservice.exception;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class ExceptionResponse {

    //timestamp
    private Date timestamp;
    //message
    private String message;
    private String details;

    
}
