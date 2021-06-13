package com.cpap.rest.webservice.restfulwebservice.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    
    @Autowired
    private MessageSource messageSource;

    //GET
    //URI - /hello-world
    //method - "Hello World"
    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    //GET
    //URI - /hello-world
    //method - "Hello World"
    @GetMapping(path = "/hello-worldv2")
    public String helloWorld_2(){
        return "Hello World V2";
    }

    //GET
    //URI - /hello-world
    //method - "Hello World Bean"
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello world bean");
    }

    //GET
    //URI - /hello-world
    //method - "Hello World Bean"
    @GetMapping(path = "/hello-world/path-variable/{nume}")
    public HelloWorldBean helloWorldPathVar(@PathVariable String nume){
        return new HelloWorldBean("Hello world " + nume);
    }

    @GetMapping(path = "/hello-world-internationalization")
    public HelloWorldBean helloWorldInternationalBean(@RequestHeader(name="Accept-Language", required=false) Locale locale){
        return new HelloWorldBean(messageSource.getMessage("good.morning.message", null, locale));
    }

}
