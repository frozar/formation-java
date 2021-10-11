package com.example.injection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class InjectionApplication {

//  @Qualifier("complete")
  public static void main(String[] args) throws Exception {
    ApplicationContext context = SpringApplication
        .run(InjectionApplication.class, args);

//    StationServiceInterface stationService = context
//        .getBean(StationServiceInterface.class);

//    StationServiceInterface stationService = BeanFactoryAnnotationUtils
//        .qualifiedBeanOfType(context.getParentBeanFactory(),
//            StationServiceInterface.class, "complete");

    StationServiceInterface stationService = context.getBean("complete",
        StationServiceInterface.class);

    Voiture maVoiture = new Voiture();
    System.out.println("Au départ");
    System.out.println(maVoiture);
    System.out.println("");

    maVoiture.roule(10);
    System.out.println("Après 10 km");
    System.out.println(maVoiture);
    System.out.println("");

    maVoiture.roule(50);
    System.out.println("50 km plus tard");
    System.out.println(maVoiture);
    System.out.println("");

    stationService.faireLePlein(maVoiture);
    System.out.println("Après avoir fait le plein");
    System.out.println(maVoiture);
  }

}
