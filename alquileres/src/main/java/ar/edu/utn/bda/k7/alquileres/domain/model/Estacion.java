package ar.edu.utn.bda.k7.alquileres.domain.model;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class Estacion {

    private Long id;
    private String nombre;
    private LocalDateTime fechaHoraCreacion;
    private Float latitud;
    private Float longitud;

    public Estacion(Long id, String nombre, LocalDateTime fechaHoraCreacion, Float latitud, Float longitud) {
        this.id = id;
        this.nombre = nombre;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }


}
