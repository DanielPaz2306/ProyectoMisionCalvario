package com.mision.calvario.repository;

import com.mision.calvario.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface DistritoRepository extends JpaRepository<DistritoEntity, Long>{

    //buscar por codigo
    Optional<DistritoEntity> findByCodigoDistrito(String codigoDistrito);  
    
    //para buscar por nombre
    Optional<DistritoEntity> findByNombreDistrito(String nombreDistrito);

    //para verificar si existe un distrito con un codigo específico
    boolean existsByCodigoDistrito(String codigoDistrito);

    //para verificar si existe un distrito con un nombre igual
    boolean existByNombreDistrito(String nombreDistrito);
    
}
