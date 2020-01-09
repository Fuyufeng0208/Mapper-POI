package com.aspirecn.hutooldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Fuyufeng
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.aspirecn.hutooldemo.dao"})
public class HutooldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HutooldemoApplication.class, args);
    }

}
