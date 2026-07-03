package com.railwayguide.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 关键：不要 setAllowCredentials(true)！保持默认 false 才能用 *
        config.addAllowedOriginPattern("*");   // 允许所有域名
        config.addAllowedHeader("*");          // 允许所有头
        config.addAllowedMethod("*");          // 允许所有方法(GET/POST/PUT/DELETE/OPTIONS/PATCH)
        config.setMaxAge(3600L);               // 预检缓存时间

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}