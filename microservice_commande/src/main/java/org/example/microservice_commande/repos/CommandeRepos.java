package org.example.microservice_commande.repos;

import org.example.microservice_commande.Models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepos  extends JpaRepository<Commande , Long> {
}
