package com.projectSecur.service;

import com.projectSecur.model.Compte;
import com.projectSecur.model.CompteBancaire;
import com.projectSecur.model.CompteMobileMoney;
import com.projectSecur.model.InstitutionFinanciere;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstitutionFinanciereService {
    private List<InstitutionFinanciere> institutions = new ArrayList<>();

    public InstitutionFinanciereService() {
        initialiserInstitutions();
    }

    private void initialiserInstitutions() {
        InstitutionFinanciere ecobank = new InstitutionFinanciere("Ecobank");
        InstitutionFinanciere orabank = new InstitutionFinanciere("Orabank");
        InstitutionFinanciere tmoney = new InstitutionFinanciere("TMoney");
        InstitutionFinanciere flooz = new InstitutionFinanciere("Flooz");

        // Ajouter des comptes bancaires pour Ecobank et Orabank
        CompteBancaire compteEcobank = new CompteBancaire();
        compteEcobank.setNumeroCompte("ECO123456");
        compteEcobank.setNomCompte("John Doe");
        compteEcobank.setMotDePasse("password123");
        compteEcobank.setSolde(1000.0);
        compteEcobank.setIban("ECO123456789");
        compteEcobank.setRib("0123456789");
        ecobank.ajouterCompte(compteEcobank);

        CompteBancaire compteOrabank = new CompteBancaire();
        compteOrabank.setNumeroCompte("ORA789012");
        compteOrabank.setNomCompte("Jane Smith");
        compteOrabank.setMotDePasse("password456");
        compteOrabank.setSolde(2000.0);
        compteOrabank.setIban("ORA987654321");
        compteOrabank.setRib("9876543210");
        orabank.ajouterCompte(compteOrabank);

        // Ajouter des comptes mobile money pour TMoney et Flooz
        CompteMobileMoney compteTMoney = new CompteMobileMoney();
        compteTMoney.setNumeroCompte("TM001");
        compteTMoney.setNomCompte("Alice Johnson");
        compteTMoney.setMotDePasse("pin1234");
        compteTMoney.setSolde(500.0);
        compteTMoney.setNumeroTelephone("90123456");
        tmoney.ajouterCompte(compteTMoney);

        CompteMobileMoney compteFlooz = new CompteMobileMoney();
        compteFlooz.setNumeroCompte("FL002");
        compteFlooz.setNomCompte("Bob Williams");
        compteFlooz.setMotDePasse("pin5678");
        compteFlooz.setSolde(750.0);
        compteFlooz.setNumeroTelephone("91234567");
        flooz.ajouterCompte(compteFlooz);

        institutions.add(ecobank);
        institutions.add(orabank);
        institutions.add(tmoney);
        institutions.add(flooz);
    }

    public Optional<Compte> trouverCompte(String numeroCompteOuTelephone) {
        for (InstitutionFinanciere institution : institutions) {
            Optional<Compte> compte = institution.trouverCompte(numeroCompteOuTelephone);
            if (compte.isPresent()) {
                return compte;
            }
        }
        return Optional.empty();
    }

    public List<Compte> getTousLesComptes() {
        List<Compte> tousLesComptes = new ArrayList<>();
        for (InstitutionFinanciere institution : institutions) {
            tousLesComptes.addAll(institution.getTousLesComptes());
        }
        return tousLesComptes;
    }

    public boolean supprimerCompte(String numeroCompteOuTelephone) {
        for (InstitutionFinanciere institution : institutions) {
            if (institution.supprimerCompte(numeroCompteOuTelephone)) {
                return true;
            }
        }
        return false;
    }

    public boolean modifierCompte(Compte compteModifie) {
        for (InstitutionFinanciere institution : institutions) {
            if (institution.modifierCompte(compteModifie)) {
                return true;
            }
        }
        return false;
    }
}