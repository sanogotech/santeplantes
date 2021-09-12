package com.macrosoftas.santeplantes.web.rest;

import com.macrosoftas.santeplantes.SanteplantesApp;
import com.macrosoftas.santeplantes.domain.Plante;
import com.macrosoftas.santeplantes.repository.PlanteRepository;

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

/**
 * Integration tests for the {@link PlanteResource} REST controller.
 */
@SpringBootTest(classes = SanteplantesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PlanteResourceIT {

    private static final String DEFAULT_NOM_SCIENTIFIQUE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SCIENTIFIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_COMMUN = "AAAAAAAAAA";
    private static final String UPDATED_NOM_COMMUN = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILLE = "AAAAAAAAAA";
    private static final String UPDATED_FAMILLE = "BBBBBBBBBB";

    private static final String DEFAULT_GENRE = "AAAAAAAAAA";
    private static final String UPDATED_GENRE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_BIENFAITS = "AAAAAAAAAA";
    private static final String UPDATED_BIENFAITS = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE_BIENFAITS = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE_BIENFAITS = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_BIENFAITS_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_BIENFAITS_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_TYPE_MALADIES = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_MALADIES = "BBBBBBBBBB";

    private static final String DEFAULT_MALADIES = "AAAAAAAAAA";
    private static final String UPDATED_MALADIES = "BBBBBBBBBB";

    @Autowired
    private PlanteRepository planteRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlanteMockMvc;

    private Plante plante;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Plante createEntity(EntityManager em) {
        Plante plante = new Plante()
            .nomScientifique(DEFAULT_NOM_SCIENTIFIQUE)
            .nomCommun(DEFAULT_NOM_COMMUN)
            .famille(DEFAULT_FAMILLE)
            .genre(DEFAULT_GENRE)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .bienfaits(DEFAULT_BIENFAITS)
            .imageBienfaits(DEFAULT_IMAGE_BIENFAITS)
            .imageBienfaitsContentType(DEFAULT_IMAGE_BIENFAITS_CONTENT_TYPE)
            .typeMaladies(DEFAULT_TYPE_MALADIES)
            .maladies(DEFAULT_MALADIES);
        return plante;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Plante createUpdatedEntity(EntityManager em) {
        Plante plante = new Plante()
            .nomScientifique(UPDATED_NOM_SCIENTIFIQUE)
            .nomCommun(UPDATED_NOM_COMMUN)
            .famille(UPDATED_FAMILLE)
            .genre(UPDATED_GENRE)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .bienfaits(UPDATED_BIENFAITS)
            .imageBienfaits(UPDATED_IMAGE_BIENFAITS)
            .imageBienfaitsContentType(UPDATED_IMAGE_BIENFAITS_CONTENT_TYPE)
            .typeMaladies(UPDATED_TYPE_MALADIES)
            .maladies(UPDATED_MALADIES);
        return plante;
    }

    @BeforeEach
    public void initTest() {
        plante = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlante() throws Exception {
        int databaseSizeBeforeCreate = planteRepository.findAll().size();
        // Create the Plante
        restPlanteMockMvc.perform(post("/api/plantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(plante)))
            .andExpect(status().isCreated());

        // Validate the Plante in the database
        List<Plante> planteList = planteRepository.findAll();
        assertThat(planteList).hasSize(databaseSizeBeforeCreate + 1);
        Plante testPlante = planteList.get(planteList.size() - 1);
        assertThat(testPlante.getNomScientifique()).isEqualTo(DEFAULT_NOM_SCIENTIFIQUE);
        assertThat(testPlante.getNomCommun()).isEqualTo(DEFAULT_NOM_COMMUN);
        assertThat(testPlante.getFamille()).isEqualTo(DEFAULT_FAMILLE);
        assertThat(testPlante.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testPlante.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testPlante.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testPlante.getBienfaits()).isEqualTo(DEFAULT_BIENFAITS);
        assertThat(testPlante.getImageBienfaits()).isEqualTo(DEFAULT_IMAGE_BIENFAITS);
        assertThat(testPlante.getImageBienfaitsContentType()).isEqualTo(DEFAULT_IMAGE_BIENFAITS_CONTENT_TYPE);
        assertThat(testPlante.getTypeMaladies()).isEqualTo(DEFAULT_TYPE_MALADIES);
        assertThat(testPlante.getMaladies()).isEqualTo(DEFAULT_MALADIES);
    }

    @Test
    @Transactional
    public void createPlanteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planteRepository.findAll().size();

        // Create the Plante with an existing ID
        plante.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanteMockMvc.perform(post("/api/plantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(plante)))
            .andExpect(status().isBadRequest());

        // Validate the Plante in the database
        List<Plante> planteList = planteRepository.findAll();
        assertThat(planteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomCommunIsRequired() throws Exception {
        int databaseSizeBeforeTest = planteRepository.findAll().size();
        // set the field null
        plante.setNomCommun(null);

        // Create the Plante, which fails.


        restPlanteMockMvc.perform(post("/api/plantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(plante)))
            .andExpect(status().isBadRequest());

        List<Plante> planteList = planteRepository.findAll();
        assertThat(planteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBienfaitsIsRequired() throws Exception {
        int databaseSizeBeforeTest = planteRepository.findAll().size();
        // set the field null
        plante.setBienfaits(null);

        // Create the Plante, which fails.


        restPlanteMockMvc.perform(post("/api/plantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(plante)))
            .andExpect(status().isBadRequest());

        List<Plante> planteList = planteRepository.findAll();
        assertThat(planteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeMaladiesIsRequired() throws Exception {
        int databaseSizeBeforeTest = planteRepository.findAll().size();
        // set the field null
        plante.setTypeMaladies(null);

        // Create the Plante, which fails.


        restPlanteMockMvc.perform(post("/api/plantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(plante)))
            .andExpect(status().isBadRequest());

        List<Plante> planteList = planteRepository.findAll();
        assertThat(planteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaladiesIsRequired() throws Exception {
        int databaseSizeBeforeTest = planteRepository.findAll().size();
        // set the field null
        plante.setMaladies(null);

        // Create the Plante, which fails.


        restPlanteMockMvc.perform(post("/api/plantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(plante)))
            .andExpect(status().isBadRequest());

        List<Plante> planteList = planteRepository.findAll();
        assertThat(planteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlantes() throws Exception {
        // Initialize the database
        planteRepository.saveAndFlush(plante);

        // Get all the planteList
        restPlanteMockMvc.perform(get("/api/plantes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(plante.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomScientifique").value(hasItem(DEFAULT_NOM_SCIENTIFIQUE)))
            .andExpect(jsonPath("$.[*].nomCommun").value(hasItem(DEFAULT_NOM_COMMUN)))
            .andExpect(jsonPath("$.[*].famille").value(hasItem(DEFAULT_FAMILLE)))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].bienfaits").value(hasItem(DEFAULT_BIENFAITS)))
            .andExpect(jsonPath("$.[*].imageBienfaitsContentType").value(hasItem(DEFAULT_IMAGE_BIENFAITS_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imageBienfaits").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_BIENFAITS))))
            .andExpect(jsonPath("$.[*].typeMaladies").value(hasItem(DEFAULT_TYPE_MALADIES)))
            .andExpect(jsonPath("$.[*].maladies").value(hasItem(DEFAULT_MALADIES)));
    }
    
    @Test
    @Transactional
    public void getPlante() throws Exception {
        // Initialize the database
        planteRepository.saveAndFlush(plante);

        // Get the plante
        restPlanteMockMvc.perform(get("/api/plantes/{id}", plante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(plante.getId().intValue()))
            .andExpect(jsonPath("$.nomScientifique").value(DEFAULT_NOM_SCIENTIFIQUE))
            .andExpect(jsonPath("$.nomCommun").value(DEFAULT_NOM_COMMUN))
            .andExpect(jsonPath("$.famille").value(DEFAULT_FAMILLE))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.bienfaits").value(DEFAULT_BIENFAITS))
            .andExpect(jsonPath("$.imageBienfaitsContentType").value(DEFAULT_IMAGE_BIENFAITS_CONTENT_TYPE))
            .andExpect(jsonPath("$.imageBienfaits").value(Base64Utils.encodeToString(DEFAULT_IMAGE_BIENFAITS)))
            .andExpect(jsonPath("$.typeMaladies").value(DEFAULT_TYPE_MALADIES))
            .andExpect(jsonPath("$.maladies").value(DEFAULT_MALADIES));
    }
    @Test
    @Transactional
    public void getNonExistingPlante() throws Exception {
        // Get the plante
        restPlanteMockMvc.perform(get("/api/plantes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlante() throws Exception {
        // Initialize the database
        planteRepository.saveAndFlush(plante);

        int databaseSizeBeforeUpdate = planteRepository.findAll().size();

        // Update the plante
        Plante updatedPlante = planteRepository.findById(plante.getId()).get();
        // Disconnect from session so that the updates on updatedPlante are not directly saved in db
        em.detach(updatedPlante);
        updatedPlante
            .nomScientifique(UPDATED_NOM_SCIENTIFIQUE)
            .nomCommun(UPDATED_NOM_COMMUN)
            .famille(UPDATED_FAMILLE)
            .genre(UPDATED_GENRE)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .bienfaits(UPDATED_BIENFAITS)
            .imageBienfaits(UPDATED_IMAGE_BIENFAITS)
            .imageBienfaitsContentType(UPDATED_IMAGE_BIENFAITS_CONTENT_TYPE)
            .typeMaladies(UPDATED_TYPE_MALADIES)
            .maladies(UPDATED_MALADIES);

        restPlanteMockMvc.perform(put("/api/plantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPlante)))
            .andExpect(status().isOk());

        // Validate the Plante in the database
        List<Plante> planteList = planteRepository.findAll();
        assertThat(planteList).hasSize(databaseSizeBeforeUpdate);
        Plante testPlante = planteList.get(planteList.size() - 1);
        assertThat(testPlante.getNomScientifique()).isEqualTo(UPDATED_NOM_SCIENTIFIQUE);
        assertThat(testPlante.getNomCommun()).isEqualTo(UPDATED_NOM_COMMUN);
        assertThat(testPlante.getFamille()).isEqualTo(UPDATED_FAMILLE);
        assertThat(testPlante.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testPlante.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testPlante.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testPlante.getBienfaits()).isEqualTo(UPDATED_BIENFAITS);
        assertThat(testPlante.getImageBienfaits()).isEqualTo(UPDATED_IMAGE_BIENFAITS);
        assertThat(testPlante.getImageBienfaitsContentType()).isEqualTo(UPDATED_IMAGE_BIENFAITS_CONTENT_TYPE);
        assertThat(testPlante.getTypeMaladies()).isEqualTo(UPDATED_TYPE_MALADIES);
        assertThat(testPlante.getMaladies()).isEqualTo(UPDATED_MALADIES);
    }

    @Test
    @Transactional
    public void updateNonExistingPlante() throws Exception {
        int databaseSizeBeforeUpdate = planteRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanteMockMvc.perform(put("/api/plantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(plante)))
            .andExpect(status().isBadRequest());

        // Validate the Plante in the database
        List<Plante> planteList = planteRepository.findAll();
        assertThat(planteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlante() throws Exception {
        // Initialize the database
        planteRepository.saveAndFlush(plante);

        int databaseSizeBeforeDelete = planteRepository.findAll().size();

        // Delete the plante
        restPlanteMockMvc.perform(delete("/api/plantes/{id}", plante.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Plante> planteList = planteRepository.findAll();
        assertThat(planteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
