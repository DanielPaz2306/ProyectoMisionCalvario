package com.mision.calvario.dto;

import com.mision.calvario.entity.PastoresEntity;

public class PastorResponseDTO {

    private Long id;
    private String codigoPastor;
    private String nombre;
    private String apellido;
    private String celular;
    private int edad;
    private String codigoIglesia;
    private String nombreIglesia;
    private String codigoDistrito;
    private String nombreDistrito;
    private boolean esPastorDistrito;
    private String nitPastor;
    private Long iglesiaId;
    private Long distritoId;

    public PastorResponseDTO(){}

    public String getCodigoPastor() {
        return codigoPastor;
    }

    public void setCodigoPastor(String codigoPastor) {
        this.codigoPastor = codigoPastor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCodigoIglesia() {
        return codigoIglesia;
    }

    public void setCodigoIglesia(String codigoIglesia) {
        this.codigoIglesia = codigoIglesia;
    }

    public String getNombreIglesia() {
        return nombreIglesia;
    }

    public void setNombreIglesia(String nombreIglesia) {
        this.nombreIglesia = nombreIglesia;
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

    public boolean isEsPastorDistrito() {
        return esPastorDistrito;
    }

    public void setEsPastorDistrito(boolean esPastorDistrito) {
        this.esPastorDistrito = esPastorDistrito;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   

    public String getNitPastor() {
        return nitPastor;
    }

    public void setNitPastor(String nitPastor) {
        this.nitPastor = nitPastor;
    }

    public Long getIglesiaId() {
        return iglesiaId;
    }

    public void setIglesiaId(Long iglesiaId) {
        this.iglesiaId = iglesiaId;
    }

    public Long getDistritoId() {
        return distritoId;
    }

    public void setDistritoId(Long distritoId) {
        this.distritoId = distritoId;
    }
    public static PastorResponseDTO fromEntity(PastoresEntity pastor){
        PastorResponseDTO dto = new PastorResponseDTO();

        dto.setId(pastor.getId());
        dto.setCodigoPastor(pastor.getCodigoPastor());
        dto.setNombre(pastor.getNombre());
        dto.setApellido(pastor.getApellido());
        dto.setCelular(pastor.getCelular());
        dto.setEdad(pastor.getEdad());
        dto.setEsPastorDistrito(pastor.getEsPastorDistrito());

        dto.setNitPastor(pastor.getNitPastor());

        if(pastor.getIglesia() != null){
            dto.setIglesiaId(pastor.getIglesia().getId());
            dto.setCodigoIglesia(pastor.getIglesia().getCodigoIglesia());
            dto.setNombreIglesia(pastor.getIglesia().getNombreIglesia());
        }

        if(pastor.getDistrito() != null){
            dto.setDistritoId(pastor.getDistrito().getId());
            dto.setCodigoDistrito(pastor.getDistrito().getCodigoDistrito());
            dto.setNombreDistrito(pastor.getDistrito().getNombreDistrito());
        }

        return dto;

    }




}
