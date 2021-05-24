package com.hgcw.fmmall.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hgcw
 * @date 2021/5/19 19:46
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket getDocket(){
        //创建接口文档封面信息
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("锋迷商城接口文档说明")
                .description("此文档详细说明了锋迷商城项目祸端接口说明")
                .version("v 2.0.1")
                .contact(new Contact("hgcw","xxxx","1727446565@qq.com"));
        ApiInfo apiInfo = apiInfoBuilder.build();

        //文档信息
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hgcw.fmmall.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


}
