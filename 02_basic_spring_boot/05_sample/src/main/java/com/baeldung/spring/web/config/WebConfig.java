package com.baeldung.spring.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = { "com.baeldung.web.controller" })
@Configuration
public class WebConfig implements WebMvcConfigurer {
//
  @Override
  public void addViewControllers(final ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index");
  }

  @Bean
  public ViewResolver viewResolver() {
    final InternalResourceViewResolver bean = new InternalResourceViewResolver();
    bean.setViewClass(JstlView.class);
    bean.setPrefix("/WEB-INF/view/");
    bean.setSuffix(".jsp");
    bean.setOrder(2);
    return bean;
  }

  @Bean
  public BeanNameViewResolver beanNameViewResolver() {
    BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
    beanNameViewResolver.setOrder(1);
    return beanNameViewResolver;
  }

  @Bean
  public View sample() {
    return new JstlView("/WEB-INF/view/sample.jsp");
  }

  @Bean
  public View sample2() {
    return new JstlView("/WEB-INF/view2/sample2.jsp");
  }

  @Bean
  public View sample3() {
    return new JstlView("/WEB-INF/view3/sample3.jsp");
  }
}