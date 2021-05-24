package com.hgcw.fmmall.service.imp;

import com.hgcw.fmmall.dao.UsersMapper;
import com.hgcw.fmmall.entity.Users;
import com.hgcw.fmmall.service.UsersService;
import com.hgcw.fmmall.utils.MD5Utils;
import com.hgcw.fmmall.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author hgcw
 * @date 2021/5/17 22:43
 */
@Service
@Scope("singleton")
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;


    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ResultVo checkLogin(String username, String password) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        List<Users> users = usersMapper.selectByExample(example);
        if (users.size() == 0) {
            return new ResultVo(10001, "用户名不存在或密码错误", null);
        } else {
            //对输入的密码加密
            String md5 = MD5Utils.md5(password);
            //查出来的密码与输入的对比
            if (md5.equals(users.get(0).getPassword())) {
                //验证成功
                return new ResultVo(10000, "登陆成功", users.get(0));
            } else {
                //密码错误
                return new ResultVo(10001, "用户名不存在或密码错误", null);
            }
        }
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    @Transactional
    public ResultVo usersResgit(String username, String password) {
        synchronized (this) {
            Example example = new Example(Users.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("username", username);
            List<Users> users = usersMapper.selectByExample(example);
            if (users.size() == 0) {
                Users users1 = new Users();
                users1.setUsername(username);
                users1.setPassword(MD5Utils.md5(password));
                users1.setUserImg("https://www.veer.com/photo/143241383");
                users1.setUserRegtime(new Date());
                users1.setUserModtime(new Date());
                int i = usersMapper.insert(users1);
                if (i > 0) {
                    return new ResultVo(10000, "注册成功", null);
                } else {
                    return new ResultVo(10002, "注册失败", null);
                }
            } else {
                return new ResultVo(10001, "用户已存在", null);
            }
        }
    }
}
