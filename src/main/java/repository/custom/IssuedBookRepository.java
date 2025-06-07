package repository.custom;

import entity.IssuedBookEntity;
import repository.CrudRepository;
import java.util.HashMap;

public interface IssuedBookRepository extends CrudRepository<IssuedBookEntity, String> {
    HashMap<String, String> getMemberSet();

    HashMap<String, String> getBookSet();
}
