package ar.edu.utn.bda.k7.alquileres.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstacionDTO {
    private Long id;
    private String nombre;
    private LocalDateTime fechaHoraCreacion;
    private Float latitud;
    private Float longitud;


}
