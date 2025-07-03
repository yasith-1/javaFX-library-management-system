package service.custom;

import dto.ReturnBook;
import service.SuperService;

import java.util.HashMap;
import java.util.List;

public interface ReturnBookService extends SuperService {
    HashMap<String, String> getBookMap();

    HashMap<String, String> getMemberMap();

    Boolean addReturnRecord(ReturnBook returnBookDto);

    Boolean deleteReturnRecord(ReturnBook returnBookDto);

    ReturnBook searchReturnRecord(ReturnBook returnBookDto);

    List<ReturnBook> getAllReturnBookList();
}
