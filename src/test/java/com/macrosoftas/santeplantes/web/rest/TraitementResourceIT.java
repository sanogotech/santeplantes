package com.macrosoftas.santeplantes.web.rest;

import com.macrosoftas.santeplantes.SanteplantesApp;
import com.macrosoftas.santeplantes.domain.Traitement;
import com.macrosoftas.santeplantes.repository.TraitementRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.macrosoftas.santeplantes.domain.enumeration.Fiabilite;
/**
 * Integration tests for the {@link TraitementResource} REST controller.
 */
@SpringBootTest(classes = SanteplantesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TraitementResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final Fiabilite DEFAULT_FIABILITE = Fiabilite.HAUT;
    private static final Fiabilite UPDATED_FIABILITE = Fiabilite.MOYEN;

    @Autowired
    private TraitementRepository traitementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTraitementMockMvc;

    private Traitement traitement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Traitement createEntity(EntityManager em) {
        Traitement traitement = new Traitement()
            .nom(DEFAULT_NOM)
            .fiabilite(DEFAULT_FIABILITE);
        return traitement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Traitement createUpdatedEntity(EntityManager em) {
        Traitement traitement = new Traitement()
            .nom(UPDATED_NOM)
            .fiabilite(UPDATED_FIABILITE);
        return traitement;
    }

    @BeforeEach
    public void initTest() {
        traitement = createEntity(em);
    }

    @Test
    @Transactional
    public void createTraitement() throws Exception {
        int databaseSizeBeforeCreate = traitementRepository.findAll().size();
        // Create the Traitement
        restTraitementMockMvc.perform(post("/api/traitements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(traitement)))
            .andExpect(status().isCreated());

        // Validate the Traitement in the database
        List<Traitement> traitementList = traitementRepository.findAll();
        assertThat(traitementList).hasSize(databaseSizeBeforeCreate + 1);
        Traitement testTraitement = traitementList.get(traitementList.size() - 1);
        assertThat(testTraitement.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testTraitement.getFiabilite()).isEqualTo(DEFAULT_FIABILITE);
    }

    @Test
    @Transactional
    public void createTraitementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = traitementRepository.findAll().size();

        // Create the Traitement with an existing ID
        traitement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTraitementMockMvc.perform(post("/api/traitements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(traitement)))
            .andExpect(status().isBadRequest());

        // Validate the Traitement in the database
        List<Traitement> traitementList = traitementRepository.findAll();
        assertThat(traitementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = traitementRepository.findAll().size();
        // set the field null
        traitement.setNom(null);

        // Create the Traitement, which fails.


        restTraitementMockMvc.perform(post("/api/traitements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(traitement)))
            .andExpect(status().isBadRequest());

        List<Traitement> traitementList = traitementRepository.findAll();
        assertThat(traitementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFiabiliteIsRequired() throws Exception {
        int databaseSizeBeforeTest = traitementRepository.findAll().size();
        // set the field null
        traitement.setFiabilite(null);

        // Create the Traitement, which fails.


        restTraitementMockMvc.perform(post("/api/traitements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(traitement)))
            .andExpect(status().isBadRequest());

        List<Traitement> traitementList = traitementRepository.findAll();
        assertThat(traitementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTraitements() throws Exception {
        // Initialize the database
        traitementRepository.saveAndFlush(traitement);

        // Get all the traitementList
        restTraitementMockMvc.perform(get("/api/traitements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(traitement.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].fiabilite").value(hasItem(DEFAULT_FIABILITE.toString())));
    }
    
    @Test
    @Transactional
    public void getTraitement() throws Exception {
        // Initialize the database
        traitementRepository.saveAndFlush(traitement);

        // Get the traitement
        restTraitementMockMvc.perform(get("/api/traitements/{id}", traitement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(traitement.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.fiabilite").value(DEFAULT_FIABILITE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTraitement() throws Exception {
        // Get the traitement
        restTraitementMockMvc.perform(get("/api/traitements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTraitement() throws Exception {
        // Initialize the database
        traitementRepository.saveAndFlush(traitement);

        int databaseSizeBeforeUpdate = traitementRepository.findAll().size();

        // Update the traitement
        Traitement updatedTraitement = traitementRepository.findById(traitement.getId()).get();
        // Disconnect from session so that the updates on updatedTraitement are not directly saved in db
        em.detach(updatedTraitement);
        updatedTraitement
            .nom(UPDATED_NOM)
            .fiabilite(UPDATED_FIABILITE);

        restTraitementMockMvc.perform(put("/api/traitements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTraitement)))
            .andExpect(status().isOk());

        // Validate the Traitement in the database
        List<Traitement> traitementList = traitementRepository.findAll();
        assertThat(traitementList).hasSize(databaseSizeBeforeUpdate);
        Traitement testTraitement = traitementList.get(traitementList.size() - 1);
        assertThat(testTraitement.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testTraitement.getFiabilite()).isEqualTo(UPDATED_FIABILITE);
    }

    @Test
    @Transactional
    public void updateNonExistingTraitement() throws Exception {
        int databaseSizeBeforeUpdate = traitementRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTraitementMockMvc.perform(put("/api/traitements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(traitement)))
            .andExpect(status().isBadRequest());

        // Validate the Traitement in the database
        List<Traitement> traitementList = traitementRepository.findAll();
        assertThat(traitementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTraitement() throws Exception {
        // Initialize the database
        traitementRepository.saveAndFlush(traitement);

        int databaseSizeBeforeDelete = traitementRepository.findAll().size();

        // Delete the traitement
        restTraitementMockMvc.perform(delete("/api/traitements/{id}", traitement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Traitement> traitementList = traitementRepository.findAll();
        assertThat(traitementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
