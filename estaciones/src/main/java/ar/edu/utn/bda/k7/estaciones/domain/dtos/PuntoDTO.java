package ar.edu.utn.bda.k7.estaciones.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PuntoDTO {
    @NotNull(message = "No se ingreso longitud")
    private Float longitud;
    @NotNull(message = "No se ingreso latitud")
    private Float latitud;
}
