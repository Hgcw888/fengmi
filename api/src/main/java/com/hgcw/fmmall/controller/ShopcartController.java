package com.hgcw.fmmall.controller;

import com.hgcw.fmmall.utils.Base64Utils;
import com.hgcw.fmmall.vo.ResStatus;
import com.hgcw.fmmall.vo.ResultVo;
import io.jsonwebtoken.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hgcw
 * @date 2021/6/29 15:27
 */
@RestController
@RequestMapping("/shopcart")
@Api(value = "购物车列表的接口", tags = "购物车列表")
public class ShopcartController {

    @ApiOperation("用户购物车接口")
    @ApiImplicitParam(dataType = "string", name = "token", value = "授权令牌", required = true)
    @GetMapping("/list")
    public ResultVo listCarts(String token) {
      return  new ResultVo(ResStatus.OK, "success", null);

    }
}
