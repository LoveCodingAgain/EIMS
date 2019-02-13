package com.lx.eims;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.lx.eims.mapper")
public class EimsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EimsApplication.class, args);
    }

}

