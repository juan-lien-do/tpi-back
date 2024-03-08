package ar.edu.utn.bda.k7.alquileres.domain.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TarifaDTO {
    private Long id;
    private Long tipoTarifa;
    private String definicion;
    private Long diaSemana;
    private Long diaMes;
    private Long mes;
    private Long anio;
    private Float montoFijoAlquiler;
    private Float montoMinutoFraccion;
    private Float montoKm;
    private Float montoHora;
}

