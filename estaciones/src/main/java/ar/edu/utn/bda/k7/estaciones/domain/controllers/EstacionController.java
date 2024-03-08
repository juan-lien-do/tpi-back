package ar.edu.utn.bda.k7.estaciones.domain.controllers;

import ar.edu.utn.bda.k7.estaciones.domain.dtos.EstacionDTO;
import ar.edu.utn.bda.k7.estaciones.domain.dtos.PuntoDTO;
import ar.edu.utn.bda.k7.estaciones.domain.exception.EstacionException;
import ar.edu.utn.bda.k7.estaciones.domain.services.EstacionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/estaciones")
public class EstacionController {
    @Autowired
    private final EstacionService estacionService;
    @GetMapping("/")
    public ResponseEntity<List<EstacionDTO>> getAll(){
        return ResponseEntity.ok(estacionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstacionDTO> getById(@PathVariable Long id){
        if (id < 1) {
            return ResponseEntity.badRequest().build();
        }
        try {
            EstacionDTO dto = estacionService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (EstacionException e){
            return ResponseEntity.notFound().header("CustomErrorHeader", e.getMessage()).build();
        }
    }


    @PostMapping("/")
    public ResponseEntity<EstacionDTO> create(@Valid @RequestBody EstacionDTO dto) {
        try {
            EstacionDTO dtofinal = estacionService.save(dto);
            return ResponseEntity.created(new URI("/estaciones/" + dtofinal.getId())).body(dtofinal);
        } catch (EstacionException e){
            return ResponseEntity.badRequest().header("CustomErrorHeader",e.getMessage()).build();
        }catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping(params = {"latitud", "longitud"})
    public ResponseEntity<EstacionDTO> getClosest(@RequestParam  Float latitud,
                                                  @RequestParam  Float longitud) {
        if (latitud < -90 || latitud > 90 || longitud < -180 || longitud > 180) {
            return ResponseEntity.badRequest().header
                    ("CustomErrorHeader","Los valores de latitud y longitud deben estar " +
                            "en los rangos válidos.").build();
        }
        try {
            PuntoDTO puntoDTO = new PuntoDTO(latitud, longitud);
            EstacionDTO estacionCercana = estacionService.getClosest(puntoDTO);
            return ResponseEntity.ok(estacionCercana);
        }catch (EstacionException e){
            return ResponseEntity.notFound().header("CustomErrorHeader", e.getMessage()).build();
        }
    }

}
