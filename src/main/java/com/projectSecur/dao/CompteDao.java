package com.projectSecur.dao;

import com.projectSecur.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompteDao extends JpaRepository<Compte,Long> {
    Optional<Compte> findByNumeroCompteOrNumeroTelephone(String numero);
    Compte save(Compte compte);
    List<Compte> findAll();
    void deleteById(Long id);
    boolean existsByNumero(String numero);
}
