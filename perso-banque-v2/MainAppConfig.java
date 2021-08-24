package fr.gouv.finances.dgfip.banque.v1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "fr.gouv.finances.dgfip.banque.v1")
public class MainAppConfig extends AnnotationConfigWebApplicationContext {

}
