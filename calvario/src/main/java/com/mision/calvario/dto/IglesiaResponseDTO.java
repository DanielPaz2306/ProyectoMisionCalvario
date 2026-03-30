package com.mision.calvario.dto;

import com.mision.calvario.entity.IglesiaEntity;

public class IglesiaResponseDTO {

    private String codigoIglesia;
    private String nombreIglesia;
    private String codigoDistrito;
    private String nombreDistrito;
    private String codigoPastor;
    private String nombrePastor; //aca ira el nombre y su apellido
    private String celularPastor;

    public IglesiaResponseDTO(){}

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

    public String getCodigoPastor() {
        return codigoPastor;
    }
    public void setCodigoPastor(String codigoPastor) {
        this.codigoPastor = codigoPastor;
    }

    public String getNombrePastor() {
        return nombrePastor;
    }
    public void setNombrePastor(String nombrePastor) {
        this.nombrePastor = nombrePastor;
    }

    public String getCelularPastor() {
        return celularPastor;
    }
    public void setCelularPastor(String celularPastor) {
        this.celularPastor = celularPastor;
    }

    public static IglesiaResponseDTO fromEntity(IglesiaEntity iglesia){
        IglesiaResponseDTO dto = new IglesiaResponseDTO();

        dto.setCodigoIglesia(iglesia.getCodigoIglesia());
        dto.setNombreIglesia(iglesia.getCodigoIglesia());

        if(iglesia.getDistrito() != null){
            dto.setCodigoDistrito(iglesia.getDistrito().getCodigoDistrito());
            dto.setNombreDistrito(iglesia.getDistrito().getNombreDistrito());
        }

        if(iglesia.getPastor() != null){
            dto.setCodigoPastor(iglesia.getPastor().getCodigoPastor());
            dto.setNombrePastor(iglesia.getPastor().getNombre() + " " + iglesia.getPastor().getApellido());
            dto.setCelularPastor(iglesia.getPastor().getCelular());
        }

        return dto;

    }


}
