package ar.edu.utn.bda.k7.alquileres.domain.repositories;

import ar.edu.utn.bda.k7.alquileres.domain.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TarifaRepository extends JpaRepository<Tarifa,Long> {

    @Query("SELECT a FROM Tarifa a WHERE a.diaMes = :param1 AND a.mes = :param2 AND a.anio = :param3")
    Optional<Tarifa> findByParams(@Param("param1") String param1, @Param("param2") String param2, @Param("param3") String param3);

    @Query("SELECT a FROM Tarifa a WHERE a.diaSemana = :param1")
    Optional<Tarifa> findByDay(@Param("param1") String param1);

}
