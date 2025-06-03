package repository.custom;

import entity.BookEntity;
import repository.CrudRepository;
import java.util.HashMap;
import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, String> {
    //    Specify method Book manipulate method add here
    String getLastBookId();

    HashMap<String, String> getAllGernes();

    HashMap<String, String> getAllAuthors();

    HashMap<String, String> getAllStatus();

    List<BookEntity> getBookEntityList();
}
