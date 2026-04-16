package org.example._0_implementing_rest_services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    // instead we have @RestController to tell Spring that all controllers endpoints are REST endpoints
//    @GetMapping("/hello")
//    @ResponseBody // we tell the servlet we don't return a view name but a direct HTTP response
//    public String hello(){
//        return "Hello!";
//    }
//
//    @GetMapping("/ciao")
//    @ResponseBody
//    public String cio(){
//        return "Ciao!";
//    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello!";
    }

    @GetMapping("/ciao")
    public String cio(){
        return "Ciao!";
    }

}
