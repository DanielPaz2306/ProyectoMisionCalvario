package com.mision.calvario.dto;

import java.time.LocalDate;

public class DiezmoRequestDTO {

    private Long pastorId;
    private Long iglesiaId;
    private int mes;
    private int anio;
    private LocalDate fechaPago;
    private String numerotransaccion;
    private String numeroCuenta;
    private String banco;
    private double monto;
    private String observaciones;
    private String urlComprobante;
    private boolean verificado;

    public DiezmoRequestDTO() {}

    public Long getPastorId() {
        return pastorId;
    }

    public void setPastorId(Long pastorId) {
        this.pastorId = pastorId;
    }

    public Long getIglesiaId() {
        return iglesiaId;
    }

    public void setIglesiaId(Long iglesiaId) {
        this.iglesiaId = iglesiaId;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getNumerotransaccion() {
        return numerotransaccion;
    }

    public void setNumerotransaccion(String numerotransaccion) {
        this.numerotransaccion = numerotransaccion;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getUrlComprobante() {
        return urlComprobante;
    }

    public void setUrlComprobante(String urlComprobante) {
        this.urlComprobante = urlComprobante;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }
}
