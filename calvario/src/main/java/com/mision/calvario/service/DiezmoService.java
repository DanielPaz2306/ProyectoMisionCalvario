package com.mision.calvario.service;

import java.util.List;
import java.util.Optional;

import com.mision.calvario.entity.DiezmoEntity;
import com.mision.calvario.entity.IglesiaEntity;
import com.mision.calvario.entity.PastoresEntity;

public interface DiezmoService {

    DiezmoEntity guardar(DiezmoEntity diezmo);
    DiezmoEntity actualizar(DiezmoEntity diezmo);
    void eliminar(Long id);

    Optional<DiezmoEntity> buscarPorId(Long id);
    Optional<DiezmoEntity> buscarPorNumerotransaccion(String numerotransaccion);
    List<DiezmoEntity> buscarTodos();
    List<DiezmoEntity> buscarPorPastor(PastoresEntity pastor);
    List<DiezmoEntity> buscarPorIglesia(IglesiaEntity iglesia);
    List<DiezmoEntity> buscarPorPastorYPeriodo(PastoresEntity pastor, int mes, int anio);
    List<DiezmoEntity> buscarPorPeriodo(int mes, int anio);

    Double totalMontoPorPastor(PastoresEntity pastor);
    Double totalMontoPorPeriodo(int mes, int anio);

}
