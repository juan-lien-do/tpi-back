package ar.edu.utn.bda.k7.estaciones.util.cercania;

import ar.edu.utn.bda.k7.estaciones.domain.model.Estacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistanciaEstacion {
    private Estacion estacion;
    private Float distancia;

}
