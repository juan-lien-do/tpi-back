package ar.edu.utn.bda.k7.alquileres.domain.model;

import ar.edu.utn.bda.k7.alquileres.domain.repositories.TarifaRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "tarifas")
public class Tarifa {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo_tarifa")
    private Long tipoTarifa;
    @Column(name = "definicion")
    private String definicion;
    @Column(name = "dia_semana")
    private Long diaSemana;
    @Column(name = "dia_mes")
    private Long diaMes;
    @Column(name = "mes")
    private Long mes;
    @Column(name = "anio")
    private Long anio;
    @Column(name = "monto_fijo_alquiler")
    private Float montoFijoAlquiler;
    @Column(name = "monto_minuto_fraccion")
    private Float montoMinutoFraccion;
    @Column(name = "monto_km")
    private Float montoKm;
    @Column(name = "monto_hora")
    private Float montoHora;

    public Tarifa(Long id, Long tipoTarifa, String definicion, Long diaSemana, Long diaMes,
                  Long mes, Long anio, Float montoFijoAlquiler, Float montoMinutoFraccion,
                  Float montoKm, Float montoHora) {
        this.id = id;
        this.tipoTarifa = tipoTarifa;
        this.definicion = definicion;
        this.diaSemana = diaSemana;
        this.diaMes = diaMes;
        this.mes = mes;
        this.anio = anio;
        this.montoFijoAlquiler = montoFijoAlquiler;
        this.montoMinutoFraccion = montoMinutoFraccion;
        this.montoKm = montoKm;
        this.montoHora = montoHora;
    }


}
