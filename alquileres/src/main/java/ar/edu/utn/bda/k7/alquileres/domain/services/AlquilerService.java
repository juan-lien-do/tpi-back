package ar.edu.utn.bda.k7.alquileres.domain.services;

import ar.edu.utn.bda.k7.alquileres.domain.dtos.AlquilerDTO;
import ar.edu.utn.bda.k7.alquileres.domain.dtos.AlquilerFinalizadoResponseDTO;
import ar.edu.utn.bda.k7.alquileres.domain.dtos.ConversorDTO;
import ar.edu.utn.bda.k7.alquileres.domain.exception.*;
import ar.edu.utn.bda.k7.alquileres.domain.model.Alquiler;
import ar.edu.utn.bda.k7.alquileres.domain.model.Estacion;
import ar.edu.utn.bda.k7.alquileres.domain.model.Tarifa;
import ar.edu.utn.bda.k7.alquileres.domain.repositories.AlquilerRepository;
import ar.edu.utn.bda.k7.alquileres.domain.repositories.IdentifierRepository;
import ar.edu.utn.bda.k7.alquileres.domain.repositories.TarifaRepository;
import ar.edu.utn.bda.k7.alquileres.util.dtomappers.AlquilerMapper;
import ar.edu.utn.bda.k7.alquileres.util.peticionexterna.PeticionesConversor;
import ar.edu.utn.bda.k7.alquileres.util.peticionexterna.PeticionesEstacion;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class AlquilerService {

    EstacionService estacionService;
    AlquilerRepository alquilerRepository;
    TarifaRepository tarifaRepository;
    IdentifierRepository identifierRepository;
    PeticionesEstacion peticionesEstacion;
    //CalculoMonto calculoMonto;
    PeticionesConversor peticionesConversor;


    @Autowired
    public AlquilerService(EstacionService estacionService, AlquilerRepository alquilerRepository, TarifaRepository tarifaRepository,
                           IdentifierRepository identifierRepository, PeticionesEstacion peticionesEstacion,
                           PeticionesConversor peticionesConversor) {
        this.estacionService = estacionService;
        this.alquilerRepository = alquilerRepository;
        this.tarifaRepository = tarifaRepository;
        this.identifierRepository = identifierRepository;
        this.peticionesEstacion = peticionesEstacion;
        //this.calculoMonto = calculoMonto;
        this.peticionesConversor = peticionesConversor;
    }

    // Buscar todos los alquileres todo ok
    @Transactional(readOnly = true)
    public List<AlquilerDTO> findAll(){
        return alquilerRepository.findAll().stream().map(AlquilerMapper::toDTO).toList();
    }
    // Buscar alquiler por ID todo OK
    @Transactional(readOnly = true)
    public AlquilerDTO findById(Long id) throws AlquilerException {
        Optional<Alquiler> alquiler = alquilerRepository.findById(id);
        if(alquiler.isEmpty()) throw new AlquilerException("Alquiler no encontrado");
        return alquiler.map(AlquilerMapper::toDTO).get();
    }

    public List<AlquilerDTO> filtrarPorMonto(double montoMinimo) {
        List<Alquiler> alquileres = alquilerRepository.findByMontoGreaterThan(montoMinimo);
        return alquileres.stream().map(AlquilerMapper::toDTO).collect(Collectors.toList());
    }

    public List<AlquilerDTO> filtrarPorEstado(Long estado) throws EstadoException {
        if (estado!= 1  && estado != 2) {
            throw new EstadoException("El estado proporcionado no es válido");
        }
        List<Alquiler> alquileres = alquilerRepository.findByEstado(estado);
        return alquileres.stream().map(AlquilerMapper::toDTO).collect(Collectors.toList());
    }

//    @Transactional(readOnly = false)
//    public AlquilerDTO save(AlquilerDTO dto) throws EstacionException, TarifaException {
//        Estacion estacionRetiro = peticionesEstacion.invocarServicioGetId(dto.getEstacionRetiroId());
//        if (estacionRetiro == null){
//            throw new EstacionException("Estacion Retiro no encontrada");
//        }
//        Estacion estacionDevolucion = null;
//        if(dto.getEstacionDevolucionId() != null) {
//            estacionDevolucion = peticionesEstacion.invocarServicioGetId(dto.getEstacionDevolucionId());
//            if (estacionDevolucion == null ) {
//                throw new EstacionException("Estacion Devolucion no encontrada");
//            }
//        }
//        Optional<Tarifa> tarifa = tarifaRepository.findById(dto.getTarifaId());
//        if (tarifa.isEmpty()){
//            throw new TarifaException("Tarifa no encontrada");
//        }
//
//        if(dto.getId() == null){
//            dto.setId((long) identifierRepository.nextValue( "ALQUILERES"));
//        }
//
//        Alquiler alquiler = AlquilerMapper.toEntity(dto, estacionRetiro, estacionDevolucion, tarifa.get());
//        System.out.println(alquiler);
//        return AlquilerMapper.toDTO(alquilerRepository.save(alquiler));
//    }

    @Transactional(readOnly = false)
    public AlquilerDTO saveNewRent(AlquilerDTO dto) throws EstacionException {

        Estacion estacionRetiro = Optional.ofNullable(peticionesEstacion.invocarServicioGetId(dto.getEstacionRetiroId()))
                .orElseThrow(() -> new EstacionException("Estacion Retiro no encontrada"));

        AlquilerDTO alquilerDto = new AlquilerDTO((long) identifierRepository.nextValue( "ALQUILERES"),
                dto.getIdCliente(), 1L,LocalDateTime.now().withNano(0),null,null,
                estacionRetiro.getId(),null,null);

        Alquiler alquiler = AlquilerMapper.toEntity(alquilerDto,null);
        return AlquilerMapper.toDTO(alquilerRepository.save(alquiler));
    }

    @Transactional(readOnly = false)
    public AlquilerFinalizadoResponseDTO update(Long id, Long idEstacion, String tipoMoneda)throws AlquilerException ,
            EstacionException, ConversorException
    {
        Estacion estacionDevuelto = Optional.ofNullable(peticionesEstacion.invocarServicioGetId(idEstacion))
                .orElseThrow(() -> new EstacionException("Estacion Devolucion no encontrada"));

        Alquiler alquiler = alquilerRepository.findById(id)
                .orElseThrow(() -> new AlquilerException("Alquiler no encontrado"));

        Tarifa tarifa = asignarTarifa(alquiler);
        Estacion estacionRetiro = peticionesEstacion.invocarServicioGetId(alquiler.getIdEstacionRetiro());
        alquiler.finalizarAlquiler(estacionService.calcularDistancia(estacionRetiro,estacionDevuelto),
                estacionDevuelto, tarifa);
        alquilerRepository.save(alquiler);

        String moneda = null;
        Float montoConvertido = null;
        if (tipoMoneda != null){
            moneda = tipoMoneda;
            montoConvertido = peticionesConversor.convertirMoneda(
                    new ConversorDTO(tipoMoneda, alquiler.getMonto())).getImporte();
        } else {
            moneda = "PESO";
            montoConvertido = alquiler.getMonto();
        }

        //String moneda = (tipoMoneda != null) ? tipoMoneda : "PESO";
        /*Float montoConvertido = (tipoMoneda != null) ?
                peticionesConversor.convertirMoneda(new ConversorDTO(tipoMoneda, alquiler.getMonto())).getImporte() :
                alquiler.getMonto();*/

        return AlquilerMapper.toDTO(alquiler, moneda, montoConvertido);
    }

    private Tarifa asignarTarifa(Alquiler alquiler) {
        alquiler.setFechaHoraDevolucion(LocalDateTime.now().withNano(0));
        String diaWeek = String.valueOf(alquiler.getFechaHoraDevolucion().getDayOfWeek().getValue());
        String diaMes = String.valueOf(alquiler.getFechaHoraDevolucion().getDayOfMonth());
        String mes = String.valueOf(alquiler.getFechaHoraDevolucion().getMonthValue());
        String year = String.valueOf(alquiler.getFechaHoraDevolucion().getYear());

        Optional<Tarifa> tarifaConDescuento = tarifaRepository.findByParams(diaMes, mes, year);
        return tarifaConDescuento.orElseGet(() -> tarifaRepository.findByDay(diaWeek).orElseThrow());
    }


}
