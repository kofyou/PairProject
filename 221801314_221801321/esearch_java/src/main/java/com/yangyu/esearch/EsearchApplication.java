package com.yangyu.esearch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan({"com.yangyu.esearch.mapper"})
public class EsearchApplication {

    public static void main(String[] args) {

        SpringApplication.run(EsearchApplication.class, args);
    }

}
