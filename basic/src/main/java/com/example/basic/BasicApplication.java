package com.example.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BasicApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(
      SpringApplicationBuilder application) {
    return application.sources(BasicApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(BasicApplication.class, args);
  }

}
