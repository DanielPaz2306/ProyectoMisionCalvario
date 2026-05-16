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
import org.springframework.transaction.annotation.Transactional;

import com.mision.calvario.service.PastoresService;

@RestController
@RequestMapping("/api/pastores")
@Transactional(readOnly = true)
public class PastoresController {

    @Autowired
    private PastoresService pastoresService;

    @GetMapping("/iglesia/codigo/{codigo}")
    public PastorResponseDTO buscarPorCodigoIglesia(@PathVariable String codigo){
        return pastoresService.buscarPorCodigoIglesia(codigo)
        .map(PastorResponseDTO::fromEntity)
        .orElseThrow(() -> new RuntimeException("No existe un pastor en el codigo de iglesia: " + codigo));
    }

    @GetMapping("/iglesia/nombre/{nombre}")
    public PastorResponseDTO buscarPorNombreIglesia(@PathVariable String nombre){
        return pastoresService.buscarPorNombreIglesia(nombre).map(PastorResponseDTO::fromEntity)
        .orElseThrow(() -> new RuntimeException("No existe un pastor en la iglesia: " + nombre));
    }

    @GetMapping("/pastores-distrito")
    public List<PastorResponseDTO> buscarPastoresDeDistrito(){
        return pastoresService.buscarPastoresDeDistrito()
        .stream()
        .map(PastorResponseDTO::fromEntity)
        .toList();
    }

    @GetMapping("/sin-iglesia")
    public List<PastorResponseDTO> buscarPastoresSinIglesia(){
        return pastoresService.buscarPastoresSinIglesia()
        .stream()
        .map(PastorResponseDTO::fromEntity)
        .toList();
    }

    @GetMapping("/sin-distrito")
    public List<PastorResponseDTO> buscarPastoresSinDistrito(){
        return pastoresService.buscarPastoresSinDistrito()
        .stream()
        .map(PastorResponseDTO::fromEntity)
        .toList(); 
    }

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
    @Transactional
    public PastorResponseDTO guardar(@RequestBody PastoresEntity pastor){
        return PastorResponseDTO.fromEntity(pastoresService.guardar(pastor));
    }

    @PutMapping("/{id}")
    @Transactional
    public PastorResponseDTO actualizar(@PathVariable Long id, @RequestBody PastoresEntity pastor){
        pastor.setId(id);
        return PastorResponseDTO.fromEntity(pastoresService.actualizar(pastor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id){
        pastoresService.eliminar(id);
    }
}
