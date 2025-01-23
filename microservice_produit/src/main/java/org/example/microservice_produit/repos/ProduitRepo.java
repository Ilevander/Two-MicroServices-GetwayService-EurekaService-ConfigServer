package org.example.microservice_produit.repos;

import org.example.microservice_produit.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepo extends JpaRepository<Produit, Long> {
}
