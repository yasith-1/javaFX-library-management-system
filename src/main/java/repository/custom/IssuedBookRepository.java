package repository.custom;

import entity.BookEntity;
import entity.IssuedBookEntity;
import repository.CrudRepository;

import java.util.HashMap;

public interface IssuedBookRepository extends CrudRepository<IssuedBookEntity, String> {
    HashMap<String, String> getMemberSet();

    HashMap<String, String> getBookSet();

    Boolean deductbookQuantity(IssuedBookEntity entity);

    Boolean renewbookQuantity(IssuedBookEntity entity);

    Boolean deleteIssuedBook(IssuedBookEntity entity);

    IssuedBookEntity searchIssuedBook(String memberId ,String bookId);
}
