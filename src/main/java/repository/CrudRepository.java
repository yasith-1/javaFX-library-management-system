package repository;

public interface CrudRepository<T, ID> extends SuperRepository {
    Boolean add(T entity);

    Boolean update(T entity);

    Boolean delete(T entity);

    T search(T entity);
}
