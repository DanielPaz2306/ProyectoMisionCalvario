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

    @Override
    @Query("SELECT d FROM DiezmoEntity d LEFT JOIN FETCH d.pastor LEFT JOIN FETCH d.iglesia")
    List<DiezmoEntity> findAll();

    @Query("SELECT d FROM DiezmoEntity d LEFT JOIN FETCH d.pastor LEFT JOIN FETCH d.iglesia WHERE d.numerotransaccion = :num")
    Optional<DiezmoEntity> findByNumerotransaccion(@Param("num") String numerotransaccion);
    
    @Query("SELECT d FROM DiezmoEntity d LEFT JOIN FETCH d.pastor LEFT JOIN FETCH d.iglesia WHERE d.pastor = :pastor")
    List<DiezmoEntity> findByPastor(@Param("pastor") PastoresEntity pastor);

    @Query("SELECT d FROM DiezmoEntity d LEFT JOIN FETCH d.pastor LEFT JOIN FETCH d.iglesia WHERE d.iglesia = :iglesia")
    List<DiezmoEntity> findByIglesia(@Param("iglesia") IglesiaEntity iglesia);

    @Query("SELECT d FROM DiezmoEntity d LEFT JOIN FETCH d.pastor LEFT JOIN FETCH d.iglesia WHERE d.pastor = :pastor AND d.mes = :mes AND d.anio = :anio")
    List<DiezmoEntity> findByPastorAndMesAndAnio(@Param("pastor") PastoresEntity pastor, @Param("mes") int mes, @Param("anio") int anio);

    @Query("SELECT d FROM DiezmoEntity d LEFT JOIN FETCH d.pastor LEFT JOIN FETCH d.iglesia WHERE d.mes = :mes AND d.anio = :anio")
    List<DiezmoEntity> findByMesAndAnio(@Param("mes") int mes, @Param("anio") int anio);

    @Query("SELECT SUM(d.monto) FROM DiezmoEntity d WHERE d.pastor = :pastor")
    Double totalMontoPorPastor(@Param ("pastor") PastoresEntity pastor); //PARA SABER EL TOTAL DIEZMADO POR UN PASTOR

    @Query("SELECT SUM(d.monto) FROM DiezmoEntity d WHERE d.mes = :mes AND d.anio = :anio")
    Double totalMontoPorPeriodo(@Param ("mes") int mes, @Param("anio") int anio); //PARA SABER EL TOTAL DIEZMADO EN UN MONTO

    boolean existsByNumerotransaccion(String numerotransaccion);
}
