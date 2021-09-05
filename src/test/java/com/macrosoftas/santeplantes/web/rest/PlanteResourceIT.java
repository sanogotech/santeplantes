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
            .genre(DEFAULT_GENRE);
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
            .genre(UPDATED_GENRE);
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
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE)));
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
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE));
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
            .genre(UPDATED_GENRE);

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
