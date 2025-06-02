package repository;

import service.SuperService;

public interface CrudRepository<T, ID> extends SuperRepository {
    Boolean add(T entity);

    Boolean update(T entity);

    Boolean delete(ID id);

    T search(ID id);
}
