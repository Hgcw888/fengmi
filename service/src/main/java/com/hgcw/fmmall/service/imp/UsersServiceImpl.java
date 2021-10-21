package com.hgcw.fmmall.service.imp;

import com.hgcw.fmmall.dao.UsersMapper;
import com.hgcw.fmmall.entity.Users;
import com.hgcw.fmmall.service.UsersService;
import com.hgcw.fmmall.utils.Base64Utils;
import com.hgcw.fmmall.utils.MD5Utils;
import com.hgcw.fmmall.vo.ResStatus;
import com.hgcw.fmmall.vo.ResultVo;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
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
            return new ResultVo(ResStatus.NO, "用户名不存在或密码错误", null);
        } else {
            //对输入的密码加密
            String md5 = MD5Utils.md5(password);
            //查出来的密码与输入的对比
            if (md5.equals(users.get(0).getPassword())) {
                //登录成功后引入jwt
                JwtBuilder builder = Jwts.builder();
                //用户权限
                HashMap<String, Object> map = new HashMap<>();
                map.put("key1","values");
                map.put("key2","values");
                //主题，就是token中携带的数据
                String token = builder.setSubject(username)
                        //设置token的⽣成时间
                        .setIssuedAt(new Date())
                        //设置⽤户id为token id
                        .setId(users.get(0).getUserId() + "")
                        //map中可以存放⽤户的⻆⾊权限信息
                        .setClaims(map)
                        //设置token有效时间
                        .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                        //设置加密⽅式和加密密码
                        .signWith(SignatureAlgorithm.HS256, "QIANfeng6666")
                        .compact();
                //验证成功
                return new ResultVo(ResStatus.OK, token, users.get(0));
            } else {
                //密码错误
                return new ResultVo(ResStatus.NO, "用户名不存在或密码错误", null);
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
                    return new ResultVo(ResStatus.OK, "注册成功", null);
                } else {
                    return new ResultVo(ResStatus.FAIL_TO_REGISTER, "注册失败", null);
                }
            } else {
                return new ResultVo(ResStatus.NO, "用户已存在", null);
            }
        }
    }
}
