# 用户

**根路径：`/User`**

- 登录：`/login`

  - ```json
    {
        "userName": "用户名",
        "userPasswd": "密码"
    }
    ```
    
  - ```json
    {
        "code": 200	 // 状态码
    }
    ```

- 获取验证码：`/getAuth`

  - ```json
    {
        "userName": "用户名",
        "email": "邮箱地址"
    }
    ```

  - ```json
    {
        "code": 200	 // 状态码
    }
    ```

- 注册：`/register`

  - ```json
    {
        "userName": "用户名",
        "email": "邮箱地址",
        "authCode": "验证码",
        "passwd": "密码"
    }
    ```

  - ```json
    {
        "code": 200	 // 状态码
    }
    ```

- 



# 服务器







# 频道







# 消息

