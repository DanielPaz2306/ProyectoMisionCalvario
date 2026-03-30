package com.mision.calvario.exception;


public class ErrorResponse {

    public int status;
    public String mensaje;

    public ErrorResponse (int status, String mensaje){
        this.status = status;
        this.mensaje = mensaje;
    }

    public int getStatus(){
        return status;
    }

    public String getMensaje(){
        return mensaje;
    }

}
