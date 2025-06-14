package service.custom.impl;

import dto.Book;
import dto.IssuedBook;
import entity.IssuedBookEntity;
import org.modelmapper.ModelMapper;
import repository.RepositoryFactory;
import repository.custom.impl.IssuedBookRepositoryImpl;
import service.custom.IssuedBookService;
import util.RepositoryType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IssuedBookServiceImpl implements IssuedBookService {
    IssuedBookRepositoryImpl repository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.ISSUEDBOOK);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public HashMap<String, String> getBookMap() {
        return repository.getBookSet();
    }

    @Override
    public HashMap<String, String> getMemberMap() {
        return repository.getMemberSet();
    }

    @Override
    public Boolean addIssueBookRecord(IssuedBook issuedBook) {
        IssuedBookEntity issuedBookEntity = modelMapper.map(issuedBook, IssuedBookEntity.class);
        return repository.add(issuedBookEntity);
    }

    @Override
    public Boolean deductBookQty(IssuedBook issuedBook) {
        IssuedBookEntity issuedBookEntity = modelMapper.map(issuedBook, IssuedBookEntity.class);
        return repository.deductbookQuantity(issuedBookEntity);
    }

    @Override
    public Boolean renewBookQty(IssuedBook issuedBook) {
        IssuedBookEntity issuedBookEntity = modelMapper.map(issuedBook, IssuedBookEntity.class);
        return repository.renewbookQuantity(issuedBookEntity);
    }

    @Override
    public Boolean updateIssueBookRecord(IssuedBook issuedBook) {
        IssuedBookEntity issuedBookEntity = modelMapper.map(issuedBook, IssuedBookEntity.class);
        return repository.update(issuedBookEntity);
    }

    @Override
    public Boolean deleteIssueBookRecord(IssuedBook issuedBook) {
        IssuedBookEntity issuedBookEntity = modelMapper.map(issuedBook, IssuedBookEntity.class);
        return repository.deleteIssuedBook(issuedBookEntity);
    }

    @Override
    public IssuedBook searchIssuedBook(String memberId, String bookId) {
        IssuedBookEntity issuedBookEntity = repository.searchIssuedBook(memberId, bookId);
        if (issuedBookEntity != null) {
            IssuedBook issuedBook = modelMapper.map(issuedBookEntity, IssuedBook.class);
            return issuedBook;
        }
        return null;
    }

    @Override
    public List<IssuedBook> getIssuedBookList() {
        List<IssuedBook> issuedBooksList = new ArrayList<>();
        List<IssuedBookEntity> issuedBookEntityList = repository.issuedBookList();

        if (issuedBookEntityList != null){
            for (IssuedBookEntity issuedBookEntity : issuedBookEntityList) {
                issuedBooksList.add(modelMapper.map(issuedBookEntity, IssuedBook.class));
            }
            return issuedBooksList;
        }
        return null;
    }

}
