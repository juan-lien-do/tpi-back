package ar.edu.utn.bda.k7.alquileres.domain.controllers;

import ar.edu.utn.bda.k7.alquileres.domain.dtos.AlquilerDTO;
import ar.edu.utn.bda.k7.alquileres.domain.dtos.AlquilerFinalizadoResponseDTO;
import ar.edu.utn.bda.k7.alquileres.domain.dtos.AlquilerFinalizadoRequestDTO;
import ar.edu.utn.bda.k7.alquileres.domain.exception.*;
import ar.edu.utn.bda.k7.alquileres.domain.services.AlquilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/alquileres")

public class AlquilerController {

    private final AlquilerService alquilerService;
    @Autowired
    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AlquilerDTO>> getAll(){
        return ResponseEntity.ok(alquilerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlquilerDTO> getById(@PathVariable Long id){
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        try {
            AlquilerDTO dto = alquilerService.findById(id);
            return ResponseEntity.ok(dto);
        }catch (AlquilerException e ){
            return ResponseEntity.notFound().header("CustomErrorHeader", e.getMessage()).build();
        }
    }

    @GetMapping(params = {"montoMinimo"})
    public ResponseEntity<List<AlquilerDTO>> filtrarPorMonto(@RequestParam("montoMinimo") double montoMinimo) {
        List<AlquilerDTO> alquileresFiltrados = alquilerService.filtrarPorMonto(montoMinimo);
        return ResponseEntity.ok(alquileresFiltrados);
    }
    // falta el try catch
    @GetMapping(params = {"estado"})
    public ResponseEntity<List<AlquilerDTO>> filtrarPorEstado(@RequestParam("estado") Long estado) {
        try {
            List<AlquilerDTO> alquileresFiltrados = alquilerService.filtrarPorEstado(estado);
            return ResponseEntity.ok(alquileresFiltrados);
        }
        catch (EstadoException e){
            return ResponseEntity.badRequest().header("CustomErrorHeader", e.getMessage()).build();
        }
    }

//    @PostMapping("/")
//    public ResponseEntity<AlquilerDTO> create(@RequestBody AlquilerDTO dto){
//        try{
//            AlquilerDTO savedDTO = alquilerService.save(dto);
//            return ResponseEntity.ok(savedDTO);
//        }
//        catch (EstacionException | TarifaException e){
//            return ResponseEntity.notFound().header("CustomErrorHeader", e.getMessage()).build();
//        }
//        catch (Exception e){
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @PostMapping("/")
    public ResponseEntity<AlquilerDTO> createNewRent(@RequestBody AlquilerDTO dto){
        try{
            AlquilerDTO savedDTO = alquilerService.saveNewRent(dto);
            return ResponseEntity.created(new URI("/alquileres/" + savedDTO.getId())).body(savedDTO);
        }
        catch (EstacionException e){
            return ResponseEntity.notFound().header("CustomErrorHeader", e.getMessage()).build();
        }
        catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<AlquilerFinalizadoResponseDTO> finishRent(@PathVariable Long id,
                                                                    @RequestBody AlquilerFinalizadoRequestDTO finalDto){
        try{
            AlquilerFinalizadoResponseDTO updateAlquiler = alquilerService.update(id,finalDto.getIdEstacion(),
                    finalDto.getTipoMoneda());
            return ResponseEntity.ok(updateAlquiler);
        }
        catch (AlquilerException | EstacionException   e){
            return ResponseEntity.notFound().header("CustomErrorHeader", e.getMessage()).build();
        }catch (ConversorException e){
            return ResponseEntity.badRequest().header("CustomErrorHeader", e.getMessage()).build();
        }
    }
}


