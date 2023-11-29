package com.makiti.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
public class MakitiTestBackendApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(MakitiTestBackendApplication.class, args);
  }

}
