package com.mision.calvario.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "pastores")
public class PastoresEntity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@Column(name = "codigopastor", nullable = true, length = 20)
private String codigopastor;

@Column(name = "nombre", nullable = false, length = 50)
private String nombre;
//HOLA
@Column(name = "apellido", nullable = false, length = 50)
private String apellido;

@Column(name = "celular", nullable = true, length = 10)
private String celular;

@Column(name = "edad", nullable = false, length = 3)
private int edad;

@OneToOne
@JoinColumn(name = "iglesia_id", nullable = true)
private IglesiaEntity iglesia;

@ManyToOne
@JoinColumn(name = "distrito_id")
private DistritoEntity distrito;

@Column(name = "es_pastor_distrito")
private boolean esPastorDistrito;

public PastoresEntity(long id, String nombre, String apellido, String celular, int edad, IglesiaEntity iglesia, DistritoEntity distrito, boolean esPastorDistrito){
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;  // CONSTRUCTOR CON CELULAR
    this.celular = celular;
    this.edad = edad;
    this.iglesia = iglesia;
    this.distrito = distrito;
    this.esPastorDistrito = esPastorDistrito;
}

public PastoresEntity(long id, String nombre, String apellido, int edad, IglesiaEntity iglesia, DistritoEntity distrito, boolean esPastorDistrito){
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;  // CONSTRUCTOR SIN CELULAR
    this.celular = null;
    this.edad = edad;
    this.iglesia = iglesia;
    this.distrito = distrito;
    this.esPastorDistrito = esPastorDistrito;
}

public PastoresEntity() {
}

public long getId(){
    return id;
}
public String getCodigoPastor(){
    return codigopastor;
}
public String getNombre(){
    return nombre;
}
public String getApellido(){
    return apellido;
}
public String getCelular(){
    return celular;
}
public int getEdad(){
    return edad;
}
public DistritoEntity getDistrito(){
    return distrito;
}
public IglesiaEntity getIglesia(){
    return iglesia;
}
public String getCodigoIglesia(){
    return iglesia.getCodigoiglesia();
}
public String getCodigoDistrito(){
    return distrito.getCodigoDistrito();
}
public boolean getEsPastorDistrito(){
    return esPastorDistrito;
}


public void setId(long id){
    this.id = id;
}
public void setCodigoPastor(String codigopastor){
    this.codigopastor = codigopastor;
}
public void setNombre(String nombre){
    this.nombre = nombre;
}
public void setApellido(String apellido){
    this.apellido = apellido;
}
public void setCelular(String celular){
    this.celular = celular;
}
public void setEdad(int edad){
    this.edad = edad;
}
public void setDistrito(DistritoEntity distrito){
    this.distrito = distrito;
}
public void setIglesia(IglesiaEntity iglesia){
    this.iglesia = iglesia;
}
public void setEsPastorDistrito(boolean esPastorDistrito){
    this.esPastorDistrito = esPastorDistrito;
}



}
