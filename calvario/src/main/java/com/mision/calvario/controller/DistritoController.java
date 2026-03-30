package com.mision.calvario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mision.calvario.dto.DistritoResponseDTO;
import com.mision.calvario.entity.DistritoEntity;
import com.mision.calvario.service.DistritoService;

@RestController
@RequestMapping("/api/distritos")
public class DistritoController {

    @Autowired
    private DistritoService distritoService;

    @GetMapping
    public List<DistritoResponseDTO> buscarTodos() {
        return distritoService.buscarTodos()
                .stream()
                .map(DistritoResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public DistritoResponseDTO buscarPorId(@PathVariable Long id) {
        return distritoService.buscarPorId(id)
                .map(DistritoResponseDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("Distrito no encontrado con id: " + id));
    }

    @GetMapping("/codigo/{codigo}")
    public DistritoResponseDTO buscarPorCodigo(@PathVariable String codigo) {
        return distritoService.buscarPorCodigo(codigo)
                .map(DistritoResponseDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("Distrito no encontrado con código: " + codigo));
    }

    // POST, PUT, DELETE — sin cambios
    @PostMapping
    public DistritoEntity guardar(@RequestBody DistritoEntity distrito) {
        return distritoService.guardar(distrito);
    }

    @PutMapping("/{id}")
    public DistritoEntity actualizar(@PathVariable Long id, @RequestBody DistritoEntity distrito) {
        distrito.setId(id);
        return distritoService.actualizar(distrito);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        distritoService.eliminar(id);
    }
}