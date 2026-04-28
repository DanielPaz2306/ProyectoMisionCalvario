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
    // solo validar nit si viene en el request
    if(pastor.getNitPastor() != null){
        // necesitarías agregar este método al repository
        if(pastoresRepository.existsByNitPastor(pastor.getNitPastor())){
            throw new RuntimeException("Ya existe un pastor con ese NIT!");
        }
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



    // solo validar celular si viene en el request
    if(pastor.getCelular() != null && pastoresRepository.existsByCelular(pastor.getCelular())){
        throw new RuntimeException("Ya existe un pastor con ese Celular!");
    }

    // validar que el distrito existe si se envió
    if(pastor.getDistrito() != null){
        if(distritoService.buscarPorId(pastor.getDistrito().getId()).isEmpty()){
            throw new RuntimeException("Este distrito no existe, crealo primero!");
        }
    }

    // validar iglesia solo si viene en el request
    if(pastor.getIglesia() != null){
        if(iglesiaService.buscarPorId(pastor.getIglesia().getId()).isEmpty()){
            throw new RuntimeException("Esta iglesia no existe!");
        }
        if(iglesiaService.tienePastor(pastor.getIglesia()) != null){
            throw new RuntimeException("Esta iglesia ya tiene un pastor asignado!");
        }
    }

    // validar pastor de distrito
    if(pastor.getEsPastorDistrito() == true){
        if(pastor.getDistrito() == null) {
            throw new RuntimeException("Para ser Pastor de Distrito, debes asignarle un distrito válido.");
        }
        if(pastoresRepository.findByDistritoAndEsPastorDistritoTrue(pastor.getDistrito()).isPresent()){
            throw new RuntimeException("Este distrito ya tiene un Pastor de Distrito asignado.");
        }
    }
    PastoresEntity pastorGuardado = pastoresRepository.save(pastor);
    pastorGuardado.setCodigoPastor("P" + String.format("%03d", pastorGuardado.getId()));
    pastorGuardado = pastoresRepository.save(pastorGuardado);

    if(pastor.getEsPastorDistrito() == true && pastor.getDistrito() != null){
        System.out.println("=== Asignando pastor de distrito ===");
        DistritoEntity distrito = distritoService.buscarPorId(pastor.getDistrito().getId()).get();
        System.out.println("Distrito encontrado: " + distrito.getNombreDistrito());
        distrito.setPastorDistrito(pastorGuardado);
        distritoRepository.save(distrito);
        System.out.println("Distrito actualizado");
    }

    return pastorGuardado;
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
    if(pastor.getDistrito() != null){
        if(distritoService.buscarPorId(pastor.getDistrito().getId()).isEmpty()){
            throw new RuntimeException("Este distrito no existe, crealo primero!");
        }
    }

    // solo validar nit si viene en el request
    if(pastor.getNitPastor() != null){
        Optional<PastoresEntity> pastorNit = pastoresRepository.findByNitPastor(pastor.getNitPastor());
        if(pastorNit.isPresent() && pastorNit.get().getId() != pastor.getId()){
            throw new RuntimeException("Ya existe un pastor con ese NIT!");
        }
    }

    PastoresEntity pastorActual = pastoresRepository.findById(pastor.getId()).get();
    pastor.setCodigoPastor(pastorActual.getCodigoPastor());

    // solo buscar si no es null
    if(pastor.getCelular() != null){
        Optional<PastoresEntity> pastorCelular = pastoresRepository.findByCelular(pastor.getCelular());
        if(pastorCelular.isPresent() && pastorCelular.get().getId() != pastor.getId()){
            throw new RuntimeException("Ya existe un pastor con este celular!");
        }
    }

    // solo buscar iglesia si no es null
    if(pastor.getIglesia() != null){
        if(iglesiaService.buscarPorId(pastor.getIglesia().getId()).isEmpty()){
            throw new RuntimeException("Esta iglesia no existe!");
        }
        Optional<PastoresEntity> pastorIglesia = pastoresRepository.findByIglesia(pastor.getIglesia());
        if(pastorIglesia.isPresent() && pastorIglesia.get().getId() != pastor.getId()){
            throw new RuntimeException("Esta iglesia ya tiene un pastor!");
        }
    }

    // verificar pastor de distrito
    if(pastor.getEsPastorDistrito() == true){
        if(pastor.getDistrito() == null) {
            throw new RuntimeException("Para ser Pastor de Distrito, debes asignarle un distrito válido.");
        }
        Optional<PastoresEntity> pastorDistritoExistente = pastoresRepository.findByDistritoAndEsPastorDistritoTrue(pastor.getDistrito());
        if(pastorDistritoExistente.isPresent() && !pastorDistritoExistente.get().getId().equals(pastor.getId())){
            throw new RuntimeException("Este distrito ya tiene un Pastor de Distrito asignado.");
        }
    }

    PastoresEntity pastorActualizado = pastoresRepository.save(pastor);

    // Actualizar referencia en el distrito si hay distrito asignado
    if(pastor.getDistrito() != null){
        DistritoEntity distrito = distritoService.buscarPorId(pastor.getDistrito().getId()).get();

        if(pastor.getEsPastorDistrito() == true){
            distrito.setPastorDistrito(pastorActualizado);
            distritoRepository.save(distrito);
        } else {
            // Si este pastor era el pastor de distrito, limpiar la referencia
            if(distrito.getPastorDistrito() != null && distrito.getPastorDistrito().getId().equals(pastor.getId())){
                distrito.setPastorDistrito(null);
                distritoRepository.save(distrito);
            }
        }
    }

    return pastorActualizado;
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

    @Override
    public Optional<PastoresEntity> buscarPorCodigoIglesia(String codigo){
        if(codigo == null || codigo.isEmpty()){
            throw new RuntimeException("El codigo no puede estar vacio");
        }

        return pastoresRepository.findByIglesiaCodigoIglesia(codigo);
    }

    @Override
    public Optional<PastoresEntity> buscarPorNombreIglesia(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new RuntimeException("El nombre de iglesia no puede estar vacío");
        }
        return pastoresRepository.findByIglesiaNombreIglesia(nombre);
    }

    @Override
    public List<PastoresEntity> buscarPastoresDeDistrito(){
        return pastoresRepository.findByEsPastorDistritoTrue();
    }

    @Override
    public List<PastoresEntity> buscarPastoresSinIglesia(){
        return pastoresRepository.findPastoresSinIglesia();
    }

    @Override
    public List<PastoresEntity> buscarPastoresSinDistrito(){
        return pastoresRepository.findPastoresSinDistrito();
    }




}
