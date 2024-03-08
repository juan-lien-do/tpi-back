package ar.edu.utn.bda.k7.alquileres.domain.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlquilerFinalizadoRequestDTO {
    private Long idEstacion;
    private String tipoMoneda;
}
