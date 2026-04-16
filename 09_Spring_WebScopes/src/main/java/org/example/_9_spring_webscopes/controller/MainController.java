package org.example._9_spring_webscopes.controller;

import org.example._9_spring_webscopes.service.LoggedUserManagementService;
import org.example._9_spring_webscopes.service.LoginCountService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final LoginCountService loginCountService;

    public MainController(LoggedUserManagementService loggedUserManagementService,  LoginCountService loginCountService) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginCountService = loginCountService;
    }

    @GetMapping("/main")
    public String hone(
            @RequestParam(required = false) String logout,
            Model model
    ) {
        // simple logout
        if(logout != null) {
            loggedUserManagementService.setUsername(null);
        }

        String username = loggedUserManagementService.getUsername();
        int count = loginCountService.getCount();

        if (username == null) {
            return "redirect:/";
        }else{
            model.addAttribute("username", username);
            model.addAttribute("loginCount", count);
            return "main";
        }
    }

}
