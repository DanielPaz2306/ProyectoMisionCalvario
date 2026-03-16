package com.mision.calvario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;


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
        if(iglesiaRepository.existByCodigoIglesia(iglesia.getCodigoiglesia())){
            throw new RuntimeException("Ya existe una iglesia con este codigo");
        }
        if(iglesiaRepository.existByNombreIglesia(iglesia.getNombreiglesia())){
            throw new RuntimeException("Ya existe una iglesia con este codigo");
        }
        if(iglesiaRepository.existByPastor(iglesia.getPastor())){
            throw new RuntimeException("Ya existe una iglesia con este pastor");
        }

        

        if(distritoService.buscarPorId(iglesia.getDistrito().getId()).isEmpty()){
            throw new RuntimeException("Este distrito no existe, crealo primero!");
        }

        return iglesiaRepository.save(iglesia);
    }

    @Override
    public Optional<IglesiaEntity> buscarPorId(Long id){
        return iglesiaRepository.findById(id);
    }

    @Override
    public Optional<IglesiaEntity> buscarPorCodigo(String codigo){
        return iglesiaRepository.findByCodigoiglesia(codigo);
    }

    @Override
    public List<IglesiaEntity> buscarTodos(){
        return iglesiaRepository.findAll();
    }

    @Override
    public IglesiaEntity actualizarIglesia(IglesiaEntity iglesia){
        if(!iglesiaRepository.existById(iglesia.getId())){
            throw new RuntimeException("Esta iglesia no existe!");
        }
        if(iglesia.getNombreiglesia() == null || iglesia.getNombreiglesia().isEmpty()){
            throw new RuntimeException("El nombre de la iglesia no puede estar vacio!");
        }
        if(iglesia.getCodigoiglesia() == null || iglesia.getCodigoiglesia().isEmpty()){
            throw new RuntimeException("El codigo de la iglesia no puede estar vacio!");
        }

        Optional<IglesiaEntity> iglesiaCodigo = iglesiaRepository.findByCodigoiglesia(iglesia.getCodigoiglesia());
        Optional<IglesiaEntity> iglesiaNombre = iglesiaRepository.findByNombreiglesia(iglesia.getNombreiglesia());
        Optional<PastoresEntity> iglesiaPastor = iglesiaRepository.findByPastor(iglesia.getPastor());

        if(iglesiaCodigo.isPresent() && iglesiaCodigo.get().getId() != iglesia.getId()){
            throw new RuntimeException("Ya existe una iglesia con ese codigo!");
        }

        if(iglesiaNombre.isPresent() && iglesiaNombre.get().getId() != iglesia.getId()){
            throw new RuntimeException("Ya existe una iglesia con ese Nombre!");
        }

        if(distritoService.buscarPorId(iglesia.getDistrito().getId()).isEmpty()){
            throw new RuntimeException("El distrito al que intentas actualizar no existe!");
        }

        if(iglesiaPastor.isPresent() && iglesiaPastor.get().getId() != iglesia.getPastor().getId()){
            throw new RuntimeException("Ya existe una iglesia con ese Pastor!");
        }

        return iglesiaRepository.save(iglesia);
    }


    @Override
    public void eliminarIglesia(Long id){
        if(!iglesiaRepository.existById(id)){
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
        if(iglesia.getPastor() == null){
            return null;
        }
        else{
            return iglesia.getPastor();
        }
    }

}
