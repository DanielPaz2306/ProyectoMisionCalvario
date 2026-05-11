package com.mision.calvario.dto;

import com.mision.calvario.entity.DiezmoEntity;
import java.time.LocalDate;

public class DiezmoResponseDTO {

    private Long id;
    private String codigoPastor;
    private String nombrePastor;
    private String nitPastor;
    private String codigoIglesia;
    private String nombreIglesia;
    private int mes;
    private int anio;
    private LocalDate fechaPago;
    private String numeroTransaccion;
    private String numeroCuenta;
    private String banco;
    private double monto;
    private String observaciones;
    private String urlComprobante;
    private boolean verificado;

    public DiezmoResponseDTO(){}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoPastor() { return codigoPastor; }
    public void setCodigoPastor(String codigoPastor) { this.codigoPastor = codigoPastor; }

    public String getNombrePastor() { return nombrePastor; }
    public void setNombrePastor(String nombrePastor) { this.nombrePastor = nombrePastor; }

    public String getNitPastor() { return nitPastor; }
    public void setNitPastor(String nitPastor) { this.nitPastor = nitPastor; }

    public String getCodigoIglesia() { return codigoIglesia; }
    public void setCodigoIglesia(String codigoIglesia) { this.codigoIglesia = codigoIglesia; }

    public String getNombreIglesia() { return nombreIglesia; }
    public void setNombreIglesia(String nombreIglesia) { this.nombreIglesia = nombreIglesia; }

    public int getMes() { return mes; }
    public void setMes(int mes) { this.mes = mes; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public LocalDate getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDate fechaPago) { this.fechaPago = fechaPago; }

    public String getNumerotransaccion() { return numeroTransaccion; }
    public void setNumerotransaccion(String numerotransaccion) { this.numeroTransaccion = numerotransaccion; }

    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getBanco() { return banco; }
    public void setBanco(String banco) { this.banco = banco; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getUrlComprobante() { return urlComprobante; }
    public void setUrlComprobante(String urlComprobante) { this.urlComprobante = urlComprobante; }

    public boolean isVerificado() { return verificado; }
    public void setVerificado(boolean verificado) { this.verificado = verificado; }


    public static DiezmoResponseDTO fromEntity(DiezmoEntity diezmo){

        DiezmoResponseDTO dto = new DiezmoResponseDTO();

        dto.setId(diezmo.getId());
        dto.setMes(diezmo.getMes());
        dto.setAnio(diezmo.getAnio());
        dto.setFechaPago(diezmo.getFechaPago());
        dto.setNumerotransaccion(diezmo.getNumerotransaccion());
        dto.setNumeroCuenta(diezmo.getNumeroCuenta());
        dto.setBanco(diezmo.getBanco());
        dto.setMonto(diezmo.getMonto());
        dto.setObservaciones(diezmo.getObservaciones());
        dto.setUrlComprobante(diezmo.getUrlComprobante());
        dto.setVerificado(diezmo.isVerificado());

        dto.setCodigoPastor(diezmo.getPastor().getCodigoPastor());
        dto.setNombrePastor(diezmo.getPastor().getNombre() + " " + diezmo.getPastor().getApellido());
        if(diezmo.getPastor().getNitPastor() == null || diezmo.getPastor().getNitPastor().isEmpty()){
            dto.setNitPastor("CF");
        }
        else{
            dto.setNitPastor(diezmo.getPastor().getNitPastor());
        }

        if(diezmo.getIglesia() != null){
            dto.setCodigoIglesia(diezmo.getIglesia().getCodigoIglesia());
            dto.setNombreIglesia(diezmo.getIglesia().getNombreIglesia());
        }
        
        return dto;


    }

}
