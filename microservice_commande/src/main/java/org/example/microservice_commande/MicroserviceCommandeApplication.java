package org.example.microservice_commande;

import org.example.microservice_commande.Models.Commande;
import org.example.microservice_commande.repos.CommandeRepos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceCommandeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCommandeApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(CommandeRepos commandeRepos) {
        return args -> {
            commandeRepos.saveAll(
                    List.of(
                            new Commande(null, "Apple", 10, LocalDate.now(), 1.5, 1L),                 // Aujourd'hui
                            new Commande(null, "Banana", 20, LocalDate.now().minusDays(1), 1.0, 2L),   // Hier
                            new Commande(null, "Orange", 15, LocalDate.now().minusDays(2), 2.0, 1L),   // Avant-hier
                            new Commande(null, "Carrot", 25, LocalDate.now().minusDays(3), 0.8, 2L),   // 3 jours avant
                            new Commande(null, "Tomato", 30, LocalDate.now().minusDays(4), 1.2, 1L),   // 4 jours avant
                            new Commande(null, "Potato", 40, LocalDate.now().minusDays(5), 0.5, 2L),   // 5 jours avant
                            new Commande(null, "Onion", 35, LocalDate.now().minusDays(6), 0.6, 1L),    // 6 jours avant
                            new Commande(null, "Lettuce", 12, LocalDate.now().minusDays(7), 1.8, 2L),  // 7 jours avant
                            new Commande(null, "Spinach", 8, LocalDate.now().minusDays(8), 2.5, 1L),   // 8 jours avant
                            new Commande(null, "Bell Pepper", 18, LocalDate.now().minusDays(9), 2.2, 2L)
                    )
            );
        };
    }
}
