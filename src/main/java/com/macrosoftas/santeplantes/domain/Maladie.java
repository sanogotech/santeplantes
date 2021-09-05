package com.macrosoftas.santeplantes.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.macrosoftas.santeplantes.domain.enumeration.TypeMaladie;

/**
 * A Maladie.
 */
@Entity
@Table(name = "maladie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Maladie implements Serializable {

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
    @Column(name = "type", nullable = false)
    private TypeMaladie type;

    @OneToMany(mappedBy = "maladie")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Plante> plantes = new HashSet<>();

    @OneToMany(mappedBy = "maladie")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Tradipraticien> tradipraticiens = new HashSet<>();

    @OneToMany(mappedBy = "maladie")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Traitement> traitements = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "maladies", allowSetters = true)
    private Plante plante;

    @ManyToOne
    @JsonIgnoreProperties(value = "maladies", allowSetters = true)
    private Tradipraticien tradipraticien;

    @ManyToOne
    @JsonIgnoreProperties(value = "maladies", allowSetters = true)
    private Traitement traitement;

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

    public Maladie nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeMaladie getType() {
        return type;
    }

    public Maladie type(TypeMaladie type) {
        this.type = type;
        return this;
    }

    public void setType(TypeMaladie type) {
        this.type = type;
    }

    public Set<Plante> getPlantes() {
        return plantes;
    }

    public Maladie plantes(Set<Plante> plantes) {
        this.plantes = plantes;
        return this;
    }

    public Maladie addPlante(Plante plante) {
        this.plantes.add(plante);
        plante.setMaladie(this);
        return this;
    }

    public Maladie removePlante(Plante plante) {
        this.plantes.remove(plante);
        plante.setMaladie(null);
        return this;
    }

    public void setPlantes(Set<Plante> plantes) {
        this.plantes = plantes;
    }

    public Set<Tradipraticien> getTradipraticiens() {
        return tradipraticiens;
    }

    public Maladie tradipraticiens(Set<Tradipraticien> tradipraticiens) {
        this.tradipraticiens = tradipraticiens;
        return this;
    }

    public Maladie addTradipraticien(Tradipraticien tradipraticien) {
        this.tradipraticiens.add(tradipraticien);
        tradipraticien.setMaladie(this);
        return this;
    }

    public Maladie removeTradipraticien(Tradipraticien tradipraticien) {
        this.tradipraticiens.remove(tradipraticien);
        tradipraticien.setMaladie(null);
        return this;
    }

    public void setTradipraticiens(Set<Tradipraticien> tradipraticiens) {
        this.tradipraticiens = tradipraticiens;
    }

    public Set<Traitement> getTraitements() {
        return traitements;
    }

    public Maladie traitements(Set<Traitement> traitements) {
        this.traitements = traitements;
        return this;
    }

    public Maladie addTraitement(Traitement traitement) {
        this.traitements.add(traitement);
        traitement.setMaladie(this);
        return this;
    }

    public Maladie removeTraitement(Traitement traitement) {
        this.traitements.remove(traitement);
        traitement.setMaladie(null);
        return this;
    }

    public void setTraitements(Set<Traitement> traitements) {
        this.traitements = traitements;
    }

    public Plante getPlante() {
        return plante;
    }

    public Maladie plante(Plante plante) {
        this.plante = plante;
        return this;
    }

    public void setPlante(Plante plante) {
        this.plante = plante;
    }

    public Tradipraticien getTradipraticien() {
        return tradipraticien;
    }

    public Maladie tradipraticien(Tradipraticien tradipraticien) {
        this.tradipraticien = tradipraticien;
        return this;
    }

    public void setTradipraticien(Tradipraticien tradipraticien) {
        this.tradipraticien = tradipraticien;
    }

    public Traitement getTraitement() {
        return traitement;
    }

    public Maladie traitement(Traitement traitement) {
        this.traitement = traitement;
        return this;
    }

    public void setTraitement(Traitement traitement) {
        this.traitement = traitement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Maladie)) {
            return false;
        }
        return id != null && id.equals(((Maladie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Maladie{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
