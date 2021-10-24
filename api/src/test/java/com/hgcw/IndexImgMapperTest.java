package com.hgcw;

import com.hgcw.fmmall.dao.IndexImgMapper;
import com.hgcw.fmmall.entity.IndexImg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author hgcw
 * @date 2021/10/3 21:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexImgMapperTest {
    @Autowired
    private IndexImgMapper indexImgMapper;

    @Test
    public void IndexImg(){
        List<IndexImg> indexImgs = indexImgMapper.listIndexImgs();
        for (IndexImg i:indexImgs
             ) {
            System.out.println(i.toString());
        }
    }

}