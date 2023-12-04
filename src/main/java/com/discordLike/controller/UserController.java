package com.discordLike.controller;


import com.discordLike.entity.User;
import com.discordLike.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    // 登录接口
    @RequestMapping("/login")
    public ResponseEntity<Integer> login(@RequestBody User user){
        int id = userService.checkUser(user);
        if(id == 0){
            return new ResponseEntity<>(404, HttpStatus.NOT_FOUND);
        }
        if(userService.login(id, user.getPasswd())){
            return new ResponseEntity<>(200, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(401, HttpStatus.BAD_REQUEST);
        }
    }

    // 获取验证码接口
    @RequestMapping("/getAuth")
    public ResponseEntity<Integer> getAuth(@RequestParam("email")String email){

        return new ResponseEntity<>(400, HttpStatus.OK);
    }

    // 注册接口
    @RequestMapping("/register")
    public ResponseEntity<Integer> register(@RequestParam("authCode")String authCode, @RequestBody User user){

        return new ResponseEntity<>(400, HttpStatus.OK);
    }
}
