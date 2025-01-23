package org.example.microservice_produit;

import org.example.microservice_produit.models.Produit;
import org.example.microservice_produit.repos.ProduitRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MicroserviceProduitApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceProduitApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ProduitRepo produitRepo) {
        return args -> {

            produitRepo.saveAll(
                    List.of(
                            new Produit(null, "Fruits"),
                            new Produit(null, "Légumes")
                    )
            );
        };
    }
}
