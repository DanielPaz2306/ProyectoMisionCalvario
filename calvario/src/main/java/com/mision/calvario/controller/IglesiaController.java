package com.mision.calvario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mision.calvario.dto.IglesiaResponseDTO;
import com.mision.calvario.entity.IglesiaEntity;
import com.mision.calvario.service.IglesiaService;
import java.util.List;

@RestController
@RequestMapping("/api/iglesias")
public class IglesiaController {

    @Autowired
    private IglesiaService iglesiaService;

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
    public IglesiaEntity guardar(@RequestBody IglesiaEntity iglesia) {
        return iglesiaService.guardar(iglesia);
    }

    @PutMapping("/{id}")
    public IglesiaEntity actualizar(@PathVariable Long id, @RequestBody IglesiaEntity iglesia) {
        iglesia.setId(id);
        return iglesiaService.actualizarIglesia(iglesia);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        iglesiaService.eliminarIglesia(id);
    }
}