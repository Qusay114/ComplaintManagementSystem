package com.nutriyummy.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     *
     * @return home page
     */
    @GetMapping("/")
    public String getHome(){
        return "home" ;
    }
}
