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

  @Bean
  public FlowExecutor flowExecutor() {
    return getFlowExecutorBuilder(this.flowRegistry()) //
        .build();
  }

  @Bean
  public FlowDefinitionRegistry flowRegistry() {
    return getFlowDefinitionRegistryBuilder(flowBuilderServices())
        .addFlowLocation("/WEB-INF/register/signup-flow.xml", "signupFlow")
        .build();
  }

  @Bean
  public FlowBuilderServices flowBuilderServices() {
    return getFlowBuilderServicesBuilder()
        .setViewFactoryCreator(this.mvcViewFactoryCreator()) // Important!
        .setValidator(this.localValidatorFactoryBean) //
        .setDevelopmentMode(true).build();
  }

  // ----------------------------------------------------------

  @Bean
  public MvcViewFactoryCreator mvcViewFactoryCreator() {
    MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
    factoryCreator.setViewResolvers(
        Collections.singletonList(this.webMvcConfig.viewResolver()));
    factoryCreator.setUseSpringBeanBinding(true);
    return factoryCreator;
  }

}
