package com.hgcw.fmmall.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hgcw.fmmall.vo.ResStatus;
import com.hgcw.fmmall.vo.ResultVo;
import io.jsonwebtoken.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author hgcw
 * @date 2021/6/29 17:44
 * token拦截器
 * 注入spring的bean中
 */
@Component
public class CheckTokenInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //放行option请求
        String method = request.getMethod();
        if ("OPTIONS".equalsIgnoreCase(method)) {
            return true;
            
        }
        //获取http中的token
        String token = request.getParameter("token");
        //判断是token是否为空
        if(token == null){
            ResultVo resultVo = new ResultVo(ResStatus.NO, "请先登录！", null);
            //提示请先登录
            doResponse(response,resultVo);
        }else{
            try {
                //验证token
                JwtParser parser = Jwts.parser();
                //解析token的SigningKey必须和⽣成token时设置密码⼀致
                parser.setSigningKey("QIANfeng6666");
                //如果token正确（密码正确，有效期内）则正常执⾏，否则抛出异常
                Jws<Claims> claimsJws = parser.parseClaimsJws(token);
                return true;
            }catch (ExpiredJwtException e){
                ResultVo resultVo = new ResultVo(ResStatus.NO, "登录过期，请重新登录！",
                        null);
                doResponse(response,resultVo);
            }catch (UnsupportedJwtException e){
                ResultVo resultVo = new ResultVo(ResStatus.NO, "Token不合法，请⾃重！",
                        null);
                doResponse(response,resultVo);
            }catch (Exception e){
                ResultVo resultVo = new ResultVo(ResStatus.NO, "请先登录！", null);
                doResponse(response,resultVo);
            }
        }
        return false;
    }

    /**
     * 调用都Response方法后去返回结果
     * @param response
     * @param resultVo
     * @throws IOException
     */
    private void doResponse(HttpServletResponse response,ResultVo resultVo) throws
            IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String s = new ObjectMapper().writeValueAsString(resultVo);
        out.print(s);
        out.flush();
        out.close();
    }

}
