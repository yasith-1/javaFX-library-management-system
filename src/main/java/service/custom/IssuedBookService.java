package service.custom;

import dto.Book;
import dto.IssuedBook;
import service.SuperService;

import java.util.HashMap;
import java.util.List;

public interface IssuedBookService extends SuperService {
    HashMap<String, String> getBookMap();

    HashMap<String, String> getMemberMap();

    Boolean addIssueBookRecord(IssuedBook issuedBook);

    Boolean deductBookQty(IssuedBook issuedBook);

    Boolean renewBookQty(IssuedBook issuedBook);

    Boolean updateIssueBookRecord(IssuedBook issuedBook);

    Boolean deleteIssueBookRecord(IssuedBook issuedBook);

    IssuedBook searchIssuedBook(String memberId , String bookId);

    List<IssuedBook> getIssuedBookList();
}
