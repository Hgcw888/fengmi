package com.hgcw.fmmall.config;

import com.hgcw.fmmall.interceptor.CheckTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hgcw
 * @date 2021/6/29 17:45
 * 接口拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private CheckTokenInterceptor checkTokenInterceptor;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(checkTokenInterceptor)
//                .addPathPatterns("/shopcart/**")
//                .addPathPatterns("/orders/**")//拦截
//                .excludePathPatterns("/users/**");//放开
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注入bean需要一个实现HandlerInterceptor接口的拦截器实例
        registry.addInterceptor(checkTokenInterceptor)
                //用于设置拦截器的过滤路径规则；
                .addPathPatterns("/shopcart/**")
                //用于设置拦截器的过滤路径规则；
                .addPathPatterns("/orders/**")
                //用于设置不需要拦截的过滤规则
                .excludePathPatterns("/user/**");
}}