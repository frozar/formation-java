package com.example.injection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) throws Exception {
    ApplicationContext context = SpringApplication.run(Application.class, args);

    Article article = context.getBean(Article.class);
//    article.setNom("livre");
    System.out.println("Mon message " + article.getNom());

    Transport transport = context.getBean(Transport.class);
//    transport.setNom("voiture");
    System.out.println(transport.getNom());

//    Transport t = new Transport("mon transport");

  }

  @Bean
  public String beanExample() {
    return new String("TOTO");
  }

//  @Bean("string2")
//  public String beanExample2() {
//    return new String("TATA");
//  }

}
