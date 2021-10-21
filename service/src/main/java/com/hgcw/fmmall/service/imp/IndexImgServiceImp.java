package com.hgcw.fmmall.service.imp;

import com.hgcw.fmmall.dao.IndexImgMapper;
import com.hgcw.fmmall.entity.IndexImg;
import com.hgcw.fmmall.service.IndexImgService;
import com.hgcw.fmmall.vo.ResStatus;
import com.hgcw.fmmall.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hgcw
 * @date 2021/10/2 21:57
 */
@Service
public class IndexImgServiceImp implements IndexImgService {
    @Autowired
    private IndexImgMapper indexImgMapper;

    /**
     * 查询商品首页轮播图信息
     *
     * @return
     */
    public ResultVo listIndexImgs() {
        List<IndexImg> indexImgs = indexImgMapper.listIndexImgs();
        if (indexImgs.size() == 0) {
            return new ResultVo(ResStatus.NO, "fail", null);
        } else {
            return new ResultVo(ResStatus.OK, "success", indexImgs);
        }
    }
}