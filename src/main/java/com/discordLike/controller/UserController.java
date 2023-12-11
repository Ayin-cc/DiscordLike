package com.discordLike.controller;


import com.discordLike.entity.User;
import com.discordLike.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    // 登录接口
    @RequestMapping("/login")
    public ResponseEntity<Integer> login(@RequestBody User user){
        int id;
        try{
            // 尝试检查用户是否已存在
            id = userService.checkUser(user);
        }catch (Exception e){
            id = -1;
        }
        if(id == -1){
            // 用户不存在
            return new ResponseEntity<>(404, HttpStatus.NOT_FOUND);
        }
        if(userService.login(id, user.getPasswd())){
            // 登录成功
            return new ResponseEntity<>(200, HttpStatus.OK);
        }
        else{
            // 密码错误
            return new ResponseEntity<>(401, HttpStatus.UNAUTHORIZED);
        }
    }

    // 获取验证码接口
    @RequestMapping("/getAuth")
    public ResponseEntity<Integer> getAuth(@RequestParam("email")String email){

        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    // 注册接口
    @RequestMapping("/register")
    public ResponseEntity<Integer> register(@RequestParam("authCode")String authCode, @RequestBody User user){
        try{
            // 尝试检查用户是否已存在
            userService.checkUser(user);
            return new ResponseEntity<>(-1, HttpStatus.CONFLICT);
        }catch (Exception e){
            if(!userService.checkAuth(authCode)){
                // 验证码错误
                return new ResponseEntity<>(-1, HttpStatus.UNAUTHORIZED);
            }

            int id = userService.register(user);
            if(id == -1){
                // 请求异常
                return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

    }
}
