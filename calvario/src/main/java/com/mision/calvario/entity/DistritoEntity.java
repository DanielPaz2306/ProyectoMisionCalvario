package com.mision.calvario.entity;

import jakarta.persistence.*;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "distritos")
public class DistritoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(name = "codigodistrito", nullable = false, length = 10)
    private String codigoDistrito;

    @Column(name = "nombredistrito", nullable = false, length = 99)
    private String nombreDistrito;


    @OneToOne
    @JoinColumn(name = "pastor_distrito_id", nullable = true)
    private PastoresEntity pastorDistrito;

    @JsonIgnore   
    @OneToMany(mappedBy = "distrito")
    private List<PastoresEntity> pastores; 
    
    @JsonIgnore
    @OneToMany(mappedBy = "distrito")
    private List<IglesiaEntity> iglesias;

    public DistritoEntity() {
    }

    

    public DistritoEntity(Long id, String codigoDistrito, String nombreDistrito, PastoresEntity pastorDistrito, List<PastoresEntity> pastores) {
        this.id = id;
        this.codigoDistrito = codigoDistrito;
        this.nombreDistrito = nombreDistrito;
        this.pastorDistrito = pastorDistrito;
        this.pastores = pastores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoDistrito() {
        return codigoDistrito;
    }

    public void setCodigoDistrito(String codigoDistrito) {
        this.codigoDistrito = codigoDistrito;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public PastoresEntity getPastorDistrito() {
        return pastorDistrito;
    }

    public void setPastorDistrito(PastoresEntity pastorDistrito) {
        this.pastorDistrito = pastorDistrito;
    }

    public List<PastoresEntity> getPastores() {
        return pastores;
    }

    public void setPastores(List<PastoresEntity> pastores) {
        this.pastores = pastores;
    }



    public List<IglesiaEntity> getIglesias() {
        return iglesias;
    }
    public void setIglesias(List<IglesiaEntity> iglesias) {
        this.iglesias = iglesias;
    }

    

}
