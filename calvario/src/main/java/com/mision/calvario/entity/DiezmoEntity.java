package com.mision.calvario.entity;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "diezmos")
public class DiezmoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pastor_id", nullable = false)
    private PastoresEntity pastor;

    @ManyToOne
    @JoinColumn(name = "iglesia_id", nullable = true)
    private IglesiaEntity iglesia;

    @Column(name = "nitPastor", nullable = true, length = 10)
    private String nitPastor;

    @Column(name = "mes", nullable = false)
    private int mes;

    @Column(name = "anio", nullable = false)
    private int anio;

    @Column(name = "fechapago", nullable = false)
    private LocalDate fechaPago;

    @Column(name = "numerotransaccion", nullable = false, unique = true, length = 50)
    private String numerotransaccion;

    @Column(name = "numeroCuenta", nullable = false, length = 30)
    private String numeroCuenta;

    @Column(name = "banco", nullable = false, length = 50)
    private String banco;

    @Column(name = "monto", nullable = false)
    private double monto;

    @Column(name = "observaciones", nullable = true)
    private String observaciones;

    @Column(name = "urlComprobante", nullable = true, length = 500)
    private String urlComprobante;

    public DiezmoEntity (){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public PastoresEntity getPastor() {
        return pastor;
    }
    public void setPastor(PastoresEntity pastor) {
        this.pastor = pastor;
    }

    public IglesiaEntity getIglesia() {
        return iglesia;
    }
    public void setIglesia(IglesiaEntity iglesia) {
        this.iglesia = iglesia;
    }

    public String getNitPastor() {
        return nitPastor;
    }
    public void setNitPastor(String nitPastor) {
        this.nitPastor = nitPastor;
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

    

}
