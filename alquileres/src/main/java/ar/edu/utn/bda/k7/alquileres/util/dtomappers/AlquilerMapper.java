package ar.edu.utn.bda.k7.alquileres.util.dtomappers;

import ar.edu.utn.bda.k7.alquileres.domain.dtos.AlquilerDTO;
import ar.edu.utn.bda.k7.alquileres.domain.dtos.AlquilerFinalizadoResponseDTO;
import ar.edu.utn.bda.k7.alquileres.domain.model.Alquiler;
import ar.edu.utn.bda.k7.alquileres.domain.model.Tarifa;

public class AlquilerMapper {

    public static Alquiler toEntity(AlquilerDTO dto, Tarifa tarifa) {

        return Alquiler
                .builder()
                .id(dto.getId())
                .idCliente(dto.getIdCliente())
                .estado(dto.getEstado())
                .fechaHoraRetiro(dto.getFechaHoraRetiro())
                .fechaHoraDevolucion(dto.getFechaHoraDevolucion())
                .monto(dto.getMonto())
                .idEstacionDevolucion(dto.getEstacionDevolucionId())
                .idEstacionRetiro(dto.getEstacionRetiroId())
                .tarifa(tarifa)
                .build();
    }

    // voy  a modificar esto para que la conversion me la haga si lo q hago es un post cuando comienza un alquiler
    // o cuando yo creo uno general, para q no me joda q setee parametros con valores null
    public static AlquilerDTO toDTO(Alquiler alquiler) {

        if (alquiler.getFechaHoraDevolucion() == null && alquiler.getMonto() == null) {
            return AlquilerDTO.builder()
                    .id(alquiler.getId())
                    .idCliente(alquiler.getIdCliente())
                    .estado(alquiler.getEstado())
                    .estacionDevolucionId(null)
                    .estacionRetiroId(alquiler.getIdEstacionRetiro())
                    .tarifaId(null)
                    .fechaHoraRetiro(alquiler.getFechaHoraRetiro())
                    .fechaHoraDevolucion(null)
                    .monto(null)
                    .build();
        }else {

            return AlquilerDTO.builder()
                    .id(alquiler.getId())
                    .idCliente(alquiler.getIdCliente())
                    .estado(alquiler.getEstado())
                    .estacionDevolucionId(alquiler.getIdEstacionDevolucion())
                    .estacionRetiroId(alquiler.getIdEstacionRetiro())
                    .tarifaId(alquiler.getTarifa().getId())
                    .fechaHoraRetiro(alquiler.getFechaHoraRetiro())
                    .fechaHoraDevolucion(alquiler.getFechaHoraDevolucion())
                    .monto(alquiler.getMonto())
                    .build();
        }
    }

    public static AlquilerFinalizadoResponseDTO toDTO(Alquiler alquiler, String tipoMoneda, Float montoConvertido) {

            return AlquilerFinalizadoResponseDTO.builder()
                    .id(alquiler.getId())
                    .idCliente(alquiler.getIdCliente())
                    .estado(alquiler.getEstado())
                    .estacionDevolucionId(alquiler.getIdEstacionDevolucion())
                    .estacionRetiroId(alquiler.getIdEstacionRetiro())
                    .tarifaId(alquiler.getTarifa().getId())
                    .fechaHoraRetiro(alquiler.getFechaHoraRetiro())
                    .fechaHoraDevolucion(alquiler.getFechaHoraDevolucion())
                    .tipoMoneda(tipoMoneda)
                    .monto(montoConvertido)
                    .build();

    }
}

