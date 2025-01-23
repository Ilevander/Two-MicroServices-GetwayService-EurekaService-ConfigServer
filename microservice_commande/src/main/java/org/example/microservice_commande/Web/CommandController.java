package org.example.microservice_commande.Web;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.microservice_commande.Models.Commande;
import org.example.microservice_commande.Models.CommandeDTO;
import org.example.microservice_commande.Models.Product;
import org.example.microservice_commande.clients.ProductFeign;
import org.example.microservice_commande.repos.CommandeRepos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/commandes")
@RefreshScope
public class CommandController {

    @Value("${mes-config-ms.commandes-last}")
    private int jours;

    private final CommandeRepos commandeRepos;
    private final ProductFeign productFeign;

    public CommandController(CommandeRepos commandeRepos, ProductFeign productFeign) {
        this.commandeRepos = commandeRepos;
        this.productFeign = productFeign;
    }

    @GetMapping
    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetAllCommandes")
    public List<CommandeDTO> getAllCommandes() {
        List<Commande> commandes = commandeRepos.findAll();
        return commandes.stream().map(commande -> {
            Product product = productFeign.getProductById(commande.getProductId());
            return new CommandeDTO(
                    commande.getId(),
                    commande.getDescription(),
                    commande.getQuantite(),
                    commande.getDate(),
                    commande.getMontant(),
                    product
            );
        }).collect(Collectors.toList());
    }

    public List<CommandeDTO> fallbackGetAllCommandes(Throwable throwable) {
        return commandeRepos.findAll().stream().map(commande -> {
            Product defaultProduct = new Product(commande.getProductId(), "Produit inconnu");
            return new CommandeDTO(
                    commande.getId(),
                    commande.getDescription(),
                    commande.getQuantite(),
                    commande.getDate(),
                    commande.getMontant(),
                    defaultProduct
            );
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetCommandeById")
    public ResponseEntity<CommandeDTO> getCommandeById(@PathVariable Long id) {
        Optional<Commande> optionalCommande = commandeRepos.findById(id);
        if (optionalCommande.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Commande commande = optionalCommande.get();
        Product product = productFeign.getProductById(commande.getProductId());
        CommandeDTO commandeDTO = new CommandeDTO(
                commande.getId(),
                commande.getDescription(),
                commande.getQuantite(),
                commande.getDate(),
                commande.getMontant(),
                product
        );
        return ResponseEntity.ok(commandeDTO);
    }

    public ResponseEntity<CommandeDTO> fallbackGetCommandeById(Long id, Throwable throwable) {
        Optional<Commande> optionalCommande = commandeRepos.findById(id);
        if (optionalCommande.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Commande commande = optionalCommande.get();
        Product defaultProduct = new Product(commande.getProductId(), "Produit inconnu");
        CommandeDTO commandeDTO = new CommandeDTO(
                commande.getId(),
                commande.getDescription(),
                commande.getQuantite(),
                commande.getDate(),
                commande.getMontant(),
                defaultProduct
        );
        return ResponseEntity.ok(commandeDTO);
    }

    @GetMapping("/arg")
    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetAllCommandesByArg")
    public List<Commande> getAllCommandesByArg() {
        LocalDate dateLimit = LocalDate.now().minusDays(jours);
        return commandeRepos.findAll().stream()
                .filter(commande -> commande.getDate().isAfter(dateLimit) || commande.getDate().isEqual(dateLimit))
                .collect(Collectors.toList());
    }

    public List<Commande> fallbackGetAllCommandesByArg(Throwable throwable) {
        return List.of();
    }

    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        commande.setDate(LocalDate.now());
        Commande savedCommande = commandeRepos.save(commande);
        return ResponseEntity.ok(savedCommande);
    }

    @PutMapping("/{id}")
    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackUpdateCommande")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande updatedCommande) {
        Optional<Commande> optionalCommande = commandeRepos.findById(id);
        if (optionalCommande.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Commande existingCommande = optionalCommande.get();
        existingCommande.setDescription(updatedCommande.getDescription());
        existingCommande.setQuantite(updatedCommande.getQuantite());
        existingCommande.setMontant(updatedCommande.getMontant());
        existingCommande.setProductId(updatedCommande.getProductId());
        commandeRepos.save(existingCommande);
        return ResponseEntity.ok(existingCommande);
    }

    public ResponseEntity<Commande> fallbackUpdateCommande(Long id, Commande updatedCommande, Throwable throwable) {
        return ResponseEntity.status(503).body(updatedCommande);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        if (!commandeRepos.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        commandeRepos.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
