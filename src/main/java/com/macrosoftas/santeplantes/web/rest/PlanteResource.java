package com.macrosoftas.santeplantes.web.rest;

import com.macrosoftas.santeplantes.domain.Plante;
import com.macrosoftas.santeplantes.repository.PlanteRepository;
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
 * REST controller for managing {@link com.macrosoftas.santeplantes.domain.Plante}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PlanteResource {

    private final Logger log = LoggerFactory.getLogger(PlanteResource.class);

    private static final String ENTITY_NAME = "plante";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanteRepository planteRepository;

    public PlanteResource(PlanteRepository planteRepository) {
        this.planteRepository = planteRepository;
    }

    /**
     * {@code POST  /plantes} : Create a new plante.
     *
     * @param plante the plante to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new plante, or with status {@code 400 (Bad Request)} if the plante has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/plantes")
    public ResponseEntity<Plante> createPlante(@Valid @RequestBody Plante plante) throws URISyntaxException {
        log.debug("REST request to save Plante : {}", plante);
        if (plante.getId() != null) {
            throw new BadRequestAlertException("A new plante cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Plante result = planteRepository.save(plante);
        return ResponseEntity.created(new URI("/api/plantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /plantes} : Updates an existing plante.
     *
     * @param plante the plante to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated plante,
     * or with status {@code 400 (Bad Request)} if the plante is not valid,
     * or with status {@code 500 (Internal Server Error)} if the plante couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/plantes")
    public ResponseEntity<Plante> updatePlante(@Valid @RequestBody Plante plante) throws URISyntaxException {
        log.debug("REST request to update Plante : {}", plante);
        if (plante.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Plante result = planteRepository.save(plante);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, plante.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /plantes} : get all the plantes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of plantes in body.
     */
    @GetMapping("/plantes")
    public List<Plante> getAllPlantes() {
        log.debug("REST request to get all Plantes");
        return planteRepository.findAll();
    }

    /**
     * {@code GET  /plantes/:id} : get the "id" plante.
     *
     * @param id the id of the plante to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the plante, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/plantes/{id}")
    public ResponseEntity<Plante> getPlante(@PathVariable Long id) {
        log.debug("REST request to get Plante : {}", id);
        Optional<Plante> plante = planteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(plante);
    }

    /**
     * {@code DELETE  /plantes/:id} : delete the "id" plante.
     *
     * @param id the id of the plante to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/plantes/{id}")
    public ResponseEntity<Void> deletePlante(@PathVariable Long id) {
        log.debug("REST request to delete Plante : {}", id);
        planteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
