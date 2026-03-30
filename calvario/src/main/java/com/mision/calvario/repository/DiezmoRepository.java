package com.mision.calvario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mision.calvario.entity.DiezmoEntity;
import com.mision.calvario.entity.IglesiaEntity;
import com.mision.calvario.entity.PastoresEntity;

import java.util.List;


@Repository
public interface DiezmoRepository extends JpaRepository<DiezmoEntity, Long> {

    Optional<DiezmoEntity> findByNumerotransaccion(String numerotransaccion); //Muestra un diezmo por su numero de transferencia
    
    List<DiezmoEntity> findByPastor(PastoresEntity pastor); //Muestra el historial de un pastor

    List<DiezmoEntity> findByIglesia(IglesiaEntity iglesia);

    List<DiezmoEntity> findByPastorAndMesAndAnio(PastoresEntity pastor, int mes, int anio); //Para reporte de pastor en un periodo especifico

    List<DiezmoEntity> findByMesAndAnio(int mes, int anio); //Para un reporte de diezmos en un periodo de tiempo

    @Query("SELECT SUM(d.monto) FROM DiezmoEntity d WHERE d.pastor = :pastor")
    Double totalMontoPorPastor(@Param ("pastor") PastoresEntity pastor); //PARA SABER EL TOTAL DIEZMADO POR UN PASTOR

    @Query("SELECT SUM(d.monto) FROM DiezmoEntity d WHERE d.mes = :mes AND d.anio = :anio")
    Double totalMontoPorPeriodo(@Param ("mes") int mes, @Param("anio") int anio); //PARA SABER EL TOTAL DIEZMADO EN UN MONTO

    boolean existsByNumerotransaccion(String numerotransaccion);
}
