package repository.custom;

import entity.IssuedBookEntity;
import repository.CrudRepository;

import java.util.HashMap;
import java.util.List;

public interface IssuedBookRepository extends CrudRepository<IssuedBookEntity, String> {
    HashMap<String, String> getMemberSet();

    HashMap<String, String> getBookSet();

    HashMap<String , Integer> getBookQuantityMap();

    Boolean deleteIssuedBook(IssuedBookEntity entity);

    List<IssuedBookEntity> issuedBookList();

    IssuedBookEntity searchIssuedBook(String memberId, String bookId);
}
