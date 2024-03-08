package ar.edu.utn.bda.k7.alquileres.domain.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class AlquilerFinalizadoResponseDTO {
    private Long id;
    private String idCliente;
    private Long estado;
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private String tipoMoneda;
    private Float monto;
    private Long estacionRetiroId;
    private Long estacionDevolucionId;
    private Long tarifaId;

}
