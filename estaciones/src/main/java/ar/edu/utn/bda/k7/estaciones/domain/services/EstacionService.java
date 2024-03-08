package ar.edu.utn.bda.k7.estaciones.domain.services;

import ar.edu.utn.bda.k7.estaciones.domain.dtos.EstacionDTO;
import ar.edu.utn.bda.k7.estaciones.domain.dtos.PuntoDTO;
import ar.edu.utn.bda.k7.estaciones.domain.exception.EstacionException;
import ar.edu.utn.bda.k7.estaciones.domain.model.Estacion;
import ar.edu.utn.bda.k7.estaciones.domain.repositories.EstacionRepository;
import ar.edu.utn.bda.k7.estaciones.domain.repositories.IdentifierRepository;
import ar.edu.utn.bda.k7.estaciones.util.cercania.Cercania;
import ar.edu.utn.bda.k7.estaciones.util.cercania.DistanciaEstacion;
import ar.edu.utn.bda.k7.estaciones.util.dtomappers.EstacionMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EstacionService {
    @Autowired
    private final EstacionRepository estacionRepository;
    @Autowired
    private final IdentifierRepository identifierRepository;

    @Transactional(readOnly = true)
    //sirve para aclara que una operacion es de solo lectura o no.
    public List<EstacionDTO> findAll(){
        return estacionRepository.findAll().stream().map(EstacionMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public EstacionDTO findById(Long id) throws EstacionException {
        Optional<Estacion> estacion = estacionRepository.findById(id);
        if(estacion.isEmpty()) throw new EstacionException("No se encontro la estacion");
        return estacion.map(EstacionMapper::toDTO).orElse(null);
    }

    @Transactional(readOnly = false)
    public EstacionDTO save(EstacionDTO dto) throws EstacionException {
        Long estacionId = (long) identifierRepository.nextValue("ESTACIONES");
        dto.setId(estacionId);
        dto.setFechaHoraCreacion(LocalDateTime.now().withNano(0));
        return EstacionMapper.toDTO(estacionRepository.save(EstacionMapper.toEntity(dto)));
    }

    @Transactional(readOnly = true)
    public EstacionDTO getClosest(PuntoDTO dto) throws EstacionException{
        List<Estacion> estaciones = estacionRepository.findAll();

        Float distanciaMinima = Cercania.distanciaAproximada(estaciones.stream(), dto);
        Estacion estacionCercana = estaciones.stream().filter(x -> Cercania.excluirLejanos(x, dto, distanciaMinima))
                .map(x -> Cercania.distanciaFinal(x, dto))
                .min(Comparator.comparing(DistanciaEstacion::getDistancia))
                .get().getEstacion();
        if (estacionCercana == null) throw new EstacionException("No se pudo encontrar");
        return EstacionMapper.toDTO(estacionCercana);
    }
}
