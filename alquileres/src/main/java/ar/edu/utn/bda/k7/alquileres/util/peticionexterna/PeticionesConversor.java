package ar.edu.utn.bda.k7.alquileres.util.peticionexterna;

import ar.edu.utn.bda.k7.alquileres.domain.dtos.ConversorDTO;
import ar.edu.utn.bda.k7.alquileres.domain.exception.ConversorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class PeticionesConversor {

    private static final Logger log = LoggerFactory.getLogger(PeticionesEstacion.class);
    private static final Set<String> MONEDAS_VALIDAS = new HashSet<>(Arrays.asList("EUR", "CLP", "BRL", "COP", "PEN", "GBP"));

    public ConversorDTO convertirMoneda(ConversorDTO conversor)  throws ConversorException{

        if (conversor.getMoneda_destino()!= null && !MONEDAS_VALIDAS.contains(conversor.getMoneda_destino()))
            throw new ConversorException("Moneda no soportada");
        try {
            RestTemplate template = new RestTemplate();
            // Creación de la entidad a enviar
            HttpEntity<ConversorDTO> entity = new HttpEntity<>(conversor);
            // respuesta de la petición tendrá en su cuerpo a un objeto del tipo
            ResponseEntity<ConversorDTO> res = template.postForEntity(
                    "http://34.82.105.125:8080/convertir", entity, ConversorDTO.class);
            // Se comprueba si el código de repuesta es de la familia 200
            if (res.getStatusCode().is2xxSuccessful()) {
                log.debug("Persona creada correctamente: {}", res.getBody());
                return res.getBody();
            } else {
                log.warn("Respuesta no exitosa: {}", res.getStatusCode());
                return  null;
            }
        } catch (HttpClientErrorException ex) {
            // La repuesta no es exitosa.
            log.error("Error en la petición", ex);
            return null;
        }
    }
}
