package com.rokwonkk.springcoredemo.config;

import com.rokwonkk.springcoredemo.common.Coach;
import com.rokwonkk.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
