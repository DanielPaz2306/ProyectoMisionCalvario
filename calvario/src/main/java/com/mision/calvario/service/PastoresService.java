package com.mision.calvario.service;

import java.util.Optional;

import com.mision.calvario.entity.DistritoEntity;
import com.mision.calvario.entity.IglesiaEntity;
import com.mision.calvario.entity.PastoresEntity;
import java.util.List;

public interface PastoresService {

    PastoresEntity guardar(PastoresEntity pastor);

    Optional<PastoresEntity> buscarPorId(Long id);

    Optional<PastoresEntity> buscarPorCodigoPastor(String codigo);

    List<PastoresEntity> buscarPorNombre(String nombre);

    List<PastoresEntity> buscarPorEdad(int edad);

    Optional<PastoresEntity> buscarPorIglesia(IglesiaEntity iglesia);

    List<PastoresEntity> buscarPorDistrito(DistritoEntity distrito);

    Optional<PastoresEntity> buscarPastorDistrito(DistritoEntity distrito);

    List<PastoresEntity> buscarTodos();

    PastoresEntity actualizar(PastoresEntity pastor);

    void eliminar(Long id);

    Optional<PastoresEntity> buscarPorCodigoIglesia(String codigo);

    Optional<PastoresEntity> buscarPorNombreIglesia(String nombre);
    
    List<PastoresEntity> buscarPastoresDeDistrito();
    
    List<PastoresEntity> buscarPastoresSinIglesia();
    
    List<PastoresEntity> buscarPastoresSinDistrito();

}
