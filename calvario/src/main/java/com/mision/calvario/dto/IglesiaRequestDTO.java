package com.mision.calvario.dto;

public class IglesiaRequestDTO {
    private String nombreIglesia;
    private Long distritoId;
    private Long pastorId;

    public String getNombreIglesia() { return nombreIglesia; }
    public void setNombreIglesia(String nombreIglesia) { this.nombreIglesia = nombreIglesia; }

    public Long getDistritoId() { return distritoId; }
    public void setDistritoId(Long distritoId) { this.distritoId = distritoId; }

    public Long getPastorId() { return pastorId; }
    public void setPastorId(Long pastorId) { this.pastorId = pastorId; }
}
