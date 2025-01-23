package org.example.microservice_commande.Models;


import java.time.LocalDate;


public class CommandeDTO {
    private Long id;
    private String description;
    private int quantite;
    private LocalDate date;
    private Double montant;
    private Product product; // Détails du produit associé

    public CommandeDTO(Long id, String description, int quantite, LocalDate date, Double montant, Product product) {
        this.id = id;
        this.description = description;
        this.quantite = quantite;
        this.date = date;
        this.montant = montant;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
