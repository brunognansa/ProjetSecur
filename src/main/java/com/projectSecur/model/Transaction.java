package com.projectSecur.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCompteSource;
    private String numeroCompteDestination;
    private double montant;
    private LocalDateTime date;
    private String institutionDestination;
    private String  typeCompteDestination;
    private LocalDateTime dateTransaction;

    @ManyToOne
    @JoinColumn(name = "compte_source_id")
    private Compte compteSource;

    @ManyToOne
    @JoinColumn(name = "compte_destination_id")
    private Compte compteDestination;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCompteSource() {
        return numeroCompteSource;
    }

    public void setNumeroCompteSource(String numeroCompteSource) {
        this.numeroCompteSource = numeroCompteSource;
    }

    public String getNumeroCompteDestination() {
        return numeroCompteDestination;
    }

    public void setNumeroCompteDestination(String numeroCompteDestination) {
        this.numeroCompteDestination = numeroCompteDestination;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getInstitutionDestination() {
        return institutionDestination;
    }

    public void setInstitutionDestination(String institutionDestination) {
        this.institutionDestination = institutionDestination;
    }

    public Compte getCompteSource() {
        return compteSource;
    }

    public void setCompteSource(Compte compteSource) {
        this.compteSource = compteSource;
    }

    public Compte getCompteDestination() {
        return compteDestination;
    }

    public void setCompteDestination(Compte compteDestination) {
        this.compteDestination = compteDestination;
    }
    public String getTypeCompteDestination() {
        return typeCompteDestination;
    }

    public void setTypeCompteDestination(String typeCompteDestination) {
        this.typeCompteDestination = typeCompteDestination;
    }
    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }
}
