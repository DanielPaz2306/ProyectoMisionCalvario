package com.mision.calvario.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "pastores")
public class PastoresEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigopastor", nullable = true, length = 20)
    private String codigoPastor;  // ← camelCase

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "celular", nullable = true, length = 15)
    private String celular;

    @Column(name = "edad", nullable = false)
    private int edad;

    @Column(name = "nitpastor", nullable = true, length = 10)
    private String nitPastor;

    @JsonIgnoreProperties("pastor")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iglesia_id", nullable = true)
    private IglesiaEntity iglesia;

    @JsonIgnoreProperties({"pastorDistrito", "pastores"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distrito_id")
    private DistritoEntity distrito;

    @Column(name = "es_pastor_distrito")
    private boolean esPastorDistrito;

    public PastoresEntity() {}

    public PastoresEntity(Long id, String nombre, String apellido, String celular, int edad, IglesiaEntity iglesia, DistritoEntity distrito, boolean esPastorDistrito){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.edad = edad;
        this.iglesia = iglesia;
        this.distrito = distrito;
        this.esPastorDistrito = esPastorDistrito;
    }

    public PastoresEntity(Long id, String nombre, String apellido, int edad, IglesiaEntity iglesia, DistritoEntity distrito, boolean esPastorDistrito){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = null;
        this.edad = edad;
        this.iglesia = iglesia;
        this.distrito = distrito;
        this.esPastorDistrito = esPastorDistrito;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoPastor() { return codigoPastor; }
    public void setCodigoPastor(String codigoPastor) { this.codigoPastor = codigoPastor; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public IglesiaEntity getIglesia() { return iglesia; }
    public void setIglesia(IglesiaEntity iglesia) { this.iglesia = iglesia; }

    public DistritoEntity getDistrito() { return distrito; }
    public void setDistrito(DistritoEntity distrito) { this.distrito = distrito; }

    public boolean getEsPastorDistrito() { return esPastorDistrito; }
    public void setEsPastorDistrito(boolean esPastorDistrito) { this.esPastorDistrito = esPastorDistrito; }

    public String getCodigoIglesia() { 
        if(iglesia == null) return null;
        return iglesia.getCodigoIglesia(); 
    }   // ← actualizado
    public String getCodigoDistrito() { 
        return (distrito != null) ? distrito.getCodigoDistrito() : null; 
    }

    public String getNitPastor() {
    return nitPastor;
    }
    public void setNitPastor(String nitPastor) {
        this.nitPastor = nitPastor;
    }
    

}