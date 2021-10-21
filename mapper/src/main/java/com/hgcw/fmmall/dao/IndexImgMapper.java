package com.hgcw.fmmall.dao;

import com.hgcw.fmmall.entity.IndexImg;
import com.hgcw.fmmall.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexImgMapper extends GeneralDAO<IndexImg> {
    /**
     * 查询商品首页轮播图
     * @return
     */
      List<IndexImg> listIndexImgs();
}