package com.mision.calvario.service;

import com.mision.calvario.entity.*;
import java.util.Optional;
import java.util.List;


public interface IglesiaService {

    Optional<IglesiaEntity> buscarPorNombre(String nombre);

    Optional<IglesiaEntity> buscarPorNombrePastor(String nombre);

    List<IglesiaEntity> buscarIglesiasSinPastor();

    List<IglesiaEntity> buscarIglesiasSinDistrito();

    IglesiaEntity guardar(IglesiaEntity iglesia); //

    Optional<IglesiaEntity> buscarPorId(Long id);

    Optional<IglesiaEntity> buscarPorCodigo(String codigoIglesia);

    List<IglesiaEntity> buscarPorDistrito(Long distritoId);

    List<IglesiaEntity> buscarTodos();

    IglesiaEntity actualizarIglesia(IglesiaEntity iglesia);

    void eliminarIglesia(Long id);

    PastoresEntity tienePastor(IglesiaEntity iglesia);
    

}
