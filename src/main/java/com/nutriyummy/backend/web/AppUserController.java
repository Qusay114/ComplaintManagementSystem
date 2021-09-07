package com.nutriyummy.backend.web;

import com.nutriyummy.backend.domain.AppUser;
import com.nutriyummy.backend.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
public class AppUserController {

    @Autowired
    private AppUserService appUserService ;
    @Autowired
    private BCryptPasswordEncoder encoder ;

    /**
     *
     * @return sign up page
     */
    @GetMapping("/signup")
    public String getSignupPage(){
        return "signup" ;
    }

    @PostMapping("/signup")
    public RedirectView createNewAppUser(@RequestParam String firstName , @RequestParam String lastName,
                                         @RequestParam String username , @RequestParam String password){
        AppUser appUser = new AppUser(firstName , lastName , username , encoder.encode(password));
        AppUser savedAppUser = appUserService.saveAppUser(appUser) ;

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedAppUser, null , new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }


    /**
     *
     * @return login page
     */
    @GetMapping("/login")
    public String getLoginPage(){
        return "login" ;
    }


}
