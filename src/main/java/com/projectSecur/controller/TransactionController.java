package com.projectSecur.controller;

import com.projectSecur.Exception.CompteExistantException;
import com.projectSecur.model.Compte;
import com.projectSecur.service.CompteService;
import com.projectSecur.service.InstitutionFinanciereService;
import com.projectSecur.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private InstitutionFinanciereService institutionFinanciereService;


    // Endpoint pour effectuer une transaction
    @PostMapping("/transactions")
    public ResponseEntity<?> effectuerTransaction(@RequestBody TransactionRequest request) {
        try {
            transactionService.effectuerTransaction(
                    request.getNumeroSource(),
                    request.getNumeroDestination(),
                    request.getInstitutionDestination(),
                    request.getNomCompteSource(),
                    request.getMotDePasse(),
                    request.getMontant()
            );
            return ResponseEntity.ok("Transaction effectuée avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint pour obtenir tous les comptes (utilisant TransactionService)
    @GetMapping("/transactions/comptes")
    public ResponseEntity<List<Compte>> getComptesFromTransactionService() {
        return ResponseEntity.ok(transactionService.getComptes());
    }

    // Endpoint pour obtenir tous les comptes (utilisant InstitutionFinanciereService)
    @GetMapping("/comptes")
    public ResponseEntity<List<Compte>> getTousLesComptes() {
        return ResponseEntity.ok(institutionFinanciereService.getTousLesComptes());
    }

    // Endpoint pour obtenir un compte spécifique
    @GetMapping("/comptes/{numeroCompteOuTelephone}")
    public ResponseEntity<?> getCompte(@PathVariable String numeroCompteOuTelephone) {
        Optional<Compte> compte = institutionFinanciereService.trouverCompte(numeroCompteOuTelephone);
        return compte.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint pour supprimer un compte
    @DeleteMapping("/comptes/{numeroCompteOuTelephone}")
    public ResponseEntity<?> supprimerCompte(@PathVariable String numeroCompteOuTelephone) {
        boolean supprime = institutionFinanciereService.supprimerCompte(numeroCompteOuTelephone);
        if (supprime) {
            return ResponseEntity.ok("Compte supprimé avec succès");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pour modifier un compte
    @PutMapping("/comptes")
    public ResponseEntity<?> modifierCompte(@RequestBody Compte compteModifie) {
        boolean modifie = institutionFinanciereService.modifierCompte(compteModifie);
        if (modifie) {
            return ResponseEntity.ok("Compte modifié avec succès");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private CompteService compteService;

    @PostMapping
    public ResponseEntity<?> ajouterCompte(@RequestBody Compte nouveauCompte) {
        try {
            Compte compteAjoute = compteService.ajouterCompte(nouveauCompte);
            return ResponseEntity.ok(compteAjoute);
        } catch (CompteExistantException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}