package com.example.injection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

  public static void main(String[] args) throws Exception {
    ApplicationContext context = SpringApplication.run(Application.class, args);

    Article article = context.getBean(Article.class);
    article.setNom("livre");
    System.out.println(article.getNom());

//    Transport transport = context.getBean(Transport.class);
//    transport.setNom("voiture");
//    System.out.println(transport.getNom());
  }

//  @Bean
//  public String beanExample() {
//    return new String();
//  }

}
