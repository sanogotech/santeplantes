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
 * A Tradipraticien.
 */
@Entity
@Table(name = "tradipraticien")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tradipraticien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotNull
    @Column(name = "entreprise", nullable = false)
    private String entreprise;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "ville", nullable = false)
    private String ville;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "fiabilite", nullable = false)
    private Fiabilite fiabilite;

    @Column(name = "ras")
    private Boolean ras;

    @OneToMany(mappedBy = "tradipraticien")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Maladie> maladies = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "tradipraticiens", allowSetters = true)
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

    public Tradipraticien nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Tradipraticien prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public Tradipraticien entreprise(String entreprise) {
        this.entreprise = entreprise;
        return this;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getPhone() {
        return phone;
    }

    public Tradipraticien phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVille() {
        return ville;
    }

    public Tradipraticien ville(String ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public Tradipraticien adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Fiabilite getFiabilite() {
        return fiabilite;
    }

    public Tradipraticien fiabilite(Fiabilite fiabilite) {
        this.fiabilite = fiabilite;
        return this;
    }

    public void setFiabilite(Fiabilite fiabilite) {
        this.fiabilite = fiabilite;
    }

    public Boolean isRas() {
        return ras;
    }

    public Tradipraticien ras(Boolean ras) {
        this.ras = ras;
        return this;
    }

    public void setRas(Boolean ras) {
        this.ras = ras;
    }

    public Set<Maladie> getMaladies() {
        return maladies;
    }

    public Tradipraticien maladies(Set<Maladie> maladies) {
        this.maladies = maladies;
        return this;
    }

    public Tradipraticien addMaladie(Maladie maladie) {
        this.maladies.add(maladie);
        maladie.setTradipraticien(this);
        return this;
    }

    public Tradipraticien removeMaladie(Maladie maladie) {
        this.maladies.remove(maladie);
        maladie.setTradipraticien(null);
        return this;
    }

    public void setMaladies(Set<Maladie> maladies) {
        this.maladies = maladies;
    }

    public Maladie getMaladie() {
        return maladie;
    }

    public Tradipraticien maladie(Maladie maladie) {
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
        if (!(o instanceof Tradipraticien)) {
            return false;
        }
        return id != null && id.equals(((Tradipraticien) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tradipraticien{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", entreprise='" + getEntreprise() + "'" +
            ", phone='" + getPhone() + "'" +
            ", ville='" + getVille() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", fiabilite='" + getFiabilite() + "'" +
            ", ras='" + isRas() + "'" +
            "}";
    }
}
