package com.mision.calvario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.mision.calvario.entity.DistritoEntity;
import com.mision.calvario.entity.IglesiaEntity;
import com.mision.calvario.entity.PastoresEntity;
import java.util.List;


@Repository
public interface IglesiaRepository extends JpaRepository<IglesiaEntity, Long>{

    @Override
    @Query("SELECT i FROM IglesiaEntity i LEFT JOIN FETCH i.distrito LEFT JOIN FETCH i.pastor")
    List<IglesiaEntity> findAll();

    @Query("SELECT i FROM IglesiaEntity i LEFT JOIN FETCH i.distrito LEFT JOIN FETCH i.pastor p WHERE LOWER(CONCAT(p.nombre, ' ', p.apellido)) = LOWER(:nombreCompleto)")
    Optional<IglesiaEntity> findByPastorNombreCompleto(@Param("nombreCompleto") String nombreCompleto);

    @Query("SELECT i FROM IglesiaEntity i LEFT JOIN FETCH i.distrito WHERE i.pastor IS NULL")
    List<IglesiaEntity> findIglesiasSinPastor();

    @Query("SELECT i FROM IglesiaEntity i LEFT JOIN FETCH i.pastor WHERE i.distrito IS NULL")
    List<IglesiaEntity> findIglesiasSinDistrito();

    Optional<IglesiaEntity> findByCodigoIglesia(String codigoIglesia);
    Optional<IglesiaEntity> findByNombreIglesia(String nombreIglesia);

    @Query("SELECT i FROM IglesiaEntity i LEFT JOIN FETCH i.distrito LEFT JOIN FETCH i.pastor WHERE i.distrito = :distrito")
    List<IglesiaEntity> findByDistrito(@Param("distrito") DistritoEntity distrito);

    Optional<IglesiaEntity> findByPastor(PastoresEntity pastor);

    boolean existsByCodigoIglesia(String codigoIglesia);
    boolean existsByNombreIglesia(String nombreIglesia);
    boolean existsByPastor(PastoresEntity pastor);
}