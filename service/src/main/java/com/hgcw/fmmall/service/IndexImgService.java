package com.hgcw.fmmall.service;

import com.hgcw.fmmall.vo.ResultVo;
import org.springframework.stereotype.Service;

/**
 * @author hgcw
 * @date 2021/10/2 21:51
 */
public interface IndexImgService {

    /**
     * 商品首页轮播图查询
     *
     */
    public ResultVo listIndexImgs();

}
