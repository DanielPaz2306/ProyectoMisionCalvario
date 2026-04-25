package com.mision.calvario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import com.mision.calvario.entity.DistritoEntity;
import com.mision.calvario.entity.IglesiaEntity;
import com.mision.calvario.entity.PastoresEntity;
import com.mision.calvario.repository.DistritoRepository;
import com.mision.calvario.repository.IglesiaRepository;
import com.mision.calvario.repository.PastoresRepository;

@Service
public class DistritoServiceImpl implements DistritoService {

    @Autowired
    private DistritoRepository distritoRepository;

    @Autowired
    private IglesiaRepository iglesiaRepository;

    @Autowired
    private PastoresRepository pastoresRepository;

    @Override
    public DistritoEntity guardar(DistritoEntity distrito){
        if(distritoRepository.existsByCodigoDistrito(distrito.getCodigoDistrito())){
            throw new RuntimeException("Ya existe un distrito con este codigo");
        }
        if(distritoRepository.existsByNombreDistrito(distrito.getNombreDistrito())){
            throw new RuntimeException("Ya existe un distrito con este nombre");
        }

        return distritoRepository.save(distrito);

    };

    @Override
    public Optional<DistritoEntity> buscarPorId(long id){
        return distritoRepository.findById(id);
    }

    @Override
    public List<DistritoEntity> buscarSinPastor(){
        return distritoRepository.findDistritosSinPastor();
    }

    @Override
    public Optional<DistritoEntity> buscarPorNombrePastorDistrito(String nombre){
        if(nombre == null || nombre.isEmpty()){
            throw new RuntimeException("El nombre no puede estar Vacio");
        }
        return distritoRepository.findByPastorDistritoNombre(nombre);
    }

    @Override
    public Optional<DistritoEntity> buscarPorNombre(String nombre){
        return distritoRepository.findByNombreDistrito(nombre);
    }

    @Override
    public Optional<DistritoEntity> buscarPorCodigo(String codigo){
        return distritoRepository.findByCodigoDistrito(codigo);
    }

    @Override
    public List<DistritoEntity> buscarTodos(){
        return distritoRepository.findAllConPastorDistrito();
    }

    @Override
    public DistritoEntity actualizar(DistritoEntity distrito) {

    if (!distritoRepository.existsById(distrito.getId())) {
        throw new RuntimeException("Este Distrito no Existe");
    }

    if (distrito.getNombreDistrito() == null || distrito.getNombreDistrito().isEmpty()) {
        throw new RuntimeException("El nombre del distrito no puede estar vacio");
    }

    if (distrito.getCodigoDistrito() == null || distrito.getCodigoDistrito().isEmpty()) {
        throw new RuntimeException("El codigo del distrito no puede estar vacio");
    }

    Optional<DistritoEntity> distritoCodigo = distritoRepository.findByCodigoDistrito(distrito.getCodigoDistrito());
    Optional<DistritoEntity> distritoNombre = distritoRepository.findByNombreDistrito(distrito.getNombreDistrito());

    if (distritoCodigo.isPresent() && distritoCodigo.get().getId() != distrito.getId()) {
        throw new RuntimeException("Ya existe un distrito con ese codigo");
    }

    if (distritoNombre.isPresent() && distritoNombre.get().getId() != distrito.getId()) {
        throw new RuntimeException("Ya existe un distrito con ese nombre");
    }

    // Obtener el distrito actual de la BD
    DistritoEntity distritoActual = distritoRepository.findById(distrito.getId()).get();

    if (distrito.getPastorDistrito() != null) {

        // Buscar el pastor nuevo
        PastoresEntity pastorNuevo = pastoresRepository.findById(distrito.getPastorDistrito().getId())
                .orElseThrow(() -> new RuntimeException("El pastor no existe"));

        // Validar que el pastor pertenece a este distrito
        if (pastorNuevo.getDistrito() == null || pastorNuevo.getDistrito().getId() != distrito.getId()) {
            throw new RuntimeException("El pastor no pertenece a este distrito — traslada al pastor primero");
        }

        // Verificar que el pastor no es PD de otro distrito
        Optional<DistritoEntity> distritoConEsePastor = distritoRepository.findByPastorDistrito(pastorNuevo);
        if (distritoConEsePastor.isPresent() && distritoConEsePastor.get().getId() != distrito.getId()) {
            throw new RuntimeException("Este pastor ya es pastor de distrito en " + distritoConEsePastor.get().getNombreDistrito());
        }

        // Desasignar pastor anterior si existía y es diferente al nuevo
        if (distritoActual.getPastorDistrito() != null &&
            distritoActual.getPastorDistrito().getId() != pastorNuevo.getId()) {
            PastoresEntity pastorAnterior = distritoActual.getPastorDistrito();
            pastorAnterior.setEsPastorDistrito(false);
            pastoresRepository.save(pastorAnterior);
        }

        // Asignar nuevo pastor
        pastorNuevo.setEsPastorDistrito(true);
        pastoresRepository.save(pastorNuevo);
        distrito.setPastorDistrito(pastorNuevo);

    } else {
        // Si viene null — dejar distrito sin PD
        if (distritoActual.getPastorDistrito() != null) {
            PastoresEntity pastorAnterior = distritoActual.getPastorDistrito();
            pastorAnterior.setEsPastorDistrito(false);
            pastoresRepository.save(pastorAnterior);
        }
        distrito.setPastorDistrito(null);
    }

    return distritoRepository.save(distrito);
}

    @Override
    public void eliminar(long id){
        DistritoEntity distrito = distritoRepository.findById(id).orElseThrow(() -> new RuntimeException("Este distrito no existe"));

        List<IglesiaEntity> iglesias = iglesiaRepository.findByDistrito(distrito);
        if(!iglesias.isEmpty()){
            throw new RuntimeException("No puedes eliminar un distrito que contiene iglesias.");
        }
        
        List<PastoresEntity> pastores = pastoresRepository.findByDistrito(distrito);
        if(!pastores.isEmpty()){
            throw new RuntimeException("No puedes eliminar un distrito que contiene pastores.");
        }
        
        distritoRepository.deleteById(id);
    }
}
