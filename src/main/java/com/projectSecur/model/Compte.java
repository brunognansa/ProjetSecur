package com.projectSecur.model;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String numeroCompte;
    protected String nomCompte;
    protected String motDePasse;
    protected double solde;
    protected String institutionFinanciere;

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    private boolean actif;

    // Getters
    public Long getId() {
        return id;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public String getNomCompte() {
        return nomCompte;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public double getSolde() {
        return solde;
    }

    public String getInstitutionFinanciere() {
        return institutionFinanciere;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public void setNomCompte(String nomCompte) {
        this.nomCompte = nomCompte;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void setInstitutionFinanciere(String institutionFinanciere) {
        this.institutionFinanciere = institutionFinanciere;
    }
}