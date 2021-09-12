package com.macrosoftas.santeplantes.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

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
    @Column(name = "nom_commun", nullable = false, unique = true)
    private String nomCommun;

    @Column(name = "famille")
    private String famille;

    @Column(name = "genre")
    private String genre;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "photo_content_type")
    private String photoContentType;

    @NotNull
    @Size(max = 350)
    @Column(name = "bienfaits", length = 350, nullable = false)
    private String bienfaits;

    @Lob
    @Column(name = "image_bienfaits")
    private byte[] imageBienfaits;

    @Column(name = "image_bienfaits_content_type")
    private String imageBienfaitsContentType;

    @NotNull
    @Size(max = 300)
    @Column(name = "type_maladies", length = 300, nullable = false)
    private String typeMaladies;

    @NotNull
    @Size(max = 350)
    @Column(name = "maladies", length = 350, nullable = false)
    private String maladies;

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

    public byte[] getPhoto() {
        return photo;
    }

    public Plante photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public Plante photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getBienfaits() {
        return bienfaits;
    }

    public Plante bienfaits(String bienfaits) {
        this.bienfaits = bienfaits;
        return this;
    }

    public void setBienfaits(String bienfaits) {
        this.bienfaits = bienfaits;
    }

    public byte[] getImageBienfaits() {
        return imageBienfaits;
    }

    public Plante imageBienfaits(byte[] imageBienfaits) {
        this.imageBienfaits = imageBienfaits;
        return this;
    }

    public void setImageBienfaits(byte[] imageBienfaits) {
        this.imageBienfaits = imageBienfaits;
    }

    public String getImageBienfaitsContentType() {
        return imageBienfaitsContentType;
    }

    public Plante imageBienfaitsContentType(String imageBienfaitsContentType) {
        this.imageBienfaitsContentType = imageBienfaitsContentType;
        return this;
    }

    public void setImageBienfaitsContentType(String imageBienfaitsContentType) {
        this.imageBienfaitsContentType = imageBienfaitsContentType;
    }

    public String getTypeMaladies() {
        return typeMaladies;
    }

    public Plante typeMaladies(String typeMaladies) {
        this.typeMaladies = typeMaladies;
        return this;
    }

    public void setTypeMaladies(String typeMaladies) {
        this.typeMaladies = typeMaladies;
    }

    public String getMaladies() {
        return maladies;
    }

    public Plante maladies(String maladies) {
        this.maladies = maladies;
        return this;
    }

    public void setMaladies(String maladies) {
        this.maladies = maladies;
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
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + getPhotoContentType() + "'" +
            ", bienfaits='" + getBienfaits() + "'" +
            ", imageBienfaits='" + getImageBienfaits() + "'" +
            ", imageBienfaitsContentType='" + getImageBienfaitsContentType() + "'" +
            ", typeMaladies='" + getTypeMaladies() + "'" +
            ", maladies='" + getMaladies() + "'" +
            "}";
    }
}
