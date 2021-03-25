package cn.edu.fzu.papermanage.controller;

import cn.edu.fzu.papermanage.common.Result;
import cn.edu.fzu.papermanage.entity.User;
import cn.edu.fzu.papermanage.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param user the user
     * @return the result
     */
    @PostMapping
    public Result register(@RequestBody User user) {
        User existedUser = userService.findUserByAccount(user.getAccount());
        Result result;
        if (existedUser == null) {
            userService.save(user);
            result = Result.success();
        }else {
            result = Result.error("-1","账号已存在");
        }
        return result;
    }

    /**
     * 用户登录
     *
     * @param account  the account
     * @param password the password
     * @return the result
     */
    @GetMapping
    public Result login(@RequestParam String account,@RequestParam String password) {
        Result result;
        User loginUser = userService.findUserByAccount(account);
        if (loginUser == null) {
            result = Result.error("-1","账号未注册");
        }else {
            if (password.equals(loginUser.getPassword())) {
                result = Result.success();
            }else {
                result = Result.error("-1","密码错误");
            }
        }
        return result;
    }
}
