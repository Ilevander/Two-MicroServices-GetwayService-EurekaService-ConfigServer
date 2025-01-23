package org.example.microservice_produit.web;

import org.example.microservice_produit.models.Produit;
import org.example.microservice_produit.repos.ProduitRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProduitRepo produitRepo;
    public ProductController(ProduitRepo produitRepo) {
        this.produitRepo = produitRepo;
    }
    @GetMapping
    public List<Produit> findAll() {
        return produitRepo.findAll();
    }


    @GetMapping("/{id}")

    public Optional<Produit> findById(@PathVariable Long id) {
        return produitRepo.findById(id);
    }
}
