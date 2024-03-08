package ar.edu.utn.bda.k7.alquileres.domain.model;

import ar.edu.utn.bda.k7.alquileres.domain.dtos.EstacionDTO;
import ar.edu.utn.bda.k7.alquileres.domain.services.EstacionService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ALQUILERES")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Alquiler {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_cliente")
    private String idCliente;
    @Column(name = "estado")
    private Long estado;
    @Column(name = "fecha_hora_retiro")
    private LocalDateTime fechaHoraRetiro;
    @Column(name = "fecha_hora_devolucion")
    private LocalDateTime fechaHoraDevolucion;
    @Column(name = "monto")
    private Float monto;
    @Column(name = "estacion_retiro")
    private Long idEstacionRetiro;
    @Column(name = "estacion_devolucion")
    private Long idEstacionDevolucion;
    @ManyToOne
    @JoinColumn(name = "id_tarifa")
    private Tarifa tarifa;

    public void actualizarEstado(Alquiler alquiler, Estacion estacionDevuelto) {
        alquiler.setEstado(2L);
        alquiler.setIdEstacionDevolucion(estacionDevuelto.getId());
        alquiler.setFechaHoraDevolucion(LocalDateTime.now().withNano(0));
    }

    public Float calcularMontoFinal(Float distancia){

        int[] horaMin= this.calcularDiferenciaHoraMin(fechaHoraDevolucion,fechaHoraRetiro);
        if (horaMin[1] < 31){
            return tarifa.getMontoFijoAlquiler()+ horaMin[1]*tarifa.getMontoMinutoFraccion()+
                    horaMin[0]*tarifa.getMontoHora()+ distancia*tarifa.getMontoKm();
        }else{
            return tarifa.getMontoFijoAlquiler()+(horaMin[0]+1)*tarifa.getMontoHora()+
                    distancia*tarifa.getMontoKm();
        }
    }

    private int[] calcularDiferenciaHoraMin (LocalDateTime fechaDevuelvo,
                                             LocalDateTime fechaRetiro){

        Duration duration = Duration.between(fechaRetiro, fechaDevuelvo);
        long horas = duration.toHours();
        long minutos = duration.toMinutes() % 60;
        return new int[]{(int) horas, (int) minutos};
    }

    public void finalizarAlquiler(Float distancia, Estacion estacionDevuelto, Tarifa tarifa){
        this.setTarifa(tarifa);
        this.actualizarEstado(this, estacionDevuelto);
        Float montoFinal= this.calcularMontoFinal(distancia);
        this.setMonto(montoFinal);

    };
}
