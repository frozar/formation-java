package com.example.demo.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;

@Configuration
//public class WebFlowWithMvcConfig extends AbstractFlowConfiguration {
public class WebFlowConfig extends AbstractFlowConfiguration {

  @Autowired
  private LocalValidatorFactoryBean localValidatorFactoryBean;

  @Autowired
  private WebMvcConfig webMvcConfig;

//  @Bean
//  public FlowDefinitionRegistry flowRegistry() {
//    return getFlowDefinitionRegistryBuilder() //
//        .setBasePath("classpath:flows") //
//        .addFlowLocationPattern("/**/*-flow.xml") //
//        .setFlowBuilderServices(this.flowBuilderServices()) //
//        .build();
//  }

  @Bean
  public FlowExecutor flowExecutor() {
    return getFlowExecutorBuilder(this.flowRegistry()) //
        .build();
  }

  @Bean
  public FlowDefinitionRegistry flowRegistry() {
    return getFlowDefinitionRegistryBuilder(flowBuilderServices())
//        .addFlowLocation("/WEB-INF/flows/signup-flow.xml", "signupFlow")
        .addFlowLocation("/WEB-INF/register/signup-flow.xml", "signupFlow")
        .build();
  }

//  @Bean
//  public FlowBuilderServices flowBuilderServices() {
//    return getFlowBuilderServicesBuilder() //
//        .setViewFactoryCreator(this.mvcViewFactoryCreator()) // Important!
//        .setValidator(this.localValidatorFactoryBean).build();
//  }

  @Bean
  public FlowBuilderServices flowBuilderServices() {
    return getFlowBuilderServicesBuilder()
//        .setViewFactoryCreator(mvcViewFactoryCreator())
        .setViewFactoryCreator(this.mvcViewFactoryCreator()) // Important!
        .setValidator(this.localValidatorFactoryBean) //
        .setDevelopmentMode(true).build();
  }

  // ----------------------------------------------------------

//  @Bean
//  public FlowHandlerMapping flowHandlerMapping() {
//    FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
//    handlerMapping.setOrder(-1);
//    handlerMapping.setFlowRegistry(this.flowRegistry());
//    return handlerMapping;
//  }
//
//  @Bean
//  public FlowHandlerAdapter flowHandlerAdapter() {
//    FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
//    handlerAdapter.setFlowExecutor(this.flowExecutor());
//    handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
//    return handlerAdapter;
//  }
//
//  @Bean
//  public ViewFactoryCreator mvcViewFactoryCreator() {
//    MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
////		factoryCreator.setViewResolvers(Collections.singletonList(this.thymeleafViewResolver()));
//
////    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
////    viewResolver.setPrefix("/WEB-INF/views/");
////    viewResolver.setSuffix(".jsp");
////    factoryCreator.setViewResolvers(Collections.singletonList(viewResolver));
//
//    factoryCreator
//        .setViewResolvers(Collections.singletonList(this.viewResolver()));
//
//    factoryCreator.setUseSpringBeanBinding(true);
//    return factoryCreator; 
//  }

//  @Bean
//  public ViewResolver viewResolver() {
//    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//    viewResolver.setPrefix("/WEB-INF/views/");
//    viewResolver.setSuffix(".jsp");
//    return viewResolver;
//  }

  @Bean
  public MvcViewFactoryCreator mvcViewFactoryCreator() {
    MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
    factoryCreator.setViewResolvers(
        Collections.singletonList(this.webMvcConfig.viewResolver()));
    factoryCreator.setUseSpringBeanBinding(true);
    return factoryCreator;
  }

//  @Bean
//  @Description("Thymeleaf AJAX view resolver for Spring WebFlow")
//  public AjaxThymeleafViewResolver thymeleafViewResolver() {
//    AjaxThymeleafViewResolver viewResolver = new AjaxThymeleafViewResolver();
//    viewResolver.setViewClass(FlowAjaxThymeleafView.class);
//    viewResolver.setTemplateEngine(this.templateEngine());
//    viewResolver.setCharacterEncoding("UTF-8");
//    return viewResolver;
//  }
//
//  @Bean
//  @Description("Thymeleaf template resolver serving HTML 5")
//  public ClassLoaderTemplateResolver templateResolver() {
//    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//    templateResolver.setPrefix("templates/");
//    templateResolver.setCacheable(false);
//    templateResolver.setSuffix(".html");
//    templateResolver.setTemplateMode("HTML5");
//    templateResolver.setCharacterEncoding("UTF-8");
//    return templateResolver;
//  }

//  @Bean
//  @Description("Thymeleaf template engine with Spring integration")
//  public SpringTemplateEngine templateEngine() {
//    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//    templateEngine.setTemplateResolver(this.templateResolver());
//    return templateEngine;
//  }
}
