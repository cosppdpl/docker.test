package com.cpap.rest.webservice.restfulwebservice.user;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class UserPost {
    
    private Integer id;
    private Integer userId;
    private Date timestamp;
    private String message;

}
