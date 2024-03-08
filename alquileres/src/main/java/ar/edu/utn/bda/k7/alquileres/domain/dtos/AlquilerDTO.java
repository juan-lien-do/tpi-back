package ar.edu.utn.bda.k7.alquileres.domain.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AlquilerDTO {
    private Long id;
    private String idCliente;
    private Long estado;
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private Float monto;
    private Long estacionRetiroId;
    private Long estacionDevolucionId;
    private Long tarifaId;

    public AlquilerDTO(Long id, String idCliente, Long estado, LocalDateTime fechaHoraRetiro,
                       LocalDateTime fechaHoraDevolucion, Float monto, Long estacionRetiroId,
                       Long estacionDevolucionId, Long tarifaId) {
        this.id = id;
        this.idCliente = idCliente;
        this.estado = estado;
        this.fechaHoraRetiro = fechaHoraRetiro;
        this.fechaHoraDevolucion = fechaHoraDevolucion;
        this.monto = monto;
        this.estacionRetiroId = estacionRetiroId;
        this.estacionDevolucionId = estacionDevolucionId;
        this.tarifaId = tarifaId;
    }
}
