package com.pairproject.papercv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.pairproject.papercv.dao")
@SpringBootApplication
public class PaperCVApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaperCVApplication.class, args);
    }

}
