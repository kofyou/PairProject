package com.yangyu.esearch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author : yangyu
 * @ClassName : com.yangyu.esearch.swagger.SwaggerConfig
 * @Description : Swagger配置类
 * @date : 2021-03-28 20:53:32
 * Copyright  2021 user. All rights reserved.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("yangyu")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yangyu.esearch.controller"))
                .build();
    }

    //配置Swagger信息
    private ApiInfo apiInfo(){
        Contact contact = new Contact("yangyu","https://www.cnblogs.com/yangyu-huang/",
                "1849588816@qq.com");
        return new ApiInfo(
                "yangyu的SwaggerAPI文档",
                "即使再小的帆也能远航",
                "v1.0",
                "https://www.cnblogs.com/yangyu-huang/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
