package com.controller;

import com.domain.User;
import com.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    LoginService loginService;
    @GetMapping("/users/find")
    public List<User> findUser(HttpServletRequest request){
        String date_header = request.getHeader("date_header");
        String cyphertext=request.getHeader("cyphertext");
        System.out.println(date_header+" "+cyphertext);
        return loginService.findUser();
    }

    @PostMapping("/users/find/{name}")
    public String getUserpsd(@PathVariable(value = "name") String name,HttpServletRequest request){
        System.out.println(name);
        String date_header = request.getHeader("date_header");
        String cyphertext=request.getHeader("cyphertext");
        System.out.println(date_header+" "+cyphertext);


        return loginService.getPasswd(name);
    }

}
