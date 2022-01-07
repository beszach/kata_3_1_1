package com.alex.test_kata_3_1_1.controller;


import com.alex.test_kata_3_1_1.model.User;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Log4j2
public class UsersController {

    @GetMapping("/user")
    public String user(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        log.info("Info about user: "+user.toString());
        modelMap.addAttribute("user", user);
        return "user";
    }

}
