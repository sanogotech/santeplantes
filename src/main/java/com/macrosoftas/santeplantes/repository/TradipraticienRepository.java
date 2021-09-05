package com.macrosoftas.santeplantes.repository;

import com.macrosoftas.santeplantes.domain.Tradipraticien;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Tradipraticien entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TradipraticienRepository extends JpaRepository<Tradipraticien, Long> {
}
