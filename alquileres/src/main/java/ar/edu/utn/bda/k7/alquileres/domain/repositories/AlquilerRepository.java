package ar.edu.utn.bda.k7.alquileres.domain.repositories;

import ar.edu.utn.bda.k7.alquileres.domain.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    List<Alquiler> findByMontoGreaterThan(double monto);
    List<Alquiler> findByEstado(Long estado);
}

