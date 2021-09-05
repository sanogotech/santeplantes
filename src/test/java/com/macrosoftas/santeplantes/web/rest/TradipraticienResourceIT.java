package com.macrosoftas.santeplantes.web.rest;

import com.macrosoftas.santeplantes.SanteplantesApp;
import com.macrosoftas.santeplantes.domain.Tradipraticien;
import com.macrosoftas.santeplantes.repository.TradipraticienRepository;

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
 * Integration tests for the {@link TradipraticienResource} REST controller.
 */
@SpringBootTest(classes = SanteplantesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TradipraticienResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_ENTREPRISE = "AAAAAAAAAA";
    private static final String UPDATED_ENTREPRISE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_VILLE = "AAAAAAAAAA";
    private static final String UPDATED_VILLE = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final Fiabilite DEFAULT_FIABILITE = Fiabilite.HAUT;
    private static final Fiabilite UPDATED_FIABILITE = Fiabilite.MOYEN;

    private static final Boolean DEFAULT_RAS = false;
    private static final Boolean UPDATED_RAS = true;

    @Autowired
    private TradipraticienRepository tradipraticienRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTradipraticienMockMvc;

    private Tradipraticien tradipraticien;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tradipraticien createEntity(EntityManager em) {
        Tradipraticien tradipraticien = new Tradipraticien()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .entreprise(DEFAULT_ENTREPRISE)
            .phone(DEFAULT_PHONE)
            .ville(DEFAULT_VILLE)
            .adresse(DEFAULT_ADRESSE)
            .fiabilite(DEFAULT_FIABILITE)
            .ras(DEFAULT_RAS);
        return tradipraticien;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tradipraticien createUpdatedEntity(EntityManager em) {
        Tradipraticien tradipraticien = new Tradipraticien()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .entreprise(UPDATED_ENTREPRISE)
            .phone(UPDATED_PHONE)
            .ville(UPDATED_VILLE)
            .adresse(UPDATED_ADRESSE)
            .fiabilite(UPDATED_FIABILITE)
            .ras(UPDATED_RAS);
        return tradipraticien;
    }

    @BeforeEach
    public void initTest() {
        tradipraticien = createEntity(em);
    }

    @Test
    @Transactional
    public void createTradipraticien() throws Exception {
        int databaseSizeBeforeCreate = tradipraticienRepository.findAll().size();
        // Create the Tradipraticien
        restTradipraticienMockMvc.perform(post("/api/tradipraticiens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tradipraticien)))
            .andExpect(status().isCreated());

        // Validate the Tradipraticien in the database
        List<Tradipraticien> tradipraticienList = tradipraticienRepository.findAll();
        assertThat(tradipraticienList).hasSize(databaseSizeBeforeCreate + 1);
        Tradipraticien testTradipraticien = tradipraticienList.get(tradipraticienList.size() - 1);
        assertThat(testTradipraticien.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testTradipraticien.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testTradipraticien.getEntreprise()).isEqualTo(DEFAULT_ENTREPRISE);
        assertThat(testTradipraticien.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testTradipraticien.getVille()).isEqualTo(DEFAULT_VILLE);
        assertThat(testTradipraticien.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testTradipraticien.getFiabilite()).isEqualTo(DEFAULT_FIABILITE);
        assertThat(testTradipraticien.isRas()).isEqualTo(DEFAULT_RAS);
    }

    @Test
    @Transactional
    public void createTradipraticienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tradipraticienRepository.findAll().size();

        // Create the Tradipraticien with an existing ID
        tradipraticien.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTradipraticienMockMvc.perform(post("/api/tradipraticiens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tradipraticien)))
            .andExpect(status().isBadRequest());

        // Validate the Tradipraticien in the database
        List<Tradipraticien> tradipraticienList = tradipraticienRepository.findAll();
        assertThat(tradipraticienList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = tradipraticienRepository.findAll().size();
        // set the field null
        tradipraticien.setNom(null);

        // Create the Tradipraticien, which fails.


        restTradipraticienMockMvc.perform(post("/api/tradipraticiens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tradipraticien)))
            .andExpect(status().isBadRequest());

        List<Tradipraticien> tradipraticienList = tradipraticienRepository.findAll();
        assertThat(tradipraticienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = tradipraticienRepository.findAll().size();
        // set the field null
        tradipraticien.setPrenom(null);

        // Create the Tradipraticien, which fails.


        restTradipraticienMockMvc.perform(post("/api/tradipraticiens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tradipraticien)))
            .andExpect(status().isBadRequest());

        List<Tradipraticien> tradipraticienList = tradipraticienRepository.findAll();
        assertThat(tradipraticienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEntrepriseIsRequired() throws Exception {
        int databaseSizeBeforeTest = tradipraticienRepository.findAll().size();
        // set the field null
        tradipraticien.setEntreprise(null);

        // Create the Tradipraticien, which fails.


        restTradipraticienMockMvc.perform(post("/api/tradipraticiens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tradipraticien)))
            .andExpect(status().isBadRequest());

        List<Tradipraticien> tradipraticienList = tradipraticienRepository.findAll();
        assertThat(tradipraticienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = tradipraticienRepository.findAll().size();
        // set the field null
        tradipraticien.setPhone(null);

        // Create the Tradipraticien, which fails.


        restTradipraticienMockMvc.perform(post("/api/tradipraticiens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tradipraticien)))
            .andExpect(status().isBadRequest());

        List<Tradipraticien> tradipraticienList = tradipraticienRepository.findAll();
        assertThat(tradipraticienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVilleIsRequired() throws Exception {
        int databaseSizeBeforeTest = tradipraticienRepository.findAll().size();
        // set the field null
        tradipraticien.setVille(null);

        // Create the Tradipraticien, which fails.


        restTradipraticienMockMvc.perform(post("/api/tradipraticiens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tradipraticien)))
            .andExpect(status().isBadRequest());

        List<Tradipraticien> tradipraticienList = tradipraticienRepository.findAll();
        assertThat(tradipraticienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseIsRequired() throws Exception {
        int databaseSizeBeforeTest = tradipraticienRepository.findAll().size();
        // set the field null
        tradipraticien.setAdresse(null);

        // Create the Tradipraticien, which fails.


        restTradipraticienMockMvc.perform(post("/api/tradipraticiens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tradipraticien)))
            .andExpect(status().isBadRequest());

        List<Tradipraticien> tradipraticienList = tradipraticienRepository.findAll();
        assertThat(tradipraticienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFiabiliteIsRequired() throws Exception {
        int databaseSizeBeforeTest = tradipraticienRepository.findAll().size();
        // set the field null
        tradipraticien.setFiabilite(null);

        // Create the Tradipraticien, which fails.


        restTradipraticienMockMvc.perform(post("/api/tradipraticiens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tradipraticien)))
            .andExpect(status().isBadRequest());

        List<Tradipraticien> tradipraticienList = tradipraticienRepository.findAll();
        assertThat(tradipraticienList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTradipraticiens() throws Exception {
        // Initialize the database
        tradipraticienRepository.saveAndFlush(tradipraticien);

        // Get all the tradipraticienList
        restTradipraticienMockMvc.perform(get("/api/tradipraticiens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tradipraticien.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].entreprise").value(hasItem(DEFAULT_ENTREPRISE)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].fiabilite").value(hasItem(DEFAULT_FIABILITE.toString())))
            .andExpect(jsonPath("$.[*].ras").value(hasItem(DEFAULT_RAS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTradipraticien() throws Exception {
        // Initialize the database
        tradipraticienRepository.saveAndFlush(tradipraticien);

        // Get the tradipraticien
        restTradipraticienMockMvc.perform(get("/api/tradipraticiens/{id}", tradipraticien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tradipraticien.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.entreprise").value(DEFAULT_ENTREPRISE))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.fiabilite").value(DEFAULT_FIABILITE.toString()))
            .andExpect(jsonPath("$.ras").value(DEFAULT_RAS.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTradipraticien() throws Exception {
        // Get the tradipraticien
        restTradipraticienMockMvc.perform(get("/api/tradipraticiens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTradipraticien() throws Exception {
        // Initialize the database
        tradipraticienRepository.saveAndFlush(tradipraticien);

        int databaseSizeBeforeUpdate = tradipraticienRepository.findAll().size();

        // Update the tradipraticien
        Tradipraticien updatedTradipraticien = tradipraticienRepository.findById(tradipraticien.getId()).get();
        // Disconnect from session so that the updates on updatedTradipraticien are not directly saved in db
        em.detach(updatedTradipraticien);
        updatedTradipraticien
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .entreprise(UPDATED_ENTREPRISE)
            .phone(UPDATED_PHONE)
            .ville(UPDATED_VILLE)
            .adresse(UPDATED_ADRESSE)
            .fiabilite(UPDATED_FIABILITE)
            .ras(UPDATED_RAS);

        restTradipraticienMockMvc.perform(put("/api/tradipraticiens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTradipraticien)))
            .andExpect(status().isOk());

        // Validate the Tradipraticien in the database
        List<Tradipraticien> tradipraticienList = tradipraticienRepository.findAll();
        assertThat(tradipraticienList).hasSize(databaseSizeBeforeUpdate);
        Tradipraticien testTradipraticien = tradipraticienList.get(tradipraticienList.size() - 1);
        assertThat(testTradipraticien.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testTradipraticien.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testTradipraticien.getEntreprise()).isEqualTo(UPDATED_ENTREPRISE);
        assertThat(testTradipraticien.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testTradipraticien.getVille()).isEqualTo(UPDATED_VILLE);
        assertThat(testTradipraticien.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testTradipraticien.getFiabilite()).isEqualTo(UPDATED_FIABILITE);
        assertThat(testTradipraticien.isRas()).isEqualTo(UPDATED_RAS);
    }

    @Test
    @Transactional
    public void updateNonExistingTradipraticien() throws Exception {
        int databaseSizeBeforeUpdate = tradipraticienRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTradipraticienMockMvc.perform(put("/api/tradipraticiens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tradipraticien)))
            .andExpect(status().isBadRequest());

        // Validate the Tradipraticien in the database
        List<Tradipraticien> tradipraticienList = tradipraticienRepository.findAll();
        assertThat(tradipraticienList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTradipraticien() throws Exception {
        // Initialize the database
        tradipraticienRepository.saveAndFlush(tradipraticien);

        int databaseSizeBeforeDelete = tradipraticienRepository.findAll().size();

        // Delete the tradipraticien
        restTradipraticienMockMvc.perform(delete("/api/tradipraticiens/{id}", tradipraticien.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tradipraticien> tradipraticienList = tradipraticienRepository.findAll();
        assertThat(tradipraticienList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
