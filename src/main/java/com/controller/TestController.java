package com.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class TestController {

    @RequestMapping("/test/{name}")
    @ResponseBody
    public void func(@PathVariable(value = "name") String name){
        System.out.println("name"+name+"Hello !");

    }

}
