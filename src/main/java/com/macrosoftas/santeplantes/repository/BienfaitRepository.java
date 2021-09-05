package com.macrosoftas.santeplantes.repository;

import com.macrosoftas.santeplantes.domain.Bienfait;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bienfait entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BienfaitRepository extends JpaRepository<Bienfait, Long> {
}
