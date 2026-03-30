package com.mision.calvario.repository;

import com.mision.calvario.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistritoRepository extends JpaRepository<DistritoEntity, Long>{

    @Query("SELECT d FROM DistritoEntity d LEFT JOIN FETCH d.pastorDistrito WHERE 1=1")
    List<DistritoEntity> findAllConPastorDistrito();

    Optional<DistritoEntity> findByCodigoDistrito(String codigoDistrito);

    Optional<DistritoEntity> findByNombreDistrito(String nombreDistrito);

    boolean existsByCodigoDistrito(String codigoDistrito);

    boolean existsByNombreDistrito(String nombreDistrito);
}