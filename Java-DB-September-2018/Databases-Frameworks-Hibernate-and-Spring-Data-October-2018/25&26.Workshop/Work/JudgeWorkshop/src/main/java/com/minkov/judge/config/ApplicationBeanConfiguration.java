package com.minkov.judge.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.minkov.judge.util.FileUtil;
import com.minkov.judge.util.FileUtilImpl;
import com.minkov.judge.util.ValidationUtil;
import com.minkov.judge.util.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public FileUtil fileUtil() {
        return new FileUtilImpl();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
