package com.macrosoftas.santeplantes.repository;

import com.macrosoftas.santeplantes.domain.Maladie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Maladie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MaladieRepository extends JpaRepository<Maladie, Long> {
}
