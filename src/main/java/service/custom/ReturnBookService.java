package service.custom;

import dto.ReturnBook;
import service.SuperService;

import java.util.HashMap;

public interface ReturnBookService extends SuperService {
    HashMap<String, String> getBookMap();

    HashMap<String, String> getMemberMap();

    Boolean addReturnRecord(ReturnBook returnBookDto);

    Boolean deleteReturnRecord(ReturnBook returnBookDto);
}
