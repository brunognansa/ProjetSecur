package com.projectSecur.model;

public class CompteBancaire extends Compte {

    private String iban;
    private String rib;

    // Getters

    public String getIban() {
        return iban;
    }
    public String getRib() {
        return rib;
    }

    // Setters

    public void setIban(String iban) {
        this.iban = iban;
    }
    public void setRib(String rib) {
        this.rib = rib;
    }
}
