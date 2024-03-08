package ar.edu.utn.bda.k7.estaciones.util.cercania;

import ar.edu.utn.bda.k7.estaciones.domain.dtos.PuntoDTO;
import ar.edu.utn.bda.k7.estaciones.domain.model.Estacion;
import lombok.Data;

import java.util.Comparator;
import java.util.stream.Stream;

// Esta clase provee al caso de uso de encontrar la estacion más cercana a cierto punto.
@Data
public class Cercania {

    // Esto es para encontrar el valor distancia y luego usarlo como parámetro del excluir lejanos
    public static Float distanciaAproximada(Stream<Estacion> estaciones, PuntoDTO dto){
        return estaciones.map(x -> Cercania
                        .distanciaAproximadaMetodoAux(x, dto))
                .min(Comparator.comparing(Float::floatValue))
                .get();
    }

    // Esto consigue la distancia aproximada por cada punto
    private static Float distanciaAproximadaMetodoAux(Estacion estacion, PuntoDTO dto){

        return Math.max(Math.abs(estacion.getLatitud() - dto.getLatitud()),
                Math.abs(estacion.getLongitud() - dto.getLongitud()));
    }


    // El chiste de esto es quitar elementos que no esten en un cuadrado con centro en el puntoDTO
    // y lado 2* distancia, para tener que hacer la menor cantidad de raices cuadradas posibles
    public static Boolean excluirLejanos(Estacion estacion, PuntoDTO dto, Float distancia){
        return !(Math.abs(estacion.getLatitud() - dto.getLatitud()) > distancia) &&
                !(Math.abs(estacion.getLongitud() - dto.getLongitud()) > distancia);
    }

    // Devuelve un objeto con la estación y la distancia calculada
    // Despues vas a tener que hacer un .min(Comparator.comparing(DistanciaEstacion::getDistancia))
    public static DistanciaEstacion distanciaFinal(Estacion estacion, PuntoDTO dto){
        float lat = 110*(estacion.getLatitud() - dto.getLatitud());
        float lon = 110*(estacion.getLongitud() - dto.getLongitud());
        Float dis = (float) Math.sqrt(Math.pow(lat, 2) + Math.pow(lon, 2));
        return new DistanciaEstacion(estacion, dis);
    }

}