package com.mision.calvario.service;

import com.mision.calvario.repository.DistritoRepository;
import com.mision.calvario.repository.IglesiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

import com.mision.calvario.entity.DistritoEntity;
import com.mision.calvario.entity.IglesiaEntity;
import com.mision.calvario.entity.PastoresEntity;
import com.mision.calvario.repository.PastoresRepository;

@Service
public class PastoresServiceImpl implements PastoresService{

    @Autowired
    private DistritoRepository distritoRepository;

    @Autowired
    private IglesiaRepository iglesiaRepository;

    @Autowired
    private PastoresRepository pastoresRepository;

    @Autowired
    private IglesiaService iglesiaService;

    @Autowired
    private DistritoService distritoService;

    PastoresServiceImpl(IglesiaRepository iglesiaRepository, DistritoRepository distritoRepository) {
        this.iglesiaRepository = iglesiaRepository;
        this.distritoRepository = distritoRepository;
    }

    @Override
    public PastoresEntity guardar(PastoresEntity pastor){

        if(pastoresRepository.existByCodigoPastor(pastor.getCodigoPastor())){
            throw new RuntimeException("Ya existe un pastor con ese codigo!");
        }
        if(pastor.getNombre() == null || pastor.getNombre().isEmpty()){
            throw new RuntimeException("El nombre no puede estar vacio!");
        }
        if(pastor.getApellido() == null || pastor.getApellido().isEmpty()){
            throw new RuntimeException("El apellido no puede estar vacio!");
        }
        if(pastoresRepository.existByCelular(pastor.getCelular())){
            throw new RuntimeException("Ya existe un pastor con ese Celular!");
        }
        if(pastor.getEdad() < 1){
            throw new RuntimeException("Edad inválida");
        }
        if(iglesiaService.tienePastor(pastor.getIglesia()) != null){
            throw new RuntimeException("Esta iglesia ya tiene un pastor asignado!");
        }
        if(distritoService.buscarPorId(pastor.getDistrito().getId()).isEmpty()){
            throw new RuntimeException("Este distrito no existe, crealo primero!");
        }
        if(pastor.getEsPastorDistrito() == true){
            if(!pastoresRepository.findByDistritoAndEsPastorDistritoTrue(pastor.getDistrito()).isEmpty()){
                throw new RuntimeException("Este distrito ya tiene un pastor de Distrito!");
            }
        }

        return pastoresRepository.save(pastor);

        
    }


    @Override
    public Optional<PastoresEntity> buscarPorId(Long id){
        return pastoresRepository.findById(id);
    }

    @Override
    public Optional<PastoresEntity> buscarPorCodigoPastor(String codigo){
        return pastoresRepository.findByCodigoPastor(codigo);
    }

    @Override
    public List<PastoresEntity> buscarPorNombre(String nombre){
        return pastoresRepository.findByNombre(nombre);
    }

    @Override
    public List<PastoresEntity> buscarPorEdad(int edad){
        return pastoresRepository.findByEdad(edad);
    }

    @Override
    public Optional<PastoresEntity> buscarPorIglesia(IglesiaEntity iglesia){
        return pastoresRepository.findByIglesia(iglesia);
    }

    @Override
    public List<PastoresEntity> buscarPorDistrito(DistritoEntity distrito){
        return pastoresRepository.findByDistrito(distrito);
    }

    @Override
    public Optional<PastoresEntity> buscarPastorDistrito(DistritoEntity distrito){
        return pastoresRepository.findByDistritoAndEsPastorDistritoTrue(distrito);
    }

    @Override
    public List<PastoresEntity> buscarTodos(){
        return pastoresRepository.findAll();
    }

    @Override
    public PastoresEntity actualizar(PastoresEntity pastor){
        
        if(!pastoresRepository.existsById(pastor.getId())){
            throw new RuntimeException("Este pastor no existe!");
        }

        if(pastor.getNombre() == null || pastor.getNombre().isEmpty()){
            throw new RuntimeException("El nombre no puede estar vacio!");
        }
        if(pastor.getApellido() == null || pastor.getApellido().isEmpty()){
            throw new RuntimeException("El apellido no puede estar vacio!");
        }
        
        if(pastor.getEdad() < 1){
            throw new RuntimeException("Edad inválida");
        }
        
        if(distritoService.buscarPorId(pastor.getDistrito().getId()).isEmpty()){
            throw new RuntimeException("Este distrito no existe, crealo primero!");
        }
        if(pastor.getEsPastorDistrito() == true){
            if(!pastoresRepository.findByDistritoAndEsPastorDistritoTrue(pastor.getDistrito()).isEmpty()){
                throw new RuntimeException("Este distrito ya tiene un pastor de Distrito!");
            }
        }

        Optional<PastoresEntity> pastorCodigo = pastoresRepository.findByCodigoPastor(pastor.getCodigoPastor());
        Optional<PastoresEntity> pastorCelular = pastoresRepository.findByCelular(pastor.getCelular());
        Optional<PastoresEntity> pastorIglesia = pastoresRepository.findByIglesia(pastor.getIglesia());
        Optional<PastoresEntity> esPastorDistrito = pastoresRepository.findByDistritoAndEsPastorDistritoTrue(pastor.getDistrito());

        if(pastorCodigo.isPresent() && pastorCodigo.get().getId() != pastor.getId()){
            throw new RuntimeException("Ya existe un pastor con este codigo!");
        }

        if(pastorCelular.isPresent() && pastorCelular.get().getId() != pastor.getId()){
            throw new RuntimeException("Ya existe un pastor con este celular");
        }

        if(pastorIglesia.isPresent() && pastorIglesia.get().getId() != pastor.getId()){
            throw new RuntimeException("Esta iglesia ya tiene un pastor!");
        }

        if(esPastorDistrito.isPresent() && esPastorDistrito.get().getId() != pastor.getId()){
            throw new RuntimeException("Este distrito ya tiene un pastor de Distrito!");
        }

        return pastoresRepository.save(pastor);


    }

    @Override
    public void eliminar(Long id){
              
        Optional<PastoresEntity> pastor = pastoresRepository.findById(id);

        if(pastor.isEmpty()){
            throw new RuntimeException("No existe ningun pastor con ese ID");
        }

        if(pastor.get().getIglesia() != null){
            IglesiaEntity iglesia = pastor.get().getIglesia();
            iglesia.setPastor(null);
            iglesiaRepository.save(iglesia);
        }

        if(pastor.get().getEsPastorDistrito() == true){
            DistritoEntity distrito = pastor.get().getDistrito();
            distrito.setPastorDistrito(null);
            distritoRepository.save(distrito);
        }

        pastoresRepository.deleteById(id);
    }
}
