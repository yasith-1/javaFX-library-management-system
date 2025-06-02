package repository.custom;

import repository.CrudRepository;

public interface BookRepository extends CrudRepository {
    //    Specify method Book manipulate method add here
    String getLastBookId();
}
