package com.macrosoftas.santeplantes.web.rest;

import com.macrosoftas.santeplantes.SanteplantesApp;
import com.macrosoftas.santeplantes.domain.Maladie;
import com.macrosoftas.santeplantes.repository.MaladieRepository;

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

import com.macrosoftas.santeplantes.domain.enumeration.TypeMaladie;
/**
 * Integration tests for the {@link MaladieResource} REST controller.
 */
@SpringBootTest(classes = SanteplantesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MaladieResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final TypeMaladie DEFAULT_TYPE = TypeMaladie.Cardiovasculaire;
    private static final TypeMaladie UPDATED_TYPE = TypeMaladie.Cancers;

    @Autowired
    private MaladieRepository maladieRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMaladieMockMvc;

    private Maladie maladie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Maladie createEntity(EntityManager em) {
        Maladie maladie = new Maladie()
            .nom(DEFAULT_NOM)
            .type(DEFAULT_TYPE);
        return maladie;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Maladie createUpdatedEntity(EntityManager em) {
        Maladie maladie = new Maladie()
            .nom(UPDATED_NOM)
            .type(UPDATED_TYPE);
        return maladie;
    }

    @BeforeEach
    public void initTest() {
        maladie = createEntity(em);
    }

    @Test
    @Transactional
    public void createMaladie() throws Exception {
        int databaseSizeBeforeCreate = maladieRepository.findAll().size();
        // Create the Maladie
        restMaladieMockMvc.perform(post("/api/maladies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maladie)))
            .andExpect(status().isCreated());

        // Validate the Maladie in the database
        List<Maladie> maladieList = maladieRepository.findAll();
        assertThat(maladieList).hasSize(databaseSizeBeforeCreate + 1);
        Maladie testMaladie = maladieList.get(maladieList.size() - 1);
        assertThat(testMaladie.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testMaladie.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createMaladieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = maladieRepository.findAll().size();

        // Create the Maladie with an existing ID
        maladie.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMaladieMockMvc.perform(post("/api/maladies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maladie)))
            .andExpect(status().isBadRequest());

        // Validate the Maladie in the database
        List<Maladie> maladieList = maladieRepository.findAll();
        assertThat(maladieList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = maladieRepository.findAll().size();
        // set the field null
        maladie.setNom(null);

        // Create the Maladie, which fails.


        restMaladieMockMvc.perform(post("/api/maladies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maladie)))
            .andExpect(status().isBadRequest());

        List<Maladie> maladieList = maladieRepository.findAll();
        assertThat(maladieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = maladieRepository.findAll().size();
        // set the field null
        maladie.setType(null);

        // Create the Maladie, which fails.


        restMaladieMockMvc.perform(post("/api/maladies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maladie)))
            .andExpect(status().isBadRequest());

        List<Maladie> maladieList = maladieRepository.findAll();
        assertThat(maladieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMaladies() throws Exception {
        // Initialize the database
        maladieRepository.saveAndFlush(maladie);

        // Get all the maladieList
        restMaladieMockMvc.perform(get("/api/maladies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(maladie.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getMaladie() throws Exception {
        // Initialize the database
        maladieRepository.saveAndFlush(maladie);

        // Get the maladie
        restMaladieMockMvc.perform(get("/api/maladies/{id}", maladie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(maladie.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMaladie() throws Exception {
        // Get the maladie
        restMaladieMockMvc.perform(get("/api/maladies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMaladie() throws Exception {
        // Initialize the database
        maladieRepository.saveAndFlush(maladie);

        int databaseSizeBeforeUpdate = maladieRepository.findAll().size();

        // Update the maladie
        Maladie updatedMaladie = maladieRepository.findById(maladie.getId()).get();
        // Disconnect from session so that the updates on updatedMaladie are not directly saved in db
        em.detach(updatedMaladie);
        updatedMaladie
            .nom(UPDATED_NOM)
            .type(UPDATED_TYPE);

        restMaladieMockMvc.perform(put("/api/maladies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMaladie)))
            .andExpect(status().isOk());

        // Validate the Maladie in the database
        List<Maladie> maladieList = maladieRepository.findAll();
        assertThat(maladieList).hasSize(databaseSizeBeforeUpdate);
        Maladie testMaladie = maladieList.get(maladieList.size() - 1);
        assertThat(testMaladie.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testMaladie.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMaladie() throws Exception {
        int databaseSizeBeforeUpdate = maladieRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMaladieMockMvc.perform(put("/api/maladies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maladie)))
            .andExpect(status().isBadRequest());

        // Validate the Maladie in the database
        List<Maladie> maladieList = maladieRepository.findAll();
        assertThat(maladieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMaladie() throws Exception {
        // Initialize the database
        maladieRepository.saveAndFlush(maladie);

        int databaseSizeBeforeDelete = maladieRepository.findAll().size();

        // Delete the maladie
        restMaladieMockMvc.perform(delete("/api/maladies/{id}", maladie.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Maladie> maladieList = maladieRepository.findAll();
        assertThat(maladieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
