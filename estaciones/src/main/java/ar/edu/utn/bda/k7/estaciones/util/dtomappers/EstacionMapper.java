package ar.edu.utn.bda.k7.estaciones.util.dtomappers;

import ar.edu.utn.bda.k7.estaciones.domain.dtos.EstacionDTO;
import ar.edu.utn.bda.k7.estaciones.domain.model.Estacion;

public class EstacionMapper {

    public static Estacion toEntity(EstacionDTO dto){
        return Estacion.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .fechaHoraCreacion(dto.getFechaHoraCreacion())
                .latitud(dto.getLatitud())
                .longitud(dto.getLongitud())
                .build();
    }

    /**
     * Returns a generic Data Transfer Object for an Estacion with {@code Estacion} argument.
     * <p>
     * You need an Estacion entity first.
     * DTO won't be created if some values are null.
     *
     * @param   estacion   an {@code Estacion}.
     * @return  a generic Data Transfer Object for {@code Estacion} argument.
     * @see     ar.edu.utn.bda.k7.estaciones.util.dtomappers.EstacionMapper#toDTO(Estacion)
     */
    public static EstacionDTO toDTO(Estacion estacion){
        return EstacionDTO.builder()
                .id(estacion.getId())
                .nombre(estacion.getNombre())
                .fechaHoraCreacion(estacion.getFechaHoraCreacion())
                .latitud(estacion.getLatitud())
                .longitud(estacion.getLongitud())
                .build();
    }
}
