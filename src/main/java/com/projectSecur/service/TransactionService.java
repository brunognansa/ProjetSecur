package com.projectSecur.service;

import com.projectSecur.dao.CompteDao;
import com.projectSecur.dao.TransactionDao;
import com.projectSecur.model.Compte;
import com.projectSecur.model.CompteBancaire;
import com.projectSecur.model.CompteMobileMoney;
import com.projectSecur.model.Transaction; // Assurez-vous d'importer votre propre classe Transaction
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionDao transactionDao;
    @Autowired
    private CompteDao compteDao;
    @Autowired
    private MailService mailService;

    @Transactional
    public void effectuerTransaction(String numeroSource, String numeroDestination, String institutionDestination,
                                     String nomCompteSource, String motDePasse, double montant) throws Exception {
        Optional<Compte> compteSourceOpt = compteDao.findByNumeroCompteOrNumeroTelephone(numeroSource);
        Optional<Compte> compteDestOpt = compteDao.findByNumeroCompteOrNumeroTelephone(numeroDestination);

        if (compteSourceOpt.isPresent() && compteDestOpt.isPresent()) {
            Compte compteSource = compteSourceOpt.get();
            Compte compteDest = compteDestOpt.get();

            if (!compteSource.getNomCompte().equals(nomCompteSource) || !compteSource.getMotDePasse().equals(motDePasse)) {
                throw new Exception("Nom de compte ou mot de passe incorrect");
            }

            if (compteSource.getSolde() < montant) {
                throw new Exception("Solde insuffisant");
            }

            compteSource.setSolde(compteSource.getSolde() - montant);
            compteDest.setSolde(compteDest.getSolde() + montant);

            compteDao.save(compteSource);
            compteDao.save(compteDest);

            Transaction transaction = new Transaction();
            transaction.setCompteSource(compteSource);
            transaction.setCompteDestination(compteDest);
            transaction.setMontant(montant);
            transaction.setDateTransaction(LocalDateTime.now());
            transaction.setInstitutionDestination(institutionDestination);

            if (compteDest instanceof CompteBancaire) {
                transaction.setTypeCompteDestination("Bancaire");
            } else if (compteDest instanceof CompteMobileMoney) {
                transaction.setTypeCompteDestination("MobileMoney");
            }

            transactionDao.save(transaction); // Suppression du cast incorrect
        } else {
            throw new Exception("Compte introuvable");
        }
    }

    public List<Compte> getComptes() {
        return List.of(); // Cette méthode devrait probablement interroger la base de données pour obtenir les comptes
    }
}
