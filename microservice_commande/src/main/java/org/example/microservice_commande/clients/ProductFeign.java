package org.example.microservice_commande.clients;

import org.example.microservice_commande.Models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-produit")  // Nom du service enregistré dans Eureka

public interface ProductFeign {

    @GetMapping("/api/products/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
