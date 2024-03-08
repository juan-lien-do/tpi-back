package ar.edu.utn.bda.k7.alquileres.util.Calculos;

import ar.edu.utn.bda.k7.alquileres.domain.dtos.EstacionDTO;
import ar.edu.utn.bda.k7.alquileres.domain.model.Estacion;
import ar.edu.utn.bda.k7.alquileres.domain.model.Tarifa;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CalculoMonto {

    //Aca ya me dan la tarifa correspondiente, en otro lado es donde se le asigna cual tendria q ser
    // aca no.
//    public Float calcularMontoFinal(Estacion estacionRetiro, Estacion estacionDevuelto, LocalDateTime fechaDevuelvo,
//                                    LocalDateTime fechaRetiro, Tarifa tarifa){
//
//        Float distancia = this.calcularDistancia(estacionRetiro,estacionDevuelto);
//        int[] horaMin= this.calcularDiferenciaHoraMin(fechaDevuelvo,fechaRetiro);
//        if (horaMin[1] < 31){
//            return tarifa.getMontoFijoAlquiler()+ horaMin[1]*tarifa.getMontoMinutoFraccion()+
//                    horaMin[0]*tarifa.getMontoHora()+ distancia*tarifa.getMontoKm();
//        }else{
//            return tarifa.getMontoFijoAlquiler()+(horaMin[0]+1)*tarifa.getMontoHora()+
//                    distancia*tarifa.getMontoKm();
//        }
//    }
//    // CAMBIAR ACA LO QUE TENGO Q HACER CONVERSION DE UNIDADES!!!!!!!!
//    private  Float calcularDistancia (Estacion estacionRetiro, Estacion estacionDevuelto){
//        float lat = 110*(estacionRetiro.getLatitud() - estacionDevuelto.getLatitud());
//        float lon = 110*(estacionRetiro.getLongitud() - estacionDevuelto.getLongitud());
//        return (float)(Math.sqrt(Math.pow(lat, 2) + Math.pow(lon, 2)));
//    }
//
//    private int[] calcularDiferenciaHoraMin (LocalDateTime fechaDevuelvo,
//                                       LocalDateTime fechaRetiro){
//
//        // Calcular la diferencia entre las dos fechas
//        Duration duration = Duration.between(fechaRetiro, fechaDevuelvo);
//
//        // Obtener la diferencia en horas y minutos
//        long horas = duration.toHours();
//        long minutos = duration.toMinutes() % 60;
//
//        return new int[]{(int) horas, (int) minutos};
//    }
}
