package ar.edu.utn.bda.k7.alquileres.domain.services;

import ar.edu.utn.bda.k7.alquileres.domain.model.Estacion;
import org.springframework.stereotype.Service;

@Service
public class EstacionService {

    public   Float calcularDistancia (Estacion estacionRetiro, Estacion estacionDevuelto){
        float lat = 110*(estacionRetiro.getLatitud() - estacionDevuelto.getLatitud());
        float lon = 110*(estacionRetiro.getLongitud() - estacionDevuelto.getLongitud());
        return (float)(Math.sqrt(Math.pow(lat, 2) + Math.pow(lon, 2)));
    }
}
