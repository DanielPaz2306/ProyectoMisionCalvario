package com.mision.calvario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import com.mision.calvario.dto.IglesiaRequestDTO;
import com.mision.calvario.dto.IglesiaResponseDTO;
import com.mision.calvario.entity.DistritoEntity;
import com.mision.calvario.entity.IglesiaEntity;
import com.mision.calvario.entity.PastoresEntity;
import com.mision.calvario.service.IglesiaService;
import java.util.List;

@RestController
@RequestMapping("/api/iglesias")
@Transactional(readOnly = true)
public class IglesiaController {

    @Autowired
    private IglesiaService iglesiaService;

    @GetMapping("/nombre/{nombre}")
    public IglesiaResponseDTO buscarPorNombre(@PathVariable String nombre){
        return iglesiaService.buscarPorNombre(nombre).map(IglesiaResponseDTO::fromEntity)
        .orElseThrow(() -> new RuntimeException("No existe la iglesia llamada: "+ nombre));
    }

    @GetMapping("/pastor/{nombreCompleto}")
    public IglesiaResponseDTO buscarPorNombrePastor(@PathVariable String nombreCompleto){
        return iglesiaService.buscarPorNombrePastor(nombreCompleto).map(IglesiaResponseDTO::fromEntity)
        .orElseThrow(() -> new RuntimeException("No existe una iglesia con ese pastor"));
    }

    @GetMapping("/sin-pastor")
    public List<IglesiaResponseDTO> buscarIglesiasSinPastor(){
        return iglesiaService.buscarIglesiasSinPastor()
        .stream()
        .map(IglesiaResponseDTO::fromEntity)
        .toList();
    }

    @GetMapping("/sin-distrito")
    public List<IglesiaResponseDTO> buscarIglesiasSinDistrito(){
        return iglesiaService.buscarIglesiasSinDistrito()
        .stream()
        .map(IglesiaResponseDTO::fromEntity)
        .toList();
    }

    @GetMapping
    public List<IglesiaResponseDTO> buscarTodos() {
        return iglesiaService.buscarTodos()
                .stream()
                .map(IglesiaResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public IglesiaResponseDTO buscarPorId(@PathVariable Long id) {
        return iglesiaService.buscarPorId(id)
                .map(IglesiaResponseDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("Iglesia no encontrada con id: " + id));
    }

    @GetMapping("/codigo/{codigo}")
    public IglesiaResponseDTO buscarPorCodigo(@PathVariable String codigo) {
        return iglesiaService.buscarPorCodigo(codigo)
                .map(IglesiaResponseDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("Iglesia no encontrada con código: " + codigo));
    }

    @GetMapping("/distrito/{distritoId}")
    public List<IglesiaResponseDTO> buscarPorDistrito(@PathVariable Long distritoId) {
        return iglesiaService.buscarPorDistrito(distritoId)
                .stream()
                .map(IglesiaResponseDTO::fromEntity)
                .toList();
    }

    // POST, PUT, DELETE — sin cambios
    @PostMapping
    @Transactional
    public IglesiaResponseDTO guardar(@RequestBody IglesiaRequestDTO request) {
        IglesiaEntity iglesia = new IglesiaEntity();
        iglesia.setNombreIglesia(request.getNombreIglesia());
        if (request.getDistritoId() != null) {
            DistritoEntity distrito = new DistritoEntity();
            distrito.setId(request.getDistritoId());
            iglesia.setDistrito(distrito);
        }
        if (request.getPastorId() != null) {
            PastoresEntity pastor = new PastoresEntity();
            pastor.setId(request.getPastorId());
            iglesia.setPastor(pastor);
        }
        return IglesiaResponseDTO.fromEntity(iglesiaService.guardar(iglesia));
    }

    @PutMapping("/{id}")
    @Transactional
    public IglesiaResponseDTO actualizar(@PathVariable Long id, @RequestBody IglesiaRequestDTO request) {
        IglesiaEntity iglesia = new IglesiaEntity();
        iglesia.setId(id);
        iglesia.setNombreIglesia(request.getNombreIglesia());
        if (request.getDistritoId() != null) {
            DistritoEntity distrito = new DistritoEntity();
            distrito.setId(request.getDistritoId());
            iglesia.setDistrito(distrito);
        }
        if (request.getPastorId() != null) {
            PastoresEntity pastor = new PastoresEntity();
            pastor.setId(request.getPastorId());
            iglesia.setPastor(pastor);
        }
        return IglesiaResponseDTO.fromEntity(iglesiaService.actualizarIglesia(iglesia));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id) {
        iglesiaService.eliminarIglesia(id);
    }



}