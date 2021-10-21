package com.hgcw.fmmall.controller;

import com.hgcw.fmmall.service.IndexImgService;
import com.hgcw.fmmall.vo.ResStatus;
import com.hgcw.fmmall.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hgcw
 * @date 2021/10/2 22:05
 */
@RestController
@RequestMapping("/index")
@Api(value = "商品首页接口",tags = "首页管理")
public class IndexImgController {
    @Autowired
    private IndexImgService indexImgService;

    @GetMapping("/indeximg")
    @ApiOperation("首页轮播图接口")
    public ResultVo listIndexImgs(){
        ResultVo resultVo = indexImgService.listIndexImgs();
        return resultVo;
    }
}
