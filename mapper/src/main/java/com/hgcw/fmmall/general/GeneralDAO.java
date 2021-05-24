package com.hgcw.fmmall.general;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author hgcw
 * @date 2021/5/22 23:37
 */
public interface GeneralDAO<T> extends Mapper, MySqlMapper<T> {
}
