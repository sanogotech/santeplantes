package com.macrosoftas.santeplantes.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Bienfait.
 */
@Entity
@Table(name = "bienfait")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Bienfait implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "vitamine")
    private String vitamine;

    @Column(name = "mineraux")
    private String mineraux;

    @OneToMany(mappedBy = "bienfait")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Plante> plantes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "bienfaits", allowSetters = true)
    private Plante plante;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Bienfait nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public Bienfait description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVitamine() {
        return vitamine;
    }

    public Bienfait vitamine(String vitamine) {
        this.vitamine = vitamine;
        return this;
    }

    public void setVitamine(String vitamine) {
        this.vitamine = vitamine;
    }

    public String getMineraux() {
        return mineraux;
    }

    public Bienfait mineraux(String mineraux) {
        this.mineraux = mineraux;
        return this;
    }

    public void setMineraux(String mineraux) {
        this.mineraux = mineraux;
    }

    public Set<Plante> getPlantes() {
        return plantes;
    }

    public Bienfait plantes(Set<Plante> plantes) {
        this.plantes = plantes;
        return this;
    }

    public Bienfait addPlante(Plante plante) {
        this.plantes.add(plante);
        plante.setBienfait(this);
        return this;
    }

    public Bienfait removePlante(Plante plante) {
        this.plantes.remove(plante);
        plante.setBienfait(null);
        return this;
    }

    public void setPlantes(Set<Plante> plantes) {
        this.plantes = plantes;
    }

    public Plante getPlante() {
        return plante;
    }

    public Bienfait plante(Plante plante) {
        this.plante = plante;
        return this;
    }

    public void setPlante(Plante plante) {
        this.plante = plante;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bienfait)) {
            return false;
        }
        return id != null && id.equals(((Bienfait) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bienfait{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", description='" + getDescription() + "'" +
            ", vitamine='" + getVitamine() + "'" +
            ", mineraux='" + getMineraux() + "'" +
            "}";
    }
}
