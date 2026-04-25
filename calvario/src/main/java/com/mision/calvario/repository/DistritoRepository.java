package com.mision.calvario.repository;

import com.mision.calvario.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistritoRepository extends JpaRepository<DistritoEntity, Long>{

    @Query("SELECT d FROM DistritoEntity d WHERE d.pastorDistrito IS NULL")
    List<DistritoEntity> findDistritosSinPastor();

    @Query("SELECT COUNT(i) FROM IglesiaEntity i WHERE i.distrito.id = :distritoId")
    int countIglesiasByDistrito(@Param("distritoId") Long distritoId);
 
    @Query("SELECT d FROM DistritoEntity d LEFT JOIN FETCH d.pastorDistrito p WHERE LOWER(CONCAT(p.nombre, ' ', p.apellido)) = LOWER(:nombreCompleto)")
    Optional<DistritoEntity> findByPastorDistritoNombre(@Param("nombreCompleto") String nombreCompleto);

    @Query("SELECT DISTINCT d FROM DistritoEntity d LEFT JOIN FETCH d.pastorDistrito LEFT JOIN FETCH d.iglesias")
    List<DistritoEntity> findAllConPastorDistrito();

    Optional<DistritoEntity> findByCodigoDistrito(String codigoDistrito);

    Optional<DistritoEntity> findByNombreDistrito(String nombreDistrito);

    Optional<DistritoEntity> findByPastorDistrito(PastoresEntity pastor);

    boolean existsByCodigoDistrito(String codigoDistrito);

    boolean existsByNombreDistrito(String nombreDistrito);
}