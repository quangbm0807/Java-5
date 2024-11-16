package org.example.ps27852_lab7.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class AuthInterConfig  implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns
                        ("/admin/**","/category/**", "/home/**", "/mailer/**", "/product/**", "/logout","/user/cart")
                .excludePathPatterns("/assets/**","/login");
    }
}
