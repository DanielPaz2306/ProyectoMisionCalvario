package com.mision.calvario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.mision.calvario.entity.IglesiaEntity;

@Repository
public interface IglesiaRepository extends JpaRepository<IglesiaEntity, Long>{

    //busca por codigo
    Optional<IglesiaEntity> findByCodigoiglesia(String codigoIglesia);

    //busca por nombre
    Optional<IglesiaEntity> findByNombreiglesia(String nombreIglesia);

    //verifica si existe un codigo igual
    boolean existByCodigoIglesia(String codigoIglesia);

    //verifica si existe un nombre igual
    boolean existByNombreIglesia(String nombreIglesia);




}
