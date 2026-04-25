package com.mision.calvario.repository;

import com.mision.calvario.entity.PastoresEntity;
import com.mision.calvario.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByUsername(String username);
    Optional<UsuarioEntity> findByPastor(PastoresEntity pastor);
    boolean existsByUsername(String username);
    
}