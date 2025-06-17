package repository.custom;

import entity.IssuedBookEntity;
import entity.ReturnBookEntity;
import repository.CrudRepository;
import java.util.HashMap;

public interface ReturnBookRepository extends CrudRepository<ReturnBookEntity, String>  {
    HashMap<String, String> getMemberSet();

    HashMap<String, String> getBookSet();

    Boolean renewbookQuantity(ReturnBookEntity entity);

    Boolean deleteReturnRecord(ReturnBookEntity entity);

    Boolean deductbookQuantity(ReturnBookEntity entity);
}
