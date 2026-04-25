package com.mision.calvario.dto;

public class LoginResponseDTO {

    private String token;
    private String username;
    private String rol;
    private Long pastorId;

    public LoginResponseDTO(){}

    public LoginResponseDTO(String token, String username, String rol, Long pastorId){
        this.token = token;
        this.username = username;
        this.rol = rol;
        this.pastorId = pastorId;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }  
    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPastorId(){
        return pastorId;
    }

    public void setPastorId(Long id){
        this.pastorId = id;
    }
    
}
