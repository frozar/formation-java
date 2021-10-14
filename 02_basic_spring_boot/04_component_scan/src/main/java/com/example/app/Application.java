package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.example.app.subpackage.Chat;
import com.externe.Other;

@SpringBootApplication
// Scan package ouside of the current package "com.example.app"
@ComponentScan(basePackages = { "com.example.app", "com.externe" })
public class Application {

  public static void main(String[] args) throws Exception {
    ApplicationContext context = SpringApplication.run(Application.class, args);

    Personne p = context.getBean(Personne.class);
    Chat c = context.getBean(Chat.class);
    Other o = context.getBean(Other.class);
  }

}
