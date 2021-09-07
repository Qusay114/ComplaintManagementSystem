package com.nutriyummy.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppUserController {

    @GetMapping("/signup")
    public String getSignupPage(){
        return "signup" ;
    }
}
