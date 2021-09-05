package com.macrosoftas.santeplantes.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.macrosoftas.santeplantes.domain.enumeration.Fiabilite;

/**
 * A Traitement.
 */
@Entity
@Table(name = "traitement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Traitement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "fiabilite", nullable = false)
    private Fiabilite fiabilite;

    @OneToMany(mappedBy = "traitement")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Maladie> maladies = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "traitements", allowSetters = true)
    private Maladie maladie;

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

    public Traitement nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Fiabilite getFiabilite() {
        return fiabilite;
    }

    public Traitement fiabilite(Fiabilite fiabilite) {
        this.fiabilite = fiabilite;
        return this;
    }

    public void setFiabilite(Fiabilite fiabilite) {
        this.fiabilite = fiabilite;
    }

    public Set<Maladie> getMaladies() {
        return maladies;
    }

    public Traitement maladies(Set<Maladie> maladies) {
        this.maladies = maladies;
        return this;
    }

    public Traitement addMaladie(Maladie maladie) {
        this.maladies.add(maladie);
        maladie.setTraitement(this);
        return this;
    }

    public Traitement removeMaladie(Maladie maladie) {
        this.maladies.remove(maladie);
        maladie.setTraitement(null);
        return this;
    }

    public void setMaladies(Set<Maladie> maladies) {
        this.maladies = maladies;
    }

    public Maladie getMaladie() {
        return maladie;
    }

    public Traitement maladie(Maladie maladie) {
        this.maladie = maladie;
        return this;
    }

    public void setMaladie(Maladie maladie) {
        this.maladie = maladie;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Traitement)) {
            return false;
        }
        return id != null && id.equals(((Traitement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Traitement{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", fiabilite='" + getFiabilite() + "'" +
            "}";
    }
}
