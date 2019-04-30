package org.softuni.university.config;

import org.softuni.university.web.interceptors.ImageInterceptor;
import org.softuni.university.web.interceptors.IconInterceptor;
import org.softuni.university.web.interceptors.TitleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationInterceptorConfiguration implements WebMvcConfigurer {

    private final IconInterceptor iconInterceptor;
    private final TitleInterceptor titleInterceptor;
    private final ImageInterceptor imageInterceptor;

    public ApplicationInterceptorConfiguration(IconInterceptor iconInterceptor, TitleInterceptor titleInterceptor, ImageInterceptor imageInterceptor) {
        this.iconInterceptor = iconInterceptor;
        this.titleInterceptor = titleInterceptor;
        this.imageInterceptor = imageInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.iconInterceptor);
        registry.addInterceptor(this.titleInterceptor);
        registry.addInterceptor(this.imageInterceptor);
    }
}