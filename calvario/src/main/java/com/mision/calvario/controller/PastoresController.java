package com.mision.calvario.controller;

import java.util.List;


import com.mision.calvario.dto.PastorResponseDTO;
import com.mision.calvario.entity.DistritoEntity;
import com.mision.calvario.entity.PastoresEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mision.calvario.service.PastoresService;

@RestController
@RequestMapping("/api/pastores")
public class PastoresController {

    @Autowired
    private PastoresService pastoresService;

    @GetMapping
    public List<PastorResponseDTO> buscarTodos(){
        return pastoresService.buscarTodos().stream().map(PastorResponseDTO::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public PastorResponseDTO buscarPorId(@PathVariable Long id) {
        return pastoresService.buscarPorId(id)
                .map(PastorResponseDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("Pastor no encontrado con id: " + id));
    }

    @GetMapping("/codigo/{codigo}")
    public PastorResponseDTO buscarPorCodigo(@PathVariable String codigo) {
        return pastoresService.buscarPorCodigoPastor(codigo)
                .map(PastorResponseDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("Pastor no encontrado con código: " + codigo));
    }

    @GetMapping("/nombre/{nombre}")
    public List<PastorResponseDTO> buscarPorNombre(@PathVariable String nombre) {
        return pastoresService.buscarPorNombre(nombre)
                .stream()
                .map(PastorResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/distrito/{distritoId}")
    public List<PastorResponseDTO> buscarPorDistrito(@PathVariable Long distritoId) {
        DistritoEntity distrito = new DistritoEntity();
        distrito.setId(distritoId);
        return pastoresService.buscarPorDistrito(distrito)
                .stream()
                .map(PastorResponseDTO::fromEntity)
                .toList();
    }

    @PostMapping
    public PastoresEntity guardar(@RequestBody PastoresEntity pastor){
        return pastoresService.guardar(pastor);
    }

    @PutMapping("/{id}")
    public PastoresEntity actualizar(@PathVariable Long id, @RequestBody PastoresEntity pastor){
        pastor.setId(id);
        return pastoresService.actualizar(pastor);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        pastoresService.eliminar(id);
    }
}
