package com.mision.calvario.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "iglesias")
public class IglesiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigoiglesia", nullable = false, length = 10)
    private String codigoIglesia;  // ← camelCase

    @Column(name = "nombreiglesia", nullable = false, length = 99)
    private String nombreIglesia;  // ← camelCase

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distrito_id")
    private DistritoEntity distrito;

    @JsonIgnoreProperties("iglesia")
    @OneToOne(mappedBy = "iglesia", fetch = FetchType.LAZY)
    private PastoresEntity pastor;

    public IglesiaEntity() {
    }

    public IglesiaEntity(Long id, String codigoIglesia, String nombreIglesia, DistritoEntity distrito, PastoresEntity pastor) {
        this.id = id;
        this.codigoIglesia = codigoIglesia;
        this.nombreIglesia = nombreIglesia;
        this.distrito = distrito;
        this.pastor = pastor;
    }

    public IglesiaEntity(Long id, String codigoIglesia, String nombreIglesia, DistritoEntity distrito) {
        this.id = id;
        this.codigoIglesia = codigoIglesia;
        this.nombreIglesia = nombreIglesia;
        this.distrito = distrito;
        this.pastor = null;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoIglesia() { return codigoIglesia; }
    public void setCodigoIglesia(String codigoIglesia) { this.codigoIglesia = codigoIglesia; }

    public String getNombreIglesia() { return nombreIglesia; }
    public void setNombreIglesia(String nombreIglesia) { this.nombreIglesia = nombreIglesia; }

    public DistritoEntity getDistrito() { return distrito; }
    public void setDistrito(DistritoEntity distrito) { this.distrito = distrito; }

    public PastoresEntity getPastor() { return pastor; }
    public void setPastor(PastoresEntity pastor) { this.pastor = pastor; }
}