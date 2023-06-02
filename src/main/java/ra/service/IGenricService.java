package ra.service;

import java.util.Optional;

public interface IGenricService<T, E>{
    Iterable<T> findAll();
    Optional<T> findById(E id);

    T save(T t);
    void delete(E id);
}
