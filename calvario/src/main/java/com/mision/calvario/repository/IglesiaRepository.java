package com.mision.calvario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.mision.calvario.entity.IglesiaEntity;
import com.mision.calvario.entity.PastoresEntity;

@Repository
public interface IglesiaRepository extends JpaRepository<IglesiaEntity, Long>{

    //busca por codigo
    Optional<IglesiaEntity> findByCodigoiglesia(String codigoIglesia);

    //busca por nombre
    Optional<IglesiaEntity> findByNombreiglesia(String nombreIglesia);

    Optional<PastoresEntity> findByPastor(PastoresEntity pastor);

    //verifica si existe un codigo igual
    boolean existByCodigoIglesia(String codigoIglesia);

    //verifica si existe un nombre igual
    boolean existByNombreIglesia(String nombreIglesia);

    boolean existByPastor(PastoresEntity pastor);

    boolean existById(long id);


}
