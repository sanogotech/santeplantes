package com.macrosoftas.santeplantes.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.macrosoftas.santeplantes.domain.enumeration.Fiabilite;

import com.macrosoftas.santeplantes.domain.enumeration.TypeExtraction;

import com.macrosoftas.santeplantes.domain.enumeration.TypeTraitement;

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
    @Column(name = "nom", nullable = false, unique = true)
    private String nom;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "fiabilite", nullable = false)
    private Fiabilite fiabilite;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_extraction", nullable = false)
    private TypeExtraction typeExtraction;

    
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "mixture_etposologie", nullable = false)
    private String mixtureEtposologie;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "source_infos")
    private String sourceInfos;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_traitement")
    private TypeTraitement typeTraitement;

    @ManyToOne
    @JsonIgnoreProperties(value = "traitements", allowSetters = true)
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

    public TypeExtraction getTypeExtraction() {
        return typeExtraction;
    }

    public Traitement typeExtraction(TypeExtraction typeExtraction) {
        this.typeExtraction = typeExtraction;
        return this;
    }

    public void setTypeExtraction(TypeExtraction typeExtraction) {
        this.typeExtraction = typeExtraction;
    }

    public String getMixtureEtposologie() {
        return mixtureEtposologie;
    }

    public Traitement mixtureEtposologie(String mixtureEtposologie) {
        this.mixtureEtposologie = mixtureEtposologie;
        return this;
    }

    public void setMixtureEtposologie(String mixtureEtposologie) {
        this.mixtureEtposologie = mixtureEtposologie;
    }

    public String getSourceInfos() {
        return sourceInfos;
    }

    public Traitement sourceInfos(String sourceInfos) {
        this.sourceInfos = sourceInfos;
        return this;
    }

    public void setSourceInfos(String sourceInfos) {
        this.sourceInfos = sourceInfos;
    }

    public TypeTraitement getTypeTraitement() {
        return typeTraitement;
    }

    public Traitement typeTraitement(TypeTraitement typeTraitement) {
        this.typeTraitement = typeTraitement;
        return this;
    }

    public void setTypeTraitement(TypeTraitement typeTraitement) {
        this.typeTraitement = typeTraitement;
    }

    public Plante getPlante() {
        return plante;
    }

    public Traitement plante(Plante plante) {
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
            ", typeExtraction='" + getTypeExtraction() + "'" +
            ", mixtureEtposologie='" + getMixtureEtposologie() + "'" +
            ", sourceInfos='" + getSourceInfos() + "'" +
            ", typeTraitement='" + getTypeTraitement() + "'" +
            "}";
    }
}
