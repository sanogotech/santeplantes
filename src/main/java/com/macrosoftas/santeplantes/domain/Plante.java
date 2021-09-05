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
 * A Plante.
 */
@Entity
@Table(name = "plante")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Plante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom_scientifique")
    private String nomScientifique;

    @NotNull
    @Column(name = "nom_commun", nullable = false)
    private String nomCommun;

    @Column(name = "famille")
    private String famille;

    @Column(name = "genre")
    private String genre;

    @OneToMany(mappedBy = "plante")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Maladie> maladies = new HashSet<>();

    @OneToMany(mappedBy = "plante")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Bienfait> bienfaits = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "plantes", allowSetters = true)
    private Maladie maladie;

    @ManyToOne
    @JsonIgnoreProperties(value = "plantes", allowSetters = true)
    private Bienfait bienfait;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomScientifique() {
        return nomScientifique;
    }

    public Plante nomScientifique(String nomScientifique) {
        this.nomScientifique = nomScientifique;
        return this;
    }

    public void setNomScientifique(String nomScientifique) {
        this.nomScientifique = nomScientifique;
    }

    public String getNomCommun() {
        return nomCommun;
    }

    public Plante nomCommun(String nomCommun) {
        this.nomCommun = nomCommun;
        return this;
    }

    public void setNomCommun(String nomCommun) {
        this.nomCommun = nomCommun;
    }

    public String getFamille() {
        return famille;
    }

    public Plante famille(String famille) {
        this.famille = famille;
        return this;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public String getGenre() {
        return genre;
    }

    public Plante genre(String genre) {
        this.genre = genre;
        return this;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<Maladie> getMaladies() {
        return maladies;
    }

    public Plante maladies(Set<Maladie> maladies) {
        this.maladies = maladies;
        return this;
    }

    public Plante addMaladie(Maladie maladie) {
        this.maladies.add(maladie);
        maladie.setPlante(this);
        return this;
    }

    public Plante removeMaladie(Maladie maladie) {
        this.maladies.remove(maladie);
        maladie.setPlante(null);
        return this;
    }

    public void setMaladies(Set<Maladie> maladies) {
        this.maladies = maladies;
    }

    public Set<Bienfait> getBienfaits() {
        return bienfaits;
    }

    public Plante bienfaits(Set<Bienfait> bienfaits) {
        this.bienfaits = bienfaits;
        return this;
    }

    public Plante addBienfait(Bienfait bienfait) {
        this.bienfaits.add(bienfait);
        bienfait.setPlante(this);
        return this;
    }

    public Plante removeBienfait(Bienfait bienfait) {
        this.bienfaits.remove(bienfait);
        bienfait.setPlante(null);
        return this;
    }

    public void setBienfaits(Set<Bienfait> bienfaits) {
        this.bienfaits = bienfaits;
    }

    public Maladie getMaladie() {
        return maladie;
    }

    public Plante maladie(Maladie maladie) {
        this.maladie = maladie;
        return this;
    }

    public void setMaladie(Maladie maladie) {
        this.maladie = maladie;
    }

    public Bienfait getBienfait() {
        return bienfait;
    }

    public Plante bienfait(Bienfait bienfait) {
        this.bienfait = bienfait;
        return this;
    }

    public void setBienfait(Bienfait bienfait) {
        this.bienfait = bienfait;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plante)) {
            return false;
        }
        return id != null && id.equals(((Plante) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Plante{" +
            "id=" + getId() +
            ", nomScientifique='" + getNomScientifique() + "'" +
            ", nomCommun='" + getNomCommun() + "'" +
            ", famille='" + getFamille() + "'" +
            ", genre='" + getGenre() + "'" +
            "}";
    }
}
