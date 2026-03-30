package com.mision.calvario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

import com.mision.calvario.entity.DistritoEntity;
import com.mision.calvario.entity.IglesiaEntity;
import com.mision.calvario.entity.PastoresEntity;
import com.mision.calvario.repository.IglesiaRepository;
import com.mision.calvario.repository.PastoresRepository;

@Service
public class IglesiaServiceImpl implements IglesiaService{


    @Autowired
    private IglesiaRepository iglesiaRepository;

    @Autowired
    private DistritoService distritoService;

    @Autowired
    private PastoresRepository pastoresRepository;


    @Override
    public IglesiaEntity guardar(IglesiaEntity iglesia){
        if(iglesiaRepository.existsByCodigoIglesia(iglesia.getCodigoIglesia())){
            throw new RuntimeException("Ya existe una iglesia con este codigo");
        }
        if(iglesiaRepository.existsByNombreIglesia(iglesia.getNombreIglesia())){
            throw new RuntimeException("Ya existe una iglesia con este nombre");
        }
        if(iglesia.getPastor() != null && iglesiaRepository.existsByPastor(iglesia.getPastor())){
        throw new RuntimeException("Ya existe una iglesia con este pastor");
        }   

        

        if(distritoService.buscarPorId(iglesia.getDistrito().getId()).isEmpty()){
            throw new RuntimeException("Este distrito no existe, crealo primero!");
        }

        return iglesiaRepository.save(iglesia);
    }

    @Override
    public List<IglesiaEntity> buscarPorDistrito(Long distritoId){
        DistritoEntity distrito = distritoService.buscarPorId(distritoId)
            .orElseThrow(() -> new RuntimeException("Este distrito no existe"));
        
        return iglesiaRepository.findByDistrito(distrito);
    }

    @Override
    public Optional<IglesiaEntity> buscarPorId(Long id){
        return iglesiaRepository.findById(id);
    }

    @Override
    public Optional<IglesiaEntity> buscarPorCodigo(String codigo){
        return iglesiaRepository.findByCodigoIglesia(codigo);
    }

    @Override
    public List<IglesiaEntity> buscarTodos(){
        return iglesiaRepository.findAll();
    }

@Override
public IglesiaEntity actualizarIglesia(IglesiaEntity iglesia){
    if(!iglesiaRepository.existsById(iglesia.getId())){
        throw new RuntimeException("Esta iglesia no existe!");
    }
    if(iglesia.getNombreIglesia() == null || iglesia.getNombreIglesia().isEmpty()){
        throw new RuntimeException("El nombre de la iglesia no puede estar vacio!");
    }
    if(iglesia.getCodigoIglesia() == null || iglesia.getCodigoIglesia().isEmpty()){
        throw new RuntimeException("El codigo de la iglesia no puede estar vacio!");
    }

    Optional<IglesiaEntity> iglesiaCodigo = iglesiaRepository.findByCodigoIglesia(iglesia.getCodigoIglesia());
    Optional<IglesiaEntity> iglesiaNombre = iglesiaRepository.findByNombreIglesia(iglesia.getNombreIglesia());

    if(iglesiaCodigo.isPresent() && iglesiaCodigo.get().getId() != iglesia.getId()){
        throw new RuntimeException("Ya existe una iglesia con ese codigo!");
    }
    if(iglesiaNombre.isPresent() && iglesiaNombre.get().getId() != iglesia.getId()){
        throw new RuntimeException("Ya existe una iglesia con ese Nombre!");
    }
    if(distritoService.buscarPorId(iglesia.getDistrito().getId()).isEmpty()){
        throw new RuntimeException("El distrito al que intentas actualizar no existe!");
    }

    // ← solo validar pastor si no es null
    if(iglesia.getPastor() != null){
        Optional<IglesiaEntity> iglesiaPastor = iglesiaRepository.findByPastor(iglesia.getPastor());
        if(iglesiaPastor.isPresent() && iglesiaPastor.get().getId() != iglesia.getId()){
            throw new RuntimeException("Este pastor ya tiene una iglesia asignada!");
        }
    }

    return iglesiaRepository.save(iglesia);
}

    @Override
    public void eliminarIglesia(Long id){
        if(!iglesiaRepository.existsById(id)){
            throw new RuntimeException("Esta iglesia no existe");
        }
        Optional<PastoresEntity> pastor = pastoresRepository.findByIglesia(iglesiaRepository.findById(id).get());
        
        if(pastor.isPresent()){
            pastor.get().setIglesia(null);
            pastoresRepository.save(pastor.get());
        }

        iglesiaRepository.deleteById(id);
    }

    @Override
    public PastoresEntity tienePastor(IglesiaEntity iglesia){
        Optional<IglesiaEntity> iglesiaCompleta = iglesiaRepository.findById(iglesia.getId());

        if(iglesiaCompleta.isEmpty()){
            return null;
        }
        else{
            return iglesiaCompleta.get().getPastor();
        }
    }

}
