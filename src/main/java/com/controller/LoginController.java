package com.controller;

import com.domain.User;
import com.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    LoginService loginService;
    @GetMapping("/users/find")
    public List<User> findUser(){
        return loginService.findUser();
    }
//    @PostMapping("/users/login")
//    public Map<String, String> loginMatch(@Valid @RequestBody User user){
//        return loginService.login(user);
//    }

}
