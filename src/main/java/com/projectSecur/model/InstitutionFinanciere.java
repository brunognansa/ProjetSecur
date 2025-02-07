package com.projectSecur.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class InstitutionFinanciere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compte> comptes = new ArrayList<>();

    // Constructeur, getters et setters

    public InstitutionFinanciere() {}

    public InstitutionFinanciere(String nom) {
        this.nom = nom;
    }

    // Méthodes pour gérer les comptes
    public void ajouterCompte(Compte compte) {
        comptes.add(compte);
        compte.setInstitutionFinanciere(this.nom);
    }

    public Optional<Compte> trouverCompte(String numeroCompteOuTelephone) {
        return comptes.stream()
                .filter(c -> c.getNumeroCompte().equals(numeroCompteOuTelephone) ||
                        (c instanceof CompteMobileMoney && ((CompteMobileMoney) c).getNumeroTelephone().equals(numeroCompteOuTelephone)))
                .findFirst();
    }

    public List<Compte> getTousLesComptes() {
        return new ArrayList<>(comptes);
    }

    public boolean supprimerCompte(String numeroCompteOuTelephone) {
        return comptes.removeIf(c -> c.getNumeroCompte().equals(numeroCompteOuTelephone) ||
                (c instanceof CompteMobileMoney && ((CompteMobileMoney) c).getNumeroTelephone().equals(numeroCompteOuTelephone)));
    }

    public boolean modifierCompte(Compte compteModifie) {
        for (int i = 0; i < comptes.size(); i++) {
            if (comptes.get(i).getNumeroCompte().equals(compteModifie.getNumeroCompte())) {
                comptes.set(i, compteModifie);
                return true;
            }
        }
        return false;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }
}