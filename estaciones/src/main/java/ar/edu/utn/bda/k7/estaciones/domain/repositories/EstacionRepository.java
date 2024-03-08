package ar.edu.utn.bda.k7.estaciones.domain.repositories;

import ar.edu.utn.bda.k7.estaciones.domain.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionRepository extends JpaRepository<Estacion, Long> {
}
