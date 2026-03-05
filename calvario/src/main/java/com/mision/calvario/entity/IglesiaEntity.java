package com.mision.calvario.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "iglesias")
public class IglesiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "codigoiglesia", nullable = false, length = 10)
    private String codigoiglesia;

    @Column(name = "nombreiglesia", nullable = false, length = 99)
    private String nombreiglesia;

    @OneToOne(mappedBy = "iglesia")
    private PastoresEntity pastor;

    public IglesiaEntity() {
    }

    public IglesiaEntity(long id, String codigoiglesia, String nombreiglesia, PastoresEntity pastor) {
        this.id = id;
        this.codigoiglesia = codigoiglesia;
        this.nombreiglesia = nombreiglesia;
        this.pastor = pastor;
    }

    public IglesiaEntity(long id, String codigoiglesia, String nombreiglesia) {
        this.id = id;
        this.codigoiglesia = codigoiglesia;
        this.nombreiglesia = nombreiglesia;
        this.pastor = null;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigoiglesia() {
        return codigoiglesia;
    }

    public void setCodigoiglesia(String codigoiglesia) {
        this.codigoiglesia = codigoiglesia;
    }

    public String getNombreiglesia() {
        return nombreiglesia;
    }

    public void setNombreiglesia(String nombreiglesia) {
        this.nombreiglesia = nombreiglesia;
    }

    public PastoresEntity getPastor() {
        return pastor;
    }

    public void setPastor(PastoresEntity pastor) {
        this.pastor = pastor;
    }

}
