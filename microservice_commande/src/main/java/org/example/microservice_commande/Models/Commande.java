package org.example.microservice_commande.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity

public class Commande {

    //[id,
    //description, quantité, date, montant
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Long id;
    private String description;
    private  int quantite;
    private LocalDate date;
    private  Double montant;
    private  Long productId;
    public Commande(Long id, String description, int quantite, LocalDate date, Double montant, Long productId) {
        this.id = id;
        this.description = description;
        this.quantite = quantite;
        this.date = date;
        this.montant = montant;
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Commande() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantite() {
        return quantite;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
}
