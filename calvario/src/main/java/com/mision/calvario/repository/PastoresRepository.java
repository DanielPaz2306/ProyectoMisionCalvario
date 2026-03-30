package com.mision.calvario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import com.mision.calvario.entity.*;

@Repository
public interface PastoresRepository extends JpaRepository<PastoresEntity, Long>{

    Optional<PastoresEntity> findByCodigoPastor(String codigoPastor);
    List<PastoresEntity> findByNombre(String nombre);
    List<PastoresEntity> findByEdad(int edad);
    Optional<PastoresEntity> findByCelular(String celular);
    List<PastoresEntity> findByDistrito(DistritoEntity distrito);
    List<PastoresEntity> findByApellido(String apellido);
    Optional<PastoresEntity> findByIglesia(IglesiaEntity iglesia);
    Optional<PastoresEntity> findByDistritoAndEsPastorDistritoTrue(DistritoEntity distrito);
    boolean existsByIglesia(IglesiaEntity iglesia);
    boolean existsByCodigoPastor(String codigoPastor);
    boolean existsByCelular(String celular);
}