package ar.edu.utn.bda.k7.alquileres.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversorDTO {

    private String moneda_destino;
    private Float importe;

}
