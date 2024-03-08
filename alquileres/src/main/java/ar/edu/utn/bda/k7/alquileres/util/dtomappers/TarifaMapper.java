package ar.edu.utn.bda.k7.alquileres.util.dtomappers;

import ar.edu.utn.bda.k7.alquileres.domain.dtos.TarifaDTO;
import ar.edu.utn.bda.k7.alquileres.domain.model.Tarifa;

public class TarifaMapper {
    public static Tarifa toEntity(TarifaDTO dto){
        return Tarifa.builder()
                .id(dto.getId())
                .tipoTarifa(dto.getTipoTarifa())
                .definicion(dto.getDefinicion())
                .diaSemana(dto.getDiaSemana())
                .diaMes(dto.getDiaMes())
                .mes(dto.getMes())
                .anio(dto.getAnio())
                .montoKm(dto.getMontoKm())
                .montoHora(dto.getMontoHora())
                .montoMinutoFraccion(dto.getMontoMinutoFraccion())
                .montoFijoAlquiler(dto.getMontoFijoAlquiler())
                .build();
    }

    public static TarifaDTO toDTO(Tarifa tarifa){
        return TarifaDTO.builder()
                .id(tarifa.getId())
                .tipoTarifa(tarifa.getTipoTarifa())
                .definicion(tarifa.getDefinicion())
                .diaSemana(tarifa.getDiaSemana())
                .diaMes(tarifa.getDiaMes())
                .mes(tarifa.getMes())
                .anio(tarifa.getAnio())
                .montoKm(tarifa.getMontoKm())
                .montoHora(tarifa.getMontoHora())
                .montoMinutoFraccion(tarifa.getMontoMinutoFraccion())
                .montoFijoAlquiler(tarifa.getMontoFijoAlquiler())
                .build();
    }

}
