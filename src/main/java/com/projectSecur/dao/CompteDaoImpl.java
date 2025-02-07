package com.projectSecur.dao;

import com.projectSecur.model.Compte;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CompteDaoImpl implements CompteDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<Compte> findByNumeroCompteOrNumeroTelephone(String numero) {
        TypedQuery<Compte> query = entityManager.createQuery(
                "SELECT c FROM Compte c WHERE c.numeroCompte = :numero OR (c INSTANCEOF CompteMobileMoney AND c.numeroTelephone = :numero)", Compte.class);
        query.setParameter("numero", numero);
        List<Compte> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }
    @Override
    public boolean existsByNumero(String numeroCompte) {
        String jpql = "SELECT COUNT(c) FROM Compte c WHERE c.numero = :numero";
        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("numero", numeroCompte)
                .getSingleResult();
        return count > 0;
    }
    @Override
    public Compte save(Compte compte) {
        return entityManager.merge(compte);
    }

    @Override
    public <S extends Compte> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<Compte> findAll() {
        return List.of();
    }

    @Override
    public List<Compte> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }


    public Optional<Compte> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Compte.class, id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void deleteById(Long id) {
        Compte compte = entityManager.find(Compte.class, id);
        if (compte != null) {
            entityManager.remove(compte);
        }
    }

    @Override
    public void delete(Compte entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Compte> entities) {

    }

    @Override
    public void deleteAll() {

    }


    @Override
    public void flush() {

    }

    @Override
    public <S extends Compte> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Compte> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Compte> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Compte getOne(Long aLong) {
        return null;
    }

    @Override
    public Compte getById(Long aLong) {
        return null;
    }

    @Override
    public Compte getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Compte> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Compte> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Compte> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Compte> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Compte> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Compte> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Compte, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Compte> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Compte> findAll(Pageable pageable) {
        return null;
    }
}