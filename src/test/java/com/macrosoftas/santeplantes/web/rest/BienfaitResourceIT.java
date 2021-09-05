package com.macrosoftas.santeplantes.web.rest;

import com.macrosoftas.santeplantes.SanteplantesApp;
import com.macrosoftas.santeplantes.domain.Bienfait;
import com.macrosoftas.santeplantes.repository.BienfaitRepository;

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

/**
 * Integration tests for the {@link BienfaitResource} REST controller.
 */
@SpringBootTest(classes = SanteplantesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BienfaitResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_VITAMINE = "AAAAAAAAAA";
    private static final String UPDATED_VITAMINE = "BBBBBBBBBB";

    private static final String DEFAULT_MINERAUX = "AAAAAAAAAA";
    private static final String UPDATED_MINERAUX = "BBBBBBBBBB";

    @Autowired
    private BienfaitRepository bienfaitRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBienfaitMockMvc;

    private Bienfait bienfait;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bienfait createEntity(EntityManager em) {
        Bienfait bienfait = new Bienfait()
            .nom(DEFAULT_NOM)
            .description(DEFAULT_DESCRIPTION)
            .vitamine(DEFAULT_VITAMINE)
            .mineraux(DEFAULT_MINERAUX);
        return bienfait;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bienfait createUpdatedEntity(EntityManager em) {
        Bienfait bienfait = new Bienfait()
            .nom(UPDATED_NOM)
            .description(UPDATED_DESCRIPTION)
            .vitamine(UPDATED_VITAMINE)
            .mineraux(UPDATED_MINERAUX);
        return bienfait;
    }

    @BeforeEach
    public void initTest() {
        bienfait = createEntity(em);
    }

    @Test
    @Transactional
    public void createBienfait() throws Exception {
        int databaseSizeBeforeCreate = bienfaitRepository.findAll().size();
        // Create the Bienfait
        restBienfaitMockMvc.perform(post("/api/bienfaits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bienfait)))
            .andExpect(status().isCreated());

        // Validate the Bienfait in the database
        List<Bienfait> bienfaitList = bienfaitRepository.findAll();
        assertThat(bienfaitList).hasSize(databaseSizeBeforeCreate + 1);
        Bienfait testBienfait = bienfaitList.get(bienfaitList.size() - 1);
        assertThat(testBienfait.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testBienfait.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBienfait.getVitamine()).isEqualTo(DEFAULT_VITAMINE);
        assertThat(testBienfait.getMineraux()).isEqualTo(DEFAULT_MINERAUX);
    }

    @Test
    @Transactional
    public void createBienfaitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bienfaitRepository.findAll().size();

        // Create the Bienfait with an existing ID
        bienfait.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBienfaitMockMvc.perform(post("/api/bienfaits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bienfait)))
            .andExpect(status().isBadRequest());

        // Validate the Bienfait in the database
        List<Bienfait> bienfaitList = bienfaitRepository.findAll();
        assertThat(bienfaitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = bienfaitRepository.findAll().size();
        // set the field null
        bienfait.setNom(null);

        // Create the Bienfait, which fails.


        restBienfaitMockMvc.perform(post("/api/bienfaits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bienfait)))
            .andExpect(status().isBadRequest());

        List<Bienfait> bienfaitList = bienfaitRepository.findAll();
        assertThat(bienfaitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = bienfaitRepository.findAll().size();
        // set the field null
        bienfait.setDescription(null);

        // Create the Bienfait, which fails.


        restBienfaitMockMvc.perform(post("/api/bienfaits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bienfait)))
            .andExpect(status().isBadRequest());

        List<Bienfait> bienfaitList = bienfaitRepository.findAll();
        assertThat(bienfaitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBienfaits() throws Exception {
        // Initialize the database
        bienfaitRepository.saveAndFlush(bienfait);

        // Get all the bienfaitList
        restBienfaitMockMvc.perform(get("/api/bienfaits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bienfait.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].vitamine").value(hasItem(DEFAULT_VITAMINE)))
            .andExpect(jsonPath("$.[*].mineraux").value(hasItem(DEFAULT_MINERAUX)));
    }
    
    @Test
    @Transactional
    public void getBienfait() throws Exception {
        // Initialize the database
        bienfaitRepository.saveAndFlush(bienfait);

        // Get the bienfait
        restBienfaitMockMvc.perform(get("/api/bienfaits/{id}", bienfait.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bienfait.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.vitamine").value(DEFAULT_VITAMINE))
            .andExpect(jsonPath("$.mineraux").value(DEFAULT_MINERAUX));
    }
    @Test
    @Transactional
    public void getNonExistingBienfait() throws Exception {
        // Get the bienfait
        restBienfaitMockMvc.perform(get("/api/bienfaits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBienfait() throws Exception {
        // Initialize the database
        bienfaitRepository.saveAndFlush(bienfait);

        int databaseSizeBeforeUpdate = bienfaitRepository.findAll().size();

        // Update the bienfait
        Bienfait updatedBienfait = bienfaitRepository.findById(bienfait.getId()).get();
        // Disconnect from session so that the updates on updatedBienfait are not directly saved in db
        em.detach(updatedBienfait);
        updatedBienfait
            .nom(UPDATED_NOM)
            .description(UPDATED_DESCRIPTION)
            .vitamine(UPDATED_VITAMINE)
            .mineraux(UPDATED_MINERAUX);

        restBienfaitMockMvc.perform(put("/api/bienfaits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBienfait)))
            .andExpect(status().isOk());

        // Validate the Bienfait in the database
        List<Bienfait> bienfaitList = bienfaitRepository.findAll();
        assertThat(bienfaitList).hasSize(databaseSizeBeforeUpdate);
        Bienfait testBienfait = bienfaitList.get(bienfaitList.size() - 1);
        assertThat(testBienfait.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testBienfait.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBienfait.getVitamine()).isEqualTo(UPDATED_VITAMINE);
        assertThat(testBienfait.getMineraux()).isEqualTo(UPDATED_MINERAUX);
    }

    @Test
    @Transactional
    public void updateNonExistingBienfait() throws Exception {
        int databaseSizeBeforeUpdate = bienfaitRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBienfaitMockMvc.perform(put("/api/bienfaits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bienfait)))
            .andExpect(status().isBadRequest());

        // Validate the Bienfait in the database
        List<Bienfait> bienfaitList = bienfaitRepository.findAll();
        assertThat(bienfaitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBienfait() throws Exception {
        // Initialize the database
        bienfaitRepository.saveAndFlush(bienfait);

        int databaseSizeBeforeDelete = bienfaitRepository.findAll().size();

        // Delete the bienfait
        restBienfaitMockMvc.perform(delete("/api/bienfaits/{id}", bienfait.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bienfait> bienfaitList = bienfaitRepository.findAll();
        assertThat(bienfaitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
