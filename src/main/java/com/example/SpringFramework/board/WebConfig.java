package com.example.SpringFramework.board;

import com.example.SpringFramework.board.web.argumentresolver.LoginMemberArgumentResolver;
import com.example.SpringFramework.board.web.interceptor.LogInterceptor;
import com.example.SpringFramework.board.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)

                // /*여기 하위는 전부다
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");


        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/", "/members/add", "/login", "/logout",
                        "/css/**", "/*.ico", "/error", "/members/idCheck"
                        );
    }
}
