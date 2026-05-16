package com.mision.calvario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import com.mision.calvario.entity.*;

@Repository
public interface PastoresRepository extends JpaRepository<PastoresEntity, Long>{

    @Override
    @Query("SELECT p FROM PastoresEntity p LEFT JOIN FETCH p.iglesia LEFT JOIN FETCH p.distrito")
    List<PastoresEntity> findAll();

    Optional<PastoresEntity> findByCodigoPastor(String codigoPastor);
    List<PastoresEntity> findByNombre(String nombre);
    List<PastoresEntity> findByEdad(int edad);
    Optional<PastoresEntity> findByCelular(String celular);
    List<PastoresEntity> findByApellido(String apellido);
    Optional<PastoresEntity> findByIglesia(IglesiaEntity iglesia);
    Optional<PastoresEntity> findByDistritoAndEsPastorDistritoTrue(DistritoEntity distrito);

    @Query("SELECT p FROM PastoresEntity p LEFT JOIN FETCH p.iglesia LEFT JOIN FETCH p.distrito WHERE p.distrito = :distrito")
    List<PastoresEntity> findByDistrito(@Param("distrito") DistritoEntity distrito);

        // Por código de iglesia
    @Query("SELECT p FROM PastoresEntity p LEFT JOIN FETCH p.iglesia i LEFT JOIN FETCH p.distrito WHERE i.codigoIglesia = :codigo")
    Optional<PastoresEntity> findByIglesiaCodigoIglesia(@Param("codigo") String codigo);

    // Por nombre de iglesia exacto
    @Query("SELECT p FROM PastoresEntity p LEFT JOIN FETCH p.iglesia i LEFT JOIN FETCH p.distrito WHERE LOWER(i.nombreIglesia) = LOWER(:nombre)")
    Optional<PastoresEntity> findByIglesiaNombreIglesia(@Param("nombre") String nombre);

    // Solo pastores de distrito
    @Query("SELECT p FROM PastoresEntity p LEFT JOIN FETCH p.iglesia LEFT JOIN FETCH p.distrito WHERE p.esPastorDistrito = true")
    List<PastoresEntity> findByEsPastorDistritoTrue();

    // Pastores sin iglesia
    @Query("SELECT p FROM PastoresEntity p LEFT JOIN FETCH p.distrito WHERE p.iglesia IS NULL")
    List<PastoresEntity> findPastoresSinIglesia();

    // Pastores sin distrito
    @Query("SELECT p FROM PastoresEntity p LEFT JOIN FETCH p.iglesia WHERE p.distrito IS NULL")
    List<PastoresEntity> findPastoresSinDistrito();

    boolean existsByIglesia(IglesiaEntity iglesia);
    boolean existsByCodigoPastor(String codigoPastor);
    boolean existsByCelular(String celular);
    boolean existsByNitPastor(String nitPastor);
    Optional<PastoresEntity> findByNitPastor(String nitPastor);
}