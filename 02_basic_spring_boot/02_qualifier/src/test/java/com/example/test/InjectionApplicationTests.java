package com.example.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.injection.DemiStationService;
import com.example.injection.StationService;
import com.example.injection.StationServiceInterface;
import com.example.injection.Voiture;

@SpringBootTest(classes = { Voiture.class, StationServiceInterface.class,
    StationService.class, DemiStationService.class })
class InjectionApplicationTests {

  @Autowired
  @Qualifier("complete")
  StationServiceInterface stationService;

  @Test
  void pleinComplet() throws Exception {
    Voiture voitureTest = new Voiture();
    voitureTest.roule(60);

    assertThat(voitureTest.getCaburant()).isEqualTo(0);
    stationService.faireLePlein(voitureTest);
    assertThat(voitureTest.getCaburant()).isEqualTo(60);
  }

  @Test
  void pleinDemi(
      @Autowired @Qualifier("demi") StationServiceInterface demiStationService)
      throws Exception {
    Voiture voitureTest = new Voiture();
    voitureTest.roule(60);

    assertThat(voitureTest.getCaburant()).isEqualTo(0);
    demiStationService.faireLePlein(voitureTest);
    assertThat(voitureTest.getCaburant()).isEqualTo(30);
  }

}
