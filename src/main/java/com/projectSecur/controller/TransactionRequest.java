package com.projectSecur.controller;

public class TransactionRequest {
    public String getNumeroSource() {
        return numeroSource;
    }

    public void setNumeroSource(String numeroSource) {
        this.numeroSource = numeroSource;
    }

    public String getNumeroDestination() {
        return numeroDestination;
    }

    public void setNumeroDestination(String numeroDestination) {
        this.numeroDestination = numeroDestination;
    }

    public String getInstitutionDestination() {
        return institutionDestination;
    }

    public void setInstitutionDestination(String institutionDestination) {
        this.institutionDestination = institutionDestination;
    }

    public String getNomCompteSource() {
        return nomCompteSource;
    }

    public void setNomCompteSource(String nomCompteSource) {
        this.nomCompteSource = nomCompteSource;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    private String numeroSource;
    private String numeroDestination;
    private String institutionDestination;
    private String nomCompteSource;
    private String motDePasse;
    private double montant;


}
