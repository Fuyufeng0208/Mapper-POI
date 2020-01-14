package com.aspirecn.hutooldemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description pring Security 的配置类
 * @Author Fuyufeng
 * @Date 2020/1/14 16:00
 * @since JDK 1.8
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //下面这两行配置表示在内存中配置了两个用户
        auth.inMemoryAuthentication()
                .withUser("fuyufeng").roles("admin").password("$2a$10$fTGzAy3.yU62flmD2dLjK.Gdp.aDQUE8ER0k1ie/TNAmEVT3hBxHi")
                .and()
                .withUser("shanshan").roles("user").password("$2a$10$fTGzAy3.yU62flmD2dLjK.Gdp.aDQUE8ER0k1ie/TNAmEVT3hBxHi");
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
