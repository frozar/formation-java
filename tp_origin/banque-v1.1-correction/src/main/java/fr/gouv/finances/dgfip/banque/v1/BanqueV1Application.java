package fr.gouv.finances.dgfip.banque.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BanqueV1Application {
	public static void main(String[] args) {
		SpringApplication.run(BanqueV1Application.class, args);
		
		// Le scénario de test se trouve dans le composant ScenarioTest
	}
}
