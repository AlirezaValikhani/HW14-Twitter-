package repositories;

public interface BaseRepository<T,ID> {
    T save(T t);
    T update(T t);
    void delete(T t);
}
