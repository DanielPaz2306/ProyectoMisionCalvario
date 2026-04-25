package com.mision.calvario.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "rol", nullable = false, length = 20)
    private String rol; //PASTOR - PASTORDISTRITO - ASISTENTEAP - ADMINISTRADOR

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @OneToOne
    @JoinColumn(name = "pastorId", nullable = true)
    private PastoresEntity pastor;

    public UsuarioEntity(){}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public PastoresEntity getPastor(){
        return pastor;
    }
    public void setPastor(PastoresEntity pastor){
        this.pastor = pastor;
    }

}
