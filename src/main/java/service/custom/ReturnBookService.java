package service.custom;

import dto.ReturnBook;

import java.util.HashMap;

public interface ReturnBookService {
    HashMap<String, String> getBookMap();

    HashMap<String, String> getMemberMap();

    Boolean addReturnRecord(ReturnBook returnBookDto);

    Boolean deleteReturnRecord(ReturnBook returnBookDto);
}
