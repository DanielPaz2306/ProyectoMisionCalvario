package com.mision.calvario.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mision.calvario.entity.DiezmoEntity;
import com.mision.calvario.entity.IglesiaEntity;
import com.mision.calvario.entity.PastoresEntity;
import com.mision.calvario.repository.DiezmoRepository;


@Service
public class DiezmoServiceImpl implements DiezmoService {

    @Autowired
    private DiezmoRepository diezmoRepository;

    @Autowired
    private PastoresService pastoresService;

    @Autowired
    private IglesiaService iglesiaService;

    @Override
    public DiezmoEntity guardar (DiezmoEntity diezmo) {

        //VALIDAMOS PASTOR
        if(diezmo.getPastor() == null){
            throw new RuntimeException("El Campo pastor no puede ir vacio");
        }
        if(pastoresService.buscarPorId(diezmo.getPastor().getId()).isEmpty()){
            throw new RuntimeException("El pastor no existe");
        }

        //VALIDAMOS LA IGLESIA SI ES QUE VIENE
        if(diezmo.getIglesia() != null){
            if(iglesiaService.buscarPorId(diezmo.getIglesia().getId()).isEmpty()){
                throw new RuntimeException("La iglesia no existe");
            }
        }

        //VALIDAMOS LA FECHA DEL DIEZMO
        if(diezmo.getMes() < 1 || diezmo.getMes() > 12){
                throw new RuntimeException("El Mes ingresado es invalido (1 - 12)");
        }
        if(diezmo.getAnio() < 2000){
                throw new RuntimeException("El año ingresado es invalido (>2000)");
        }

        //VALIDAMOS MONTO
        if(diezmo.getMonto() <= 0){
                throw new RuntimeException("El monto debe ser mayor a 0");
        }

        //VALIDAMOS FECHA DE PAGO
        if(diezmo.getFechaPago() == null){
                throw new RuntimeException("La fecha de pago es obligatoria");
        }

        //VALIDAMOS EL NUMERO DE TRANSACCION
        if(diezmo.getNumerotransaccion() == null || diezmo.getNumerotransaccion().isEmpty()){
                throw new RuntimeException("El numero de transacción es obligatorio");
        }
        if(diezmoRepository.existsByNumerotransaccion(diezmo.getNumerotransaccion())){
                throw new RuntimeException("Ya existe un diezmo con ese numero de transacción");
        }

        //VALIDAMOS BANCO Y NUMERO DE CUENTA
        if(diezmo.getBanco() == null || diezmo.getBanco().isEmpty()){
                throw new RuntimeException("El campo BANCO es obligatorio");
        }
        if(diezmo.getNumeroCuenta() == null || diezmo.getNumeroCuenta().isEmpty()){
                throw new RuntimeException("El campo NUMERO DE CUENTA es obligatorio");
        }

        return diezmoRepository.save(diezmo);

    }

    @Override
    public DiezmoEntity actualizar(DiezmoEntity diezmo){
        if(!diezmoRepository.existsById(diezmo.getId())){
            throw new RuntimeException("Este diezmo no existe");
        }

        //VALIDAMOS PASTOR
        if(diezmo.getPastor() == null){ 
            throw new RuntimeException("El pastor no puede ser NULL");
        }
        if(pastoresService.buscarPorId(diezmo.getPastor().getId()).isEmpty()){
            throw new RuntimeException("El pastor no existe");
        }

        //VALIDAMOS IGLESIA SI VIENE
        if(diezmo.getIglesia() != null){
            if(iglesiaService.buscarPorId(diezmo.getIglesia().getId()).isEmpty()){
                throw new RuntimeException("La iglesia no existe");
            }
        }

        //VALIDAMOS MES
        if(diezmo.getMes() < 1 || diezmo.getMes() > 12){
            throw new RuntimeException("El mes ingresado es invalido (1 - 12)");
        }

        //VALIDAMOS AÑO
        if(diezmo.getAnio() < 2000){
            throw new RuntimeException("El año ingresado no es valido (>2000)");
        }

        //VALIDAMOS FECHA DE PAGO
        if(diezmo.getFechaPago() == null){
            throw new RuntimeException("La fecha de pago no puede ser nula");
        }

        //VALIDAMOS NUMERO DE TRANSACCION
        if(diezmo.getNumerotransaccion() == null){
            throw new RuntimeException("El numero de transaccion es obligatorio");
        }
        Optional<DiezmoEntity> diezmoTransaccion = diezmoRepository.findByNumerotransaccion(diezmo.getNumerotransaccion());
        if(diezmoTransaccion.isPresent() && diezmoTransaccion.get().getId() != diezmo.getId()){
            throw new RuntimeException("Ya existe un diezmo con este numero de transacción");
        }

        //VALIDAR BANCO Y NIMERO DE CUENTA
        if(diezmo.getBanco() == null || diezmo.getBanco().isEmpty()){
            throw new RuntimeException("El campo BANCO es obligatorio");
        }
        if(diezmo.getNumeroCuenta() == null || diezmo.getNumeroCuenta().isEmpty()){
            throw new RuntimeException("El campo NUMERO DE CUENTA es obligatorio");
        }

        return diezmoRepository.save(diezmo);
        
    }

    @Override
    public void eliminar(Long id){
        if(!diezmoRepository.existsById(id)){
            throw new RuntimeException("Este diezmo no existe");
        }
        diezmoRepository.deleteById(id);
    }

    @Override
    public Optional<DiezmoEntity> buscarPorId(Long id){
        return diezmoRepository.findById(id);
    }

    @Override
    public Optional<DiezmoEntity> buscarPorNumerotransaccion(String numerotransaccion){
        return diezmoRepository.findByNumerotransaccion(numerotransaccion);
    }

    @Override
    public List<DiezmoEntity> buscarTodos(){
        return diezmoRepository.findAll();
    }

    @Override
    public List<DiezmoEntity> buscarPorPastor(PastoresEntity pastor){
        return diezmoRepository.findByPastor(pastor);
    }

    @Override
    public List<DiezmoEntity> buscarPorIglesia(IglesiaEntity iglesia){
        return diezmoRepository.findByIglesia(iglesia);
    }

    @Override
    public List<DiezmoEntity> buscarPorPastorYPeriodo(PastoresEntity pastor, int mes, int anio) {       
        if (mes < 1 || mes > 12) {
            throw new RuntimeException("El mes debe estar entre 1 y 12!");
        }
        if (anio < 2000) {
            throw new RuntimeException("El año ingresado no es válido!");
        }
        return diezmoRepository.findByPastorAndMesAndAnio(pastor, mes, anio);
    }

    @Override
    public List<DiezmoEntity> buscarPorPeriodo(int mes, int anio) {
        if (mes < 1 || mes > 12) {
            throw new RuntimeException("El mes debe estar entre 1 y 12!");
        }
        if (anio < 2000) {
            throw new RuntimeException("El año ingresado no es válido!");
        }
        return diezmoRepository.findByMesAndAnio(mes, anio);
    }

    @Override
    public Double totalMontoPorPastor(PastoresEntity pastor) {
        if (pastor == null) {
            throw new RuntimeException("El pastor es obligatorio!");
        }
        if (pastoresService.buscarPorId(pastor.getId()).isEmpty()) {
            throw new RuntimeException("Este pastor no existe!");
        }
        Double total = diezmoRepository.totalMontoPorPastor(pastor);
        return total != null ? total : 0.0;
    }

    @Override
    public Double totalMontoPorPeriodo(int mes, int anio) {
        if (mes < 1 || mes > 12) {
            throw new RuntimeException("El mes debe estar entre 1 y 12!");
        }
        if (anio < 2000) {
            throw new RuntimeException("El año ingresado no es válido!");
        }
        Double total = diezmoRepository.totalMontoPorPeriodo(mes, anio);
        return total != null ? total : 0.0;
    }

}
