package com.hgcw.fmmall.service;

import com.hgcw.fmmall.vo.ResultVo;
import org.springframework.stereotype.Service;

/**
 * @author hgcw
 * @date 2021/5/17 22:42
 */
@Service
public interface UsersService {

    ResultVo checkLogin(String username, String password);

    ResultVo usersResgit(String username, String password);

}
