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
    public Optional<DistritoEntity> buscarPorCodigo(String codigo){
        return distritoRepository.findByCodigoDistrito(codigo);
    }

    @Override
    public List<DistritoEntity> buscarTodos(){
        return distritoRepository.findAllConPastorDistrito();
    }

    @Override
    public DistritoEntity actualizar(DistritoEntity distrito){

        if(!distritoRepository.existsById(distrito.getId())){
            throw new RuntimeException("Este Distrito no Existe"); 
        }

        if(distrito.getNombreDistrito() == null || distrito.getNombreDistrito().isEmpty() ){
            throw new RuntimeException("El nombre del distrito no puede estar vacio");
        }

        if(distrito.getCodigoDistrito() == null || distrito.getCodigoDistrito() == ""){
            throw new RuntimeException("El codigo del distrito no puede estar vacio");
        }

        Optional<DistritoEntity> distritoCodigo = distritoRepository.findByCodigoDistrito(distrito.getCodigoDistrito());
        Optional<DistritoEntity> distritoNombre = distritoRepository.findByNombreDistrito(distrito.getNombreDistrito());

        //Verifica si existe otro distrito con ese codigo para luego obtener el id de este registro
        //y luego verifica si el id encontrado es igual al que pretendo modificar para asi evitar que hayan codigos dupes
        if( distritoCodigo.isPresent() && distritoCodigo.get().getId() != distrito.getId()){
            throw new RuntimeException("Ya existe un distrito con ese codigo");
        }
        
        //lo mismo de arriba pero con el nombre
        if( distritoNombre.isPresent() && distritoNombre.get().getId() != distrito.getId()){
            throw new RuntimeException("Ya existe un distrito con ese nombre");
        }

        return distritoRepository.save(distrito); //usamos el mismo save que en guardar porque JPA lo guarda si el distrito no tiene id, y si ya tiene solo lo actualiza
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
