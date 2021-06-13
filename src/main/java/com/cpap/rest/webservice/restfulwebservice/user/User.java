package com.cpap.rest.webservice.restfulwebservice.user;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class User {
    
 
    private Integer id;
    
    @Size(min=2)
    private String name;
    @Past
    private Date birthDate;
}
