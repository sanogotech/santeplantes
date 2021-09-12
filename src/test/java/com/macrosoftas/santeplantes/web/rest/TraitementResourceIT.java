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
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.macrosoftas.santeplantes.domain.enumeration.Fiabilite;
import com.macrosoftas.santeplantes.domain.enumeration.TypeExtraction;
import com.macrosoftas.santeplantes.domain.enumeration.TypeTraitement;
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

    private static final TypeExtraction DEFAULT_TYPE_EXTRACTION = TypeExtraction.Infusion;
    private static final TypeExtraction UPDATED_TYPE_EXTRACTION = TypeExtraction.Decoction;

    private static final String DEFAULT_MIXTURE_ETPOSOLOGIE = "AAAAAAAAAA";
    private static final String UPDATED_MIXTURE_ETPOSOLOGIE = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_INFOS = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_INFOS = "BBBBBBBBBB";

    private static final TypeTraitement DEFAULT_TYPE_TRAITEMENT = TypeTraitement.Preventif;
    private static final TypeTraitement UPDATED_TYPE_TRAITEMENT = TypeTraitement.Curatif;

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
            .fiabilite(DEFAULT_FIABILITE)
            .typeExtraction(DEFAULT_TYPE_EXTRACTION)
            .mixtureEtposologie(DEFAULT_MIXTURE_ETPOSOLOGIE)
            .sourceInfos(DEFAULT_SOURCE_INFOS)
            .typeTraitement(DEFAULT_TYPE_TRAITEMENT);
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
            .fiabilite(UPDATED_FIABILITE)
            .typeExtraction(UPDATED_TYPE_EXTRACTION)
            .mixtureEtposologie(UPDATED_MIXTURE_ETPOSOLOGIE)
            .sourceInfos(UPDATED_SOURCE_INFOS)
            .typeTraitement(UPDATED_TYPE_TRAITEMENT);
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
        assertThat(testTraitement.getTypeExtraction()).isEqualTo(DEFAULT_TYPE_EXTRACTION);
        assertThat(testTraitement.getMixtureEtposologie()).isEqualTo(DEFAULT_MIXTURE_ETPOSOLOGIE);
        assertThat(testTraitement.getSourceInfos()).isEqualTo(DEFAULT_SOURCE_INFOS);
        assertThat(testTraitement.getTypeTraitement()).isEqualTo(DEFAULT_TYPE_TRAITEMENT);
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
    public void checkTypeExtractionIsRequired() throws Exception {
        int databaseSizeBeforeTest = traitementRepository.findAll().size();
        // set the field null
        traitement.setTypeExtraction(null);

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
            .andExpect(jsonPath("$.[*].fiabilite").value(hasItem(DEFAULT_FIABILITE.toString())))
            .andExpect(jsonPath("$.[*].typeExtraction").value(hasItem(DEFAULT_TYPE_EXTRACTION.toString())))
            .andExpect(jsonPath("$.[*].mixtureEtposologie").value(hasItem(DEFAULT_MIXTURE_ETPOSOLOGIE.toString())))
            .andExpect(jsonPath("$.[*].sourceInfos").value(hasItem(DEFAULT_SOURCE_INFOS.toString())))
            .andExpect(jsonPath("$.[*].typeTraitement").value(hasItem(DEFAULT_TYPE_TRAITEMENT.toString())));
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
            .andExpect(jsonPath("$.fiabilite").value(DEFAULT_FIABILITE.toString()))
            .andExpect(jsonPath("$.typeExtraction").value(DEFAULT_TYPE_EXTRACTION.toString()))
            .andExpect(jsonPath("$.mixtureEtposologie").value(DEFAULT_MIXTURE_ETPOSOLOGIE.toString()))
            .andExpect(jsonPath("$.sourceInfos").value(DEFAULT_SOURCE_INFOS.toString()))
            .andExpect(jsonPath("$.typeTraitement").value(DEFAULT_TYPE_TRAITEMENT.toString()));
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
            .fiabilite(UPDATED_FIABILITE)
            .typeExtraction(UPDATED_TYPE_EXTRACTION)
            .mixtureEtposologie(UPDATED_MIXTURE_ETPOSOLOGIE)
            .sourceInfos(UPDATED_SOURCE_INFOS)
            .typeTraitement(UPDATED_TYPE_TRAITEMENT);

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
        assertThat(testTraitement.getTypeExtraction()).isEqualTo(UPDATED_TYPE_EXTRACTION);
        assertThat(testTraitement.getMixtureEtposologie()).isEqualTo(UPDATED_MIXTURE_ETPOSOLOGIE);
        assertThat(testTraitement.getSourceInfos()).isEqualTo(UPDATED_SOURCE_INFOS);
        assertThat(testTraitement.getTypeTraitement()).isEqualTo(UPDATED_TYPE_TRAITEMENT);
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
