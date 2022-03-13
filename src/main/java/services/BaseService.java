package services;

import java.util.List;

public interface BaseService<T,ID> {
    T save(T t);
    void update(T t);
    void delete(T t);
    T findById(ID id);
    List<T> findAll();
}

