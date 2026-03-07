package com.mision.calvario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import com.mision.calvario.entity.*;

@Repository
public interface PastoresRepository extends JpaRepository<PastoresEntity, Long>{

    //para buscar a todos los pastores de un distrito
    List<PastoresEntity> findByDistrito(DistritoEntity distrito);

    //para buscarlos por apellido
    List<PastoresEntity> findByApellido(String apellido);

    //busca el pastor de una iglesia
    Optional<PastoresEntity> findByIglesia(IglesiaEntity iglesia);

    //buscar al pastor de un distrito
    Optional<PastoresEntity> findByDistritoAndEsPastorDistritoTrue(DistritoEntity distrito);

    //verifica si una iglesia tiene pastor o no
    boolean existByIglesia(IglesiaEntity iglesia);

}
