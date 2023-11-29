package com.makiti.test.modules.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@CrossOrigin
@Configuration
public class WebMvcConfigurerConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins(
      "http://localhost:3000",
      "http://127.0.0.1:3000"
    ).allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE");
  }

}
