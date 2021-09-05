package com.macrosoftas.santeplantes.web.rest;

import com.macrosoftas.santeplantes.domain.Tradipraticien;
import com.macrosoftas.santeplantes.repository.TradipraticienRepository;
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
 * REST controller for managing {@link com.macrosoftas.santeplantes.domain.Tradipraticien}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TradipraticienResource {

    private final Logger log = LoggerFactory.getLogger(TradipraticienResource.class);

    private static final String ENTITY_NAME = "tradipraticien";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TradipraticienRepository tradipraticienRepository;

    public TradipraticienResource(TradipraticienRepository tradipraticienRepository) {
        this.tradipraticienRepository = tradipraticienRepository;
    }

    /**
     * {@code POST  /tradipraticiens} : Create a new tradipraticien.
     *
     * @param tradipraticien the tradipraticien to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tradipraticien, or with status {@code 400 (Bad Request)} if the tradipraticien has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tradipraticiens")
    public ResponseEntity<Tradipraticien> createTradipraticien(@Valid @RequestBody Tradipraticien tradipraticien) throws URISyntaxException {
        log.debug("REST request to save Tradipraticien : {}", tradipraticien);
        if (tradipraticien.getId() != null) {
            throw new BadRequestAlertException("A new tradipraticien cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Tradipraticien result = tradipraticienRepository.save(tradipraticien);
        return ResponseEntity.created(new URI("/api/tradipraticiens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tradipraticiens} : Updates an existing tradipraticien.
     *
     * @param tradipraticien the tradipraticien to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradipraticien,
     * or with status {@code 400 (Bad Request)} if the tradipraticien is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tradipraticien couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tradipraticiens")
    public ResponseEntity<Tradipraticien> updateTradipraticien(@Valid @RequestBody Tradipraticien tradipraticien) throws URISyntaxException {
        log.debug("REST request to update Tradipraticien : {}", tradipraticien);
        if (tradipraticien.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Tradipraticien result = tradipraticienRepository.save(tradipraticien);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tradipraticien.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tradipraticiens} : get all the tradipraticiens.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tradipraticiens in body.
     */
    @GetMapping("/tradipraticiens")
    public List<Tradipraticien> getAllTradipraticiens() {
        log.debug("REST request to get all Tradipraticiens");
        return tradipraticienRepository.findAll();
    }

    /**
     * {@code GET  /tradipraticiens/:id} : get the "id" tradipraticien.
     *
     * @param id the id of the tradipraticien to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tradipraticien, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tradipraticiens/{id}")
    public ResponseEntity<Tradipraticien> getTradipraticien(@PathVariable Long id) {
        log.debug("REST request to get Tradipraticien : {}", id);
        Optional<Tradipraticien> tradipraticien = tradipraticienRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tradipraticien);
    }

    /**
     * {@code DELETE  /tradipraticiens/:id} : delete the "id" tradipraticien.
     *
     * @param id the id of the tradipraticien to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tradipraticiens/{id}")
    public ResponseEntity<Void> deleteTradipraticien(@PathVariable Long id) {
        log.debug("REST request to delete Tradipraticien : {}", id);
        tradipraticienRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
