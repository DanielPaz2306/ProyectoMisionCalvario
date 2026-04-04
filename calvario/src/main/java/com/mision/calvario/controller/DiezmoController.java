package com.mision.calvario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mision.calvario.dto.DiezmoResponseDTO;
import com.mision.calvario.entity.DiezmoEntity;
import com.mision.calvario.entity.IglesiaEntity;
import com.mision.calvario.entity.PastoresEntity;
import com.mision.calvario.service.DiezmoService;

@RestController
@RequestMapping("api/diezmos")
public class DiezmoController {


    @Autowired
    private DiezmoService diezmoService;
    
    @GetMapping
    public List<DiezmoResponseDTO> buscarTodos(){
        return diezmoService.buscarTodos().stream().map(DiezmoResponseDTO::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public DiezmoResponseDTO buscarPorId(@PathVariable Long id){
        return diezmoService.buscarPorId(id)
                .map(DiezmoResponseDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("Diezmo no encontrado con id: " + id));
    }

    @GetMapping("/transaccion/{numeroTransaccion}")
    public DiezmoResponseDTO buscarPorTransaccion(@PathVariable String numeroTransaccion){
        return diezmoService.buscarPorNumerotransaccion(numeroTransaccion).map(DiezmoResponseDTO::fromEntity)
        .orElseThrow(() -> new RuntimeException("Numero de transaccion no encontrado: " + numeroTransaccion));
    }

    @GetMapping("/pastor/{pastorId}")
    public List<DiezmoResponseDTO> buscarPorPastor(@PathVariable Long pastorId) {
        PastoresEntity pastor = new PastoresEntity();
        pastor.setId(pastorId);
        return diezmoService.buscarPorPastor(pastor)
                .stream()
                .map(DiezmoResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/iglesia/{iglesiaId}")
    public List<DiezmoResponseDTO> buscarPorIglesia(@PathVariable Long iglesiaId) {
        IglesiaEntity iglesia = new IglesiaEntity();
        iglesia.setId(iglesiaId);
        return diezmoService.buscarPorIglesia(iglesia)
                .stream()
                .map(DiezmoResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/pastor/{pastorId}/mes/{mes}/anio/{anio}")
    public List<DiezmoResponseDTO> buscarPorPastorYPeriodo(
            @PathVariable Long pastorId,
            @PathVariable int mes,
            @PathVariable int anio) {
        PastoresEntity pastor = new PastoresEntity();
        pastor.setId(pastorId);
        return diezmoService.buscarPorPastorYPeriodo(pastor, mes, anio)
                .stream()
                .map(DiezmoResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/mes/{mes}/anio/{anio}")
    public List<DiezmoResponseDTO> buscarPorPeriodo(
            @PathVariable int mes,
            @PathVariable int anio) {
        return diezmoService.buscarPorPeriodo(mes, anio)
                .stream()
                .map(DiezmoResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/total/pastor/{pastorId}")
    public Double totalMontoPorPastor(@PathVariable Long pastorId) {
        PastoresEntity pastor = new PastoresEntity();
        pastor.setId(pastorId);
        return diezmoService.totalMontoPorPastor(pastor);
    }

    @GetMapping("/total/mes/{mes}/anio/{anio}")
    public Double totalMontoPorPeriodo(
            @PathVariable int mes,
            @PathVariable int anio) {
        return diezmoService.totalMontoPorPeriodo(mes, anio);
    }

    @PostMapping
    public DiezmoResponseDTO guardar(@RequestBody DiezmoEntity diezmo) {
        return DiezmoResponseDTO.fromEntity(diezmoService.guardar(diezmo));
    }

    @PutMapping("/{id}")
    public DiezmoResponseDTO actualizar(@PathVariable Long id, @RequestBody DiezmoEntity diezmo) {
        diezmo.setId(id);
        return DiezmoResponseDTO.fromEntity(diezmoService.actualizar(diezmo));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        diezmoService.eliminar(id);
    }

}
