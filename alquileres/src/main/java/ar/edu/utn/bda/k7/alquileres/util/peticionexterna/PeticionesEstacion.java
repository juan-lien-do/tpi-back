package ar.edu.utn.bda.k7.alquileres.util.peticionexterna;

import ar.edu.utn.bda.k7.alquileres.domain.dtos.EstacionDTO;
import ar.edu.utn.bda.k7.alquileres.domain.model.Estacion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component

public class PeticionesEstacion {

    private static final Logger log = LoggerFactory.getLogger(PeticionesEstacion.class);
    public Estacion invocarServicioGetId(Long id) {
        try {
            RestTemplate template = new RestTemplate();
            ResponseEntity<Estacion> res = template.getForEntity(
                    "http://localhost:8082/estaciones/{id}", Estacion.class, id);

    // Se comprueba si el código de repuesta es de la familia 200
            if (res.getStatusCode().is2xxSuccessful()) {
                log.debug("Persona obtenida correctamente: {}", res.getBody());

                return res.getBody();
            } else {
                log.warn("Respuesta no exitosa: {}", res.getStatusCode());
                return null;
            }
        } catch (HttpClientErrorException ex) {
    // La repuesta no es exitosa.
            log.error("Error en la petición", ex);
            return null;
        }
    }
}
