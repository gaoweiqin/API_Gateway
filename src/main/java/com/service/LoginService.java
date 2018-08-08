package com.service;

import com.dao.UserRepository;
import com.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class LoginService {
    @Autowired
    UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public List<User> findUser(){
        logger.info("查找全部");
        return userRepository.findAll();
    }
    public String getPasswd(String name){
        logger.info("验证用户名密码");

        User user=userRepository.findByName(name);
        if (user==null){
            return "用户名不存在";
        }else{
            String passwd=user.getPassword();
            return passwd;
        }
    }
//    public Map<String, String> login(User user){
//
//        Map<String, String> tokenMap = new HashMap<>();
//        if(userRepository.existsById(user.getId())){
//            tokenMap.put("username",user.getName());
//            tokenMap.put("password",user.getPassword());
//        }
//        return tokenMap;
//    }

}
