package org.example._9_spring_webscopes.controller;

import org.example._9_spring_webscopes.LoginProcessor;
import org.example._9_spring_webscopes.service.LoggedUserManagementService;
import org.example._9_spring_webscopes.service.LoginCountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final LoginProcessor loginProcessor;
    private final LoggedUserManagementService loggedUserManagementService;
    private final LoginCountService loginCountService;

    // auto wiring
    public LoginController(LoginProcessor loginProcessor, LoggedUserManagementService loggedUserManagementService, LoginCountService loginCountService) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginProcessor = loginProcessor;
        this.loginCountService = loginCountService;
    }

    @GetMapping("/")
    public String loginGet() {
        return "login";
    }

    @PostMapping("/")
    public String logInPost(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ){
        loginProcessor.setUsername(username);
        loginProcessor.setPassword(password);

        boolean loggedIn = loginProcessor.login();

        if(loggedIn){
            model.addAttribute("message", "You are logged in!");
            loginCountService.increment();
            return "redirect:/main";
        } else {
            model.addAttribute("message", "NOT logged in!");
            return "login";
        }
    }
}