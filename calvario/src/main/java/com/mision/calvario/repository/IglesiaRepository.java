package com.mision.calvario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.mision.calvario.entity.DistritoEntity;
import com.mision.calvario.entity.IglesiaEntity;
import com.mision.calvario.entity.PastoresEntity;
import java.util.List;


@Repository
public interface IglesiaRepository extends JpaRepository<IglesiaEntity, Long>{

    Optional<IglesiaEntity> findByCodigoIglesia(String codigoIglesia);
    Optional<IglesiaEntity> findByNombreIglesia(String nombreIglesia);
    List<IglesiaEntity> findByDistrito(DistritoEntity distrito);
    Optional<IglesiaEntity> findByPastor(PastoresEntity pastor);

    boolean existsByCodigoIglesia(String codigoIglesia);
    boolean existsByNombreIglesia(String nombreIglesia);
    boolean existsByPastor(PastoresEntity pastor);
}