package com.hgcw.fmmall.controller;

import com.hgcw.fmmall.entity.Users;
import com.hgcw.fmmall.service.UsersService;
import com.hgcw.fmmall.vo.ResultVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author hgcw
 * @date 2021/5/18 14:41
 */
@RestController
@RequestMapping("/users")
//`@Api` 类注解，在控制器类添加此注解，可以对控制器类进行功能说明
@Api(value = "用户管理的接口", tags = "用户管理")
////跨域请求
//@CrossOrigin
public class UsersController {
    @Autowired
    private UsersService usersService;

    /**
     * 登录
     *
     * @return `@ApiOperation`方法注解：说明接口方法的作用
     * `@ApiImplicitParams`和`@ApiImplicitParam` 方法注解，说名接口方法的参数
     */
    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "username", value = "用户登录账号", required = true),
            @ApiImplicitParam(dataType = "String", name = "password", value = "用户登录密码", required = true),
    })
    @GetMapping("/login")
    public ResultVo login(@RequestParam("username") String username, @RequestParam("password") String password) {
        ResultVo resultVo = usersService.checkLogin(username, password);
        return resultVo;
    }

    /**
     * 注册
     *
     * @return
     */
    @ApiOperation("用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "username", value = "用户注册账号", required = true),
            @ApiImplicitParam(dataType = "String", name = "password", value = "用户注册密码", required = true),
    })
    @PostMapping("/resgit")
    public ResultVo resgit(@RequestBody Users users) {
        ResultVo resultVo = usersService.usersResgit(users.getUsername(), users.getPassword());
        return resultVo;
    }

//    /**
//     * 注册
//     *
//     * @return
//     */
//    @ApiOperation("用户注册接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(dataType = "String", name = "username", value = "用户注册账号", required = true),
//            @ApiImplicitParam(dataType = "String", name = "password", value = "用户注册密码", required = true),
//    })
//    @PostMapping("/resgit/{username}/{password}")
//    public ResultVo resgit(@PathVariable("username") String username, @PathVariable("password") String password) {
//        ResultVo resultVo = usersService.usersResgit(username, password);
//        return resultVo;
//    }

}
