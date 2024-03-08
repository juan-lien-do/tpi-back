package ar.edu.utn.bda.k7.alquileres;

import ar.edu.utn.bda.k7.alquileres.domain.dtos.EstacionDTO;
import ar.edu.utn.bda.k7.alquileres.domain.model.Alquiler;
import ar.edu.utn.bda.k7.alquileres.domain.model.Estacion;
import ar.edu.utn.bda.k7.alquileres.domain.model.Tarifa;
import ar.edu.utn.bda.k7.alquileres.domain.services.EstacionService;
import ar.edu.utn.bda.k7.alquileres.util.Calculos.CalculoMonto;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CalcularMontoTest {


    @Test
    public void testCalcularMontoFinalNormal() {
        // Crear instancias de EstacionDTO, LocalDateTime y Tarifa para la prueba
        Estacion estacionRetiro = new Estacion(27L, "Estacion manuel belgrano",
                LocalDateTime.of(2023,11,14,16,50,43),5.0F,   -19F);
        Estacion estacionDevuelto = new Estacion(29L,"Estacion siria",
                LocalDateTime.of(2023,11,14,20,12,49),15F,-19F);
        LocalDateTime fechaRetiro = LocalDateTime.of(2023, 1, 1, 10, 0);
        LocalDateTime fechaDevuelvo = LocalDateTime.of(2023, 1, 1, 12, 30);

        Tarifa tarifa = new Tarifa(1L,1L,"S",1L,null,
                null,null,300F,6F,80F,240F);
        Alquiler alquiler = new Alquiler(2L,"2",2L,fechaRetiro,fechaDevuelvo,null,
                estacionRetiro.getId(),estacionDevuelto.getId(),tarifa);
        EstacionService estacionService = new EstacionService();
        Float resultado = alquiler.calcularMontoFinal(estacionService.calcularDistancia(estacionRetiro,estacionDevuelto));

        Float resultadoEsperado = 88960F;

        // Comparar el resultado con el resultado esperado
        assertEquals(resultadoEsperado, resultado, 0.01); // Ajusta la tolerancia según sea necesario
    }

    @Test
    public void testCalcularMontoFinalConDescuento() {
        // Crear instancias de EstacionDTO, LocalDateTime y Tarifa para la prueba
        Estacion estacionRetiro = new Estacion(27L, "Estacion manuel belgrano",
                LocalDateTime.of(2023,10,14,16,50,43),5.0F,   -19F);
        Estacion estacionDevuelto = new Estacion(29L,"Estacion siria",
                LocalDateTime.of(2023,10,14,20,12,49),15F,-19F);
        LocalDateTime fechaRetiro = LocalDateTime.of(2023, 1, 1, 10, 0);
        LocalDateTime fechaDevuelvo = LocalDateTime.of(2023, 1, 1, 12, 35);

        Tarifa tarifa = new Tarifa(9L,2L,"C",null,14L,
                10L,2023L,200F,4F,75F,175F);
        Alquiler alquiler = new Alquiler(2L,"2",2L,fechaRetiro,fechaDevuelvo,null,
                estacionRetiro.getId(),estacionDevuelto.getId(),tarifa);
        EstacionService estacionService = new EstacionService();
        Float resultado = alquiler.calcularMontoFinal(estacionService.calcularDistancia(estacionRetiro,estacionDevuelto));

        Float resultadoEsperado = 83225F;

        // Comparar el resultado con el resultado esperado
        assertEquals(resultadoEsperado, resultado, 0.01); // Ajusta la tolerancia según sea necesario
    }

}
