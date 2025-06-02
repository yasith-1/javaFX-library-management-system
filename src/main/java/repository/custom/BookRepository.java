package repository.custom;

import repository.CrudRepository;

import java.util.HashMap;

public interface BookRepository extends CrudRepository {
    //    Specify method Book manipulate method add here
    String getLastBookId();
    HashMap<String, String> getAllGernes();
    HashMap<String, String> getAllAuthors();
    HashMap<String, String> getAllStatus();
}
