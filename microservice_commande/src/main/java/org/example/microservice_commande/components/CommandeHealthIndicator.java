package org.example.microservice_commande.components;


import org.example.microservice_commande.repos.CommandeRepos;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CommandeHealthIndicator implements HealthIndicator {

    private final CommandeRepos commandeRepos;

    public CommandeHealthIndicator(CommandeRepos commandeRepos) {
        this.commandeRepos = commandeRepos;
    }

    @Override
    public Health health() {
        long commandeCount = commandeRepos.count(); // Compte les commandes dans la table
        if (commandeCount > 0) {
            return Health.up()
                    .withDetail("message", "Le microservice est en bonne santé.")
                    .withDetail("commandeCount", commandeCount)
                    .build();
        } else {
            return Health.down()
                    .withDetail("message", "Le microservice n'a pas de commandes.")
                    .withDetail("commandeCount", commandeCount)
                    .build();
        }
    }
}

