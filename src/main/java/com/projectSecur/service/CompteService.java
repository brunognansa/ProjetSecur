package com.projectSecur.service;

import com.projectSecur.Exception.CompteExistantException;
import com.projectSecur.dao.CompteDao;
import com.projectSecur.model.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompteService {

    @Autowired
    private CompteDao compteDao;

    @Transactional(readOnly = true)
    public List<Compte> getComptes() {
        try {
            // Récupérer tous les comptes
            List<Compte> tousLesComptes = compteDao.findAll();

            // Filtrer les comptes (par exemple, ne garder que les comptes actifs)
            List<Compte> comptesActifs = tousLesComptes.stream()
                    .filter(Compte::isActif)
                    .collect(Collectors.toList());

            // Trier les comptes (par exemple, par numéro de compte)
            comptesActifs.sort((c1, c2) -> c1.getNumeroCompte().compareTo(c2.getNumeroCompte()));

            // Appliquer une logique métier supplémentaire si nécessaire
            // Par exemple, mettre à jour le solde des comptes
            comptesActifs.forEach(this::mettreAJourSolde);

            return comptesActifs;

        } catch (Exception e) {
            // Gérer les exceptions (log, throw custom exception, etc.)
            throw new RuntimeException("Erreur lors de la récupération des comptes", e);
        }
    }

    private void mettreAJourSolde(Compte compte) {
        // Logique pour mettre à jour le solde du compte
        // Par exemple, calculer les intérêts ou appliquer des frais
        double nouveauSolde = compte.getSolde() * 1.01; // Ajoute 1% d'intérêt
        compte.setSolde(nouveauSolde);
    }

    public Compte ajouterCompte(Compte nouveauCompte) throws CompteExistantException {
        if (compteDao.existsByNumero(nouveauCompte.getNumeroCompte())) {
            throw new CompteExistantException("Un compte avec le numéro " + nouveauCompte.getNumeroCompte() + " existe déjà.");
        }
        compteDao.save(nouveauCompte);
        return nouveauCompte;
    }
}
