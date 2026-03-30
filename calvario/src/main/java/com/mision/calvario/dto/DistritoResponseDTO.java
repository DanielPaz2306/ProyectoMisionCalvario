package com.mision.calvario.dto;

import com.mision.calvario.entity.DistritoEntity;

public class DistritoResponseDTO {

    private String codigoDistrito;
    private String nombreDistrito;
    private String codigoPastorDistrito;
    private String nombrePastorDistrito; //nombre mas apellido tambien
    private String celularPastorDistrito;
    private int totalPastores;

    public DistritoResponseDTO(){}

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

    public String getCodigoPastorDistrito() {
        return codigoPastorDistrito;
    }
    public void setCodigoPastorDistrito(String codigoPastorDistrito) {
        this.codigoPastorDistrito = codigoPastorDistrito;
    }

    public String getNombrePastorDistrito() {
        return nombrePastorDistrito;
    }
    public void setNombrePastorDistrito(String nombrePastorDistrito) {
        this.nombrePastorDistrito = nombrePastorDistrito;
    }

    public String getCelularPastorDistrito() {
        return celularPastorDistrito;
    }
    public void setCelularPastorDistrito(String celularPastorDistrito) {
        this.celularPastorDistrito = celularPastorDistrito;
    }

    public int getTotalPastores() {
        return totalPastores;
    }
    public void setTotalPastores(int totalPastores) {
        this.totalPastores = totalPastores;
    }

    public static DistritoResponseDTO fromEntity(DistritoEntity distrito){

        DistritoResponseDTO dto = new DistritoResponseDTO();

        dto.setCodigoDistrito(distrito.getCodigoDistrito());
        dto.setNombreDistrito(distrito.getNombreDistrito());

        if(distrito.getPastorDistrito() != null){
            dto.setCodigoPastorDistrito(distrito.getPastorDistrito().getCodigoPastor());
            dto.setNombrePastorDistrito(distrito.getPastorDistrito().getNombre() + " " + distrito.getPastorDistrito().getApellido());
            dto.setCelularPastorDistrito(distrito.getPastorDistrito().getCelular());
        }

        if(distrito.getPastores() != null){
            dto.setTotalPastores(distrito.getPastores().size());
        }

        return dto;

    }



}
