package com.example.injection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class InjectionApplication {

  public static void main(String[] args) throws Exception {
    ApplicationContext context = SpringApplication
        .run(InjectionApplication.class, args);

    StationServiceInterface stationService = context
        .getBean(StationServiceInterface.class);

    GarageInterface garageRoue = context.getBean("roue", GarageInterface.class);
    GarageInterface garageComplete = context.getBean("complete",
        GarageInterface.class);

//    Voiture maVoiture = new Voiture();
//    System.out.println("Au départ");
//    System.out.println(maVoiture);
//    System.out.println("");
//
//    maVoiture.roule(10);
//    System.out.println("Après 10 km");
//    System.out.println(maVoiture);
//    System.out.println("");
//
//    maVoiture.roule(50);
//    System.out.println("50 km plus tard");
//    System.out.println(maVoiture);
//    System.out.println("");
//
//    stationService.faireLePlein(maVoiture);
//    System.out.println("Après avoir fait le plein");
//    System.out.println(maVoiture);
//    System.out.println("\n\n");
//
//    /***********************/
//
//    GrosLoto monLoto = new GrosLoto();
//    System.out.println("Au départ");
//    System.out.println(monLoto);
//    System.out.println("");
//
//    monLoto.roule(80);
//    System.out.println("80 km plus tard");
//    System.out.println(monLoto);
//    System.out.println("");
//
//    stationService.faireLePlein(monLoto);
//    System.out.println("Après avoir fait le plein");
//    System.out.println(monLoto);
//    System.out.println("\n\n");
//
//    /***********************/
//
//    Voiture maVoiture2 = new Voiture();
//    System.out.println("Au départ");
//    System.out.println(maVoiture2);
//    System.out.println("");
//
//    maVoiture2.roule(10);
//    maVoiture2.roule(1);
//    maVoiture2.roule(1);
//    maVoiture2.roule(1);
//    System.out.println("Roule 4 fois");
//    System.out.println(maVoiture2);
//    garageRoue.entretien(maVoiture2);
//    maVoiture2.roule(1);
//    System.out.println("Roule 1 fois");
//    System.out.println(maVoiture2);
//    System.out.println("\n\n");
//
//    /***********************/

    GrosLoto monLoto2 = new GrosLoto();
//    System.out.println("Au départ");
//    System.out.println(monLoto2);
//    System.out.println("");
//
//    monLoto2.roule(10);
//    monLoto2.roule(1);
//    monLoto2.roule(1);
//    monLoto2.roule(1);
//    System.out.println("Roule 4 fois");
//    System.out.println(monLoto2);
//    try {
//      monLoto2.roule(1);
//    } catch (Exception e) {
//      System.out.println(e.getMessage());
//      garageRoue.entretien(monLoto2);
//    }
//
//    monLoto2.roule(1);
//    System.out.println("Roule 1 fois");
//    System.out.println(monLoto2);
//    System.out.println("\n\n");
//
//    /***********************/

    garageRoue.entretien(monLoto2);
    stationService.faireLePlein(monLoto2);
    System.out.println("Rechargé mon Loto 2");
    System.out.println(monLoto2);
    monLoto2.roule(20);
    monLoto2.roule(20);
    monLoto2.roule(20);
    monLoto2.roule(20);
    System.out.println("Roule 4 fois");
    System.out.println(monLoto2);
    garageComplete.entretien(monLoto2);
    System.out.println("Après garage complet");
    System.out.println(monLoto2);

  }

}
