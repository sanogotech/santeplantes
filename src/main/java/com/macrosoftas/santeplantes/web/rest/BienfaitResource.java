package com.macrosoftas.santeplantes.web.rest;

import com.macrosoftas.santeplantes.domain.Bienfait;
import com.macrosoftas.santeplantes.repository.BienfaitRepository;
import com.macrosoftas.santeplantes.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.macrosoftas.santeplantes.domain.Bienfait}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BienfaitResource {

    private final Logger log = LoggerFactory.getLogger(BienfaitResource.class);

    private static final String ENTITY_NAME = "bienfait";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BienfaitRepository bienfaitRepository;

    public BienfaitResource(BienfaitRepository bienfaitRepository) {
        this.bienfaitRepository = bienfaitRepository;
    }

    /**
     * {@code POST  /bienfaits} : Create a new bienfait.
     *
     * @param bienfait the bienfait to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bienfait, or with status {@code 400 (Bad Request)} if the bienfait has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bienfaits")
    public ResponseEntity<Bienfait> createBienfait(@Valid @RequestBody Bienfait bienfait) throws URISyntaxException {
        log.debug("REST request to save Bienfait : {}", bienfait);
        if (bienfait.getId() != null) {
            throw new BadRequestAlertException("A new bienfait cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bienfait result = bienfaitRepository.save(bienfait);
        return ResponseEntity.created(new URI("/api/bienfaits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bienfaits} : Updates an existing bienfait.
     *
     * @param bienfait the bienfait to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bienfait,
     * or with status {@code 400 (Bad Request)} if the bienfait is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bienfait couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bienfaits")
    public ResponseEntity<Bienfait> updateBienfait(@Valid @RequestBody Bienfait bienfait) throws URISyntaxException {
        log.debug("REST request to update Bienfait : {}", bienfait);
        if (bienfait.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bienfait result = bienfaitRepository.save(bienfait);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bienfait.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bienfaits} : get all the bienfaits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bienfaits in body.
     */
    @GetMapping("/bienfaits")
    public List<Bienfait> getAllBienfaits() {
        log.debug("REST request to get all Bienfaits");
        return bienfaitRepository.findAll();
    }

    /**
     * {@code GET  /bienfaits/:id} : get the "id" bienfait.
     *
     * @param id the id of the bienfait to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bienfait, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bienfaits/{id}")
    public ResponseEntity<Bienfait> getBienfait(@PathVariable Long id) {
        log.debug("REST request to get Bienfait : {}", id);
        Optional<Bienfait> bienfait = bienfaitRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bienfait);
    }

    /**
     * {@code DELETE  /bienfaits/:id} : delete the "id" bienfait.
     *
     * @param id the id of the bienfait to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bienfaits/{id}")
    public ResponseEntity<Void> deleteBienfait(@PathVariable Long id) {
        log.debug("REST request to delete Bienfait : {}", id);
        bienfaitRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
