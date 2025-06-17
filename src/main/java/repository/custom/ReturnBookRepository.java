package repository.custom;

import entity.ReturnBookEntity;
import repository.CrudRepository;
import java.util.HashMap;
import java.util.List;

public interface ReturnBookRepository extends CrudRepository<ReturnBookEntity, String>  {
    HashMap<String, String> getMemberSet();

    HashMap<String, String> getBookSet();

    Boolean renewbookQuantity(ReturnBookEntity entity);

    Boolean deductbookQuantity(ReturnBookEntity entity);

    Boolean removeIssuedBookRecord(ReturnBookEntity entity);

    List<ReturnBookEntity> returnBookList();
}
