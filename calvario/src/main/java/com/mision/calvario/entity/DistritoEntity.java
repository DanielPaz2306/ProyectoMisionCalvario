package com.mision.calvario.entity;

import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "distritos")
public class DistritoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(name = "codigodistrito", nullable = false, length = 10)
    private String codigoDistrito;

    @Column(name = "nombredistrito", nullable = false, length = 99)
    private String nombreDistrito;

    @OneToOne
    @JoinColumn(name = "pastor_distrito_id")
    private PastoresEntity pastorDistrito;

    @OneToMany
    private ArrayList<PastoresEntity> pastores; //PENDIENTE DE VERIFICAR FUNCIONALIDAD
}
