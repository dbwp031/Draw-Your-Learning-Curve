package com.dbwp031.dylc.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Controller
public class MainPageController {

    @GetMapping("/")
    public String mainPage(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        model.addAttribute("localDateTimeNow", localDateTime);
//        System.out.println("Principal: " + principal.toString());
        if (principal != null) {
            model.addAttribute("logined", "true");
        } else {
            model.addAttribute("logined", "false");
        }
        return "template/main";
    }

    @GetMapping("/access_denied")
    public String accessDeniedPage() {
        return "template/access_denied";
    }
}
