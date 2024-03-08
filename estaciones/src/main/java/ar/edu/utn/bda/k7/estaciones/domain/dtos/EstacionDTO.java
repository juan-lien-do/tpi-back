package ar.edu.utn.bda.k7.estaciones.domain.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EstacionDTO {
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    private LocalDateTime fechaHoraCreacion;
    @NotNull(message = "La latitud no puede ser nula")
    @DecimalMin(value = "-90", message = "La latitud debe ser mayor o igual a -90")
    @DecimalMax(value = "90", message = "La latitud debe ser menor o igual a 90")
    private Float latitud;
    @NotNull(message = "La longitud no puede ser nula")
    @DecimalMin(value = "-180", message = "La longitud debe ser mayor o igual a -180")
    @DecimalMax(value = "180", message = "La longitud debe ser menor o igual a 180")
    private Float longitud;
}
