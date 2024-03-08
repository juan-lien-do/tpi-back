package ar.edu.utn.bda.k7.estaciones.domain.repositories;

public interface IdentifierRepository {
    int nextValue(String tableName);
}
