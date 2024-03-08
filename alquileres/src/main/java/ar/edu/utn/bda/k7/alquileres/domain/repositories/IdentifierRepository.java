package ar.edu.utn.bda.k7.alquileres.domain.repositories;

public interface IdentifierRepository {
    int nextValue(String tableName);
}
