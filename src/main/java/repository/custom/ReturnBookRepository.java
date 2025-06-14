package repository.custom;

import repository.CrudRepository;
import java.util.HashMap;

public interface ReturnBookRepository extends CrudRepository {
    HashMap<String, String> getMemberSet();

    HashMap<String, String> getBookSet();
}
