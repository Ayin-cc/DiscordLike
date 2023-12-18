package com.discordLike.controller;


import com.discordLike.entity.User;
import com.discordLike.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    // 登录接口
    @RequestMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        int id;
        try{
            // 尝试检查用户是否已存在
            id = userService.checkUser(user);
        }catch (Exception e){
            id = -1;
        }
        if(id == -1){
            // 用户不存在
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if(userService.login(id, user.getPasswd())){
            // 登录成功
            User ret = userService.getUser(id);
            log.info("【UserController】用户" + id + "登录成功");
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }
        else{
            // 密码错误
            log.error("【UserController】用户" + id + "登录时密码错误");
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    // 获取验证码接口
    @RequestMapping("/getAuth")
    public ResponseEntity<Integer> getAuth(@RequestParam("email")String email){
        log.info("【UserController】已向邮箱" + email + "发送验证码");
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
            log.info("【UserController】用户" + id + "注册成功");
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
    }

    // 删除账号接口
    @RequestMapping("/delete")
    public ResponseEntity<Integer> delete(@RequestBody User user){
        if(userService.login(user.getId(), user.getPasswd())) {
            userService.delete(user.getId());
            log.info("【UserController】用户" + user.getId() + "注销账号");
            return new ResponseEntity<>(200, HttpStatus.OK);
        }
        log.error("【UserController】用户" + user.getId() + "试图注销账号但是密码不正确");
        return new ResponseEntity<>(400, HttpStatus.BAD_REQUEST);
    }
}
