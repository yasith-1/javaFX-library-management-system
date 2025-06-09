package service.custom;

import dto.IssuedBook;
import service.SuperService;

import java.util.HashMap;

public interface IssuedBookService extends SuperService {
    HashMap<String, String> getBookMap();

    HashMap<String, String> getMemberMap();

    Boolean addIssueBookRecord(IssuedBook issuedBook);

    Boolean deductBookQty(IssuedBook issuedBook);
}
