package com.foodify.foodify.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig {

    @Bean
    public Cloudinary getCloudinary(){
        Map config=new HashMap();
        config.put("cloud_name","dvq49leux");
        config.put("api_key","542667191369471");
        config.put("api_secret","lCQY-w_XKgoAuWxRDArqVilYQ5U");
        config.put("secure","True");
        return new Cloudinary(config);
    }
}
