package com.macrosoftas.santeplantes.web.rest;

import com.macrosoftas.santeplantes.domain.Maladie;
import com.macrosoftas.santeplantes.repository.MaladieRepository;
import com.macrosoftas.santeplantes.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.macrosoftas.santeplantes.domain.Maladie}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MaladieResource {

    private final Logger log = LoggerFactory.getLogger(MaladieResource.class);

    private static final String ENTITY_NAME = "maladie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MaladieRepository maladieRepository;

    public MaladieResource(MaladieRepository maladieRepository) {
        this.maladieRepository = maladieRepository;
    }

    /**
     * {@code POST  /maladies} : Create a new maladie.
     *
     * @param maladie the maladie to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new maladie, or with status {@code 400 (Bad Request)} if the maladie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/maladies")
    public ResponseEntity<Maladie> createMaladie(@Valid @RequestBody Maladie maladie) throws URISyntaxException {
        log.debug("REST request to save Maladie : {}", maladie);
        if (maladie.getId() != null) {
            throw new BadRequestAlertException("A new maladie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Maladie result = maladieRepository.save(maladie);
        return ResponseEntity.created(new URI("/api/maladies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /maladies} : Updates an existing maladie.
     *
     * @param maladie the maladie to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated maladie,
     * or with status {@code 400 (Bad Request)} if the maladie is not valid,
     * or with status {@code 500 (Internal Server Error)} if the maladie couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/maladies")
    public ResponseEntity<Maladie> updateMaladie(@Valid @RequestBody Maladie maladie) throws URISyntaxException {
        log.debug("REST request to update Maladie : {}", maladie);
        if (maladie.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Maladie result = maladieRepository.save(maladie);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, maladie.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /maladies} : get all the maladies.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of maladies in body.
     */
    @GetMapping("/maladies")
    public ResponseEntity<List<Maladie>> getAllMaladies(Pageable pageable) {
        log.debug("REST request to get a page of Maladies");
        Page<Maladie> page = maladieRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /maladies/:id} : get the "id" maladie.
     *
     * @param id the id of the maladie to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the maladie, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/maladies/{id}")
    public ResponseEntity<Maladie> getMaladie(@PathVariable Long id) {
        log.debug("REST request to get Maladie : {}", id);
        Optional<Maladie> maladie = maladieRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(maladie);
    }

    /**
     * {@code DELETE  /maladies/:id} : delete the "id" maladie.
     *
     * @param id the id of the maladie to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/maladies/{id}")
    public ResponseEntity<Void> deleteMaladie(@PathVariable Long id) {
        log.debug("REST request to delete Maladie : {}", id);
        maladieRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
