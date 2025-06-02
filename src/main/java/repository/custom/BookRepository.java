package repository.custom;

import entity.BookEntity;
import repository.CrudRepository;
import repository.SuperRepository;

import java.util.HashMap;

public interface BookRepository extends CrudRepository<BookEntity, String> {
    //    Specify method Book manipulate method add here
    String getLastBookId();

    HashMap<String, String> getAllGernes();

    HashMap<String, String> getAllAuthors();

    HashMap<String, String> getAllStatus();
}
