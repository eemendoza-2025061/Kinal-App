package com.eliasmendoza.Kinalapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;

    public WebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                // Rutas protegidas:
                .addPathPatterns("/web/**", "/clientes/**", "/productos/**",
                                 "/usuarios/**", "/ventas/**", "/detalleventa/**")
                // Rutas públicas (excluidas):
                .excludePathPatterns("/login", "/registro", "/css/**", "/js/**");
    }
}
