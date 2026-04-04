package com.mision.calvario.service;

import com.mision.calvario.entity.*;
import java.util.Optional;
import java.util.List;

public interface DistritoService {

    Optional<DistritoEntity> buscarPorNombre(String nombre); //Para buscar el distrito por el nombre del distrito

    List<DistritoEntity> buscarSinPastor();

    Optional<DistritoEntity> buscarPorNombrePastorDistrito(String nombre);

    DistritoEntity guardar(DistritoEntity distrito); // Para guardar un registro creo, viene siendo el C -> CRUD

    Optional<DistritoEntity> buscarPorId(long id); // Para buscar un registro en base a un id

    Optional<DistritoEntity> buscarPorCodigo(String codigoDistrito); // igual que el de arriba pero para buscar por codigo

    List<DistritoEntity> buscarTodos(); //Devuelve todos los registros de la tabla

    DistritoEntity actualizar(DistritoEntity distrito); // actualiza un registro

    void eliminar(long id); //elimina un registro

}
